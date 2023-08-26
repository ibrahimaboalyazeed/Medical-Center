package com.global.medical.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.data.convert.JodaTimeConverters.DateTimeToDateConverter;
import org.springframework.stereotype.Service;

import com.global.medical.dto.ReservationDto;
import com.global.medical.entity.Clinic;
import com.global.medical.entity.Doctor;
import com.global.medical.entity.Patient;
import com.global.medical.entity.Reservation;
import com.global.medical.enums.Status;
import com.global.medical.error.CustomException;
import com.global.medical.repository.ReservationRepo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class ReservationService {

	private final ReservationRepo reservationRepo;


	private final ClinicService clinicService;

	private final PatientService patientService;

	private final DoctorService doctorService;

	public Reservation insert(Reservation reservation) {

		if (findReservation(reservation) != null) {
			throw new CustomException(
					"sorry ,  the requested time and date for your reservation have already been reserved.");
		}

		Clinic foundClinic = clinicService.findById(reservation.getClinic().getId());
		Doctor foundDoctor = doctorService.findById(reservation.getDoctor().getId());
		Patient foundPatient = patientService.findById(reservation.getPatient().getId());

		if (foundDoctor.getClinic().getId() != foundClinic.getId()) {
			throw new CustomException("Dr with Id " + foundDoctor.getId() + " (" + foundDoctor.getFullName() + ") "
					+ " does not belong to the " + foundClinic.getName() + " clinic.");
		}
		validateReservationDateAndTime(reservation.getReservationDate(), reservation.getReservationTime());

		Reservation reservation2 = new Reservation();
		reservation2.setClinic(foundClinic);
		reservation2.setDoctor(foundDoctor);
		reservation2.setPatient(foundPatient);
		reservation2.setReservationDate(reservation.getReservationDate());
		reservation2.setReservationTime(reservation.getReservationTime());
		reservation2.setReservationDay(reservation.getReservationDate().getDayOfWeek());
		reservation2.setStatus(reservation.getStatus());
		reservation2.setShift();

		if (!reservation2.getShift().equals(foundDoctor.getShift())) {
			throw new CustomException("Doctor : " + foundDoctor.getFullName() + " is not available in the "
					+ reservation2.getShift().toString());
		}
		if (doesPatientReservationExists(foundPatient.getId(), reservation.getReservationDate(),
				reservation.getReservationTime()) != null) {
			throw new CustomException("You have already reserved an appointment in the "
					+ doesPatientReservationExists(foundPatient.getId(), reservation.getReservationDate(),
							reservation.getReservationTime()).getClinic().getName()
					+ " clinic at " +reservation.getReservationTime() );
		}
		if (doesPatientReservationExists(foundPatient.getId(), reservation.getReservationDate(),
				reservation.getClinic().getId()) != null) {
			throw new CustomException("You have already reserved an appointment in the "
					+ doesPatientReservationExists(foundPatient.getId(), reservation.getReservationDate(),
							reservation.getClinic().getId()).getClinic().getName()
					+ " clinic at " +reservation.getReservationDate() );
		}
		if (doesReservationExists(foundDoctor.getId(), reservation.getReservationDate(),
				reservation.getReservationTime()) != null) {
			throw new CustomException("sorry ,  Dr : "
					+ doesReservationExists(foundDoctor.getId(), reservation.getReservationDate(),
							reservation.getReservationTime()).getDoctor().getFullName()
					+ " is not available in this time !");
		}

		return reservationRepo.save(reservation2);
	}

	public Reservation doesReservationExists(Long id, LocalDate reservationDate, LocalTime reservationTime) {

		return reservationRepo.findByDoctorIdAndReservationDateAndReservationTime(id, reservationDate, reservationTime);
	}

	public Reservation doesPatientReservationExists(Long id, LocalDate reservationDate, LocalTime reservationTime) {

		return reservationRepo.findByPatientIdAndReservationDateAndReservationTime(id, reservationDate, reservationTime);
	}
	public Reservation doesPatientReservationExists(Long patientId, LocalDate reservationDate, Long clinicId) {
		
		return  reservationRepo.findByPatientIdAndReservationDateAndClinicId(patientId, reservationDate, clinicId) ;
	}

	public Reservation findReservation(Reservation reservation) {
		return reservationRepo.findByReservationDateAndReservationTimeAndShiftAndClinicIdAndDoctorIdAndPatientId(
				reservation.getReservationDate(), reservation.getReservationTime(), reservation.getShift(),
				reservation.getClinic().getId(), reservation.getDoctor().getId(), reservation.getPatient().getId());
	}

	public ReservationDto generateReservationReport(Map<Long, Long> clinicsMap, LocalDate date) {

		validateReservationDate(date);

		int totalPrice = 0;

		Map<String, List<LocalTime>> map = new HashMap<String, List<LocalTime>>();

		for (Map.Entry<Long, Long> entry : clinicsMap.entrySet()) {
			Long clinicId = entry.getKey();
			Long doctorId = entry.getValue();

			Clinic foundClinic = validateClinicExists(clinicId);
			Doctor foundDoctor = validateDoctorExists(doctorId);

			if (foundDoctor.getClinic().getId() != foundClinic.getId()) {
				throw new CustomException("Dr with Id " + foundDoctor.getId() + " (" + foundDoctor.getFullName() + ") "
						+ " does not belong to the " + foundClinic.getName() + " clinic.");
			}

			List<LocalTime> unavailableTimes = findAllReservations(foundClinic, foundDoctor, date);

			totalPrice = totalPrice + clinicService.findExaminationPriceById(foundClinic);

			map.put(foundClinic.getName(), unavailableTimes);

		}

		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setMap(map);
		reservationDto.setTotalPrice(totalPrice);

		return reservationDto;

	}

	public void validateReservationDate(LocalDate date) {
		if (date.isBefore(LocalDate.now())) {
			throw new CustomException("Reservation date cannot be in the past.");
		}
	}

	public void validateReservationDateAndTime(LocalDate date, LocalTime time) {
		if (date.isBefore(LocalDate.now())
				|| (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now().plusHours(1)))) {
			throw new CustomException("Reservation date cannot be in the past.");
		}
	}

	public Clinic validateClinicExists(Long clinicId) {
		Clinic foundClinic = clinicService.findById(clinicId);
		return foundClinic;
	}

	public Doctor validateDoctorExists(Long doctorId) {
		Doctor foundDoctor = doctorService.findById(doctorId);
		return foundDoctor;
	}

	public List<LocalTime> findAllReservations(Clinic clinic, Doctor doctor, LocalDate date) {

		return reservationRepo.findReservationTimeByClinicAndReservationDate(clinic, doctor, date);
	}

	public boolean doesDoctorBelongToClinic(Doctor doctor, Clinic clinicToCheck) {

		return doctor.getClinic().equals(clinicToCheck);
	}

	@Transactional
	public List<Reservation> addNewReservation(Map<Long, Long> map, LocalTime[] times, LocalDate date, Long patientId) {

		Patient foundPatient = patientService.findById(patientId);

		validateTimes(times);

		List<Reservation> reservations = new ArrayList<Reservation>();

		int index = 0;
		for (Entry<Long, Long> entry : map.entrySet()) {

			Long clinicId = entry.getKey();
			Long doctorId = entry.getValue();

			Clinic foundClinic = validateClinicExists(clinicId);
			Doctor foundDoctor = validateDoctorExists(doctorId);

			Reservation reservation = new Reservation();

			reservation.setPatient(foundPatient);
			reservation.setClinic(foundClinic);
			reservation.setDoctor(foundDoctor);
			reservation.setReservationDate(date);
			reservation.setReservationTime(times[index]);
			reservation.setReservationDay(date.getDayOfWeek());
			reservation.setShift();
			reservation.setStatus(Status.PENDING);

			reservations.add(insert(reservation));
			index++;

		}
		return reservations;

	}

	public void validateTimes(LocalTime[] times) {

		for (int i = 0; i < (times.length) - 1; i++) {

			if (times[i + 1].isBefore(times[i]) || times[i + 1].equals(times[i])) {

				throw new CustomException(
						"Please provide ordered reservation times with at least 30 minutes between each appointment. Thank you!");
			}
		}

	}

	public int deleteReservation(Long id) {
		findById(id);
		reservationRepo.deleteById(id);
		return 1;
	}

	private Reservation findById(Long id) {
		
		return reservationRepo.findById(id).orElseThrow(() -> new CustomException("Reservation With Id " +id+" is not found."));
	}

	public List<Reservation> findAll() {
		
		return reservationRepo.findAll();
	}

	public List<Reservation> findDoctorReservations(Long id) {
		doctorService.findById(id);
		return reservationRepo.findByDoctorId(id);
	}

	public  List<Reservation> findPatientReservations(Long id) {
		patientService.findById(id);
		return  reservationRepo.findByPatientId(id);
	}

}
