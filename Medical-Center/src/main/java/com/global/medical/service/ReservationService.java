package com.global.medical.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.global.medical.entity.Reservation;
import com.global.medical.error.CustomException;
import com.global.medical.repository.ReservationRepo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class ReservationService {
	
	private final ReservationRepo reservationRepo;
	
	private final UserService userService;
	
	private final ClinicService clinicService;
	
	private final PatientService patientService;
	
	private final DoctorService doctorService;

	public Reservation insert(Reservation reservation) {
		
		if(findReservation(reservation) != null)	
		{
			throw new CustomException("sorry ,  the requested time and date for your reservation have already been reserved.");
		}
		
		Reservation reservation2 = new Reservation();
		
		reservation2.setClinic(clinicService.findById(reservation.getClinic().getId()));
		
		reservation2.setDoctor(doctorService.findById(reservation.getDoctor().getId()));
		
		reservation2.setPatient(patientService.findById(reservation.getPatient().getId()));
		
		if(reservation.getReservationDate().isBefore(LocalDate.now()))
		{
			throw new CustomException("Reservation date cannot be in the past.");
		}
		
		reservation2.setReservationDate(reservation.getReservationDate());
		
		reservation2.setReservationTime(reservation.getReservationTime());
		
		reservation2.setReservationDay(reservation.getReservationDate().getDayOfWeek());
		
		reservation2.setStatus(reservation.getStatus());
		
		reservation2.setShift(reservation.getShift());
		
		
		
		return reservationRepo.save(reservation2);
	}
	
	public Reservation findReservation (Reservation reservation)
	{
		return reservationRepo.findByReservationDateAndReservationTimeAndShiftAndClinicIdAndDoctorIdAndPatientId(reservation.getReservationDate(),
				reservation.getReservationTime(),reservation.getShift(),reservation.getClinic().getId(),
				reservation.getDoctor().getId(),reservation.getPatient().getId());
	}

}
