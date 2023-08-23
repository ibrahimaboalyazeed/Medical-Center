package com.global.medical.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.global.medical.dto.ReservationDto;
import com.global.medical.entity.Clinic;
import com.global.medical.entity.Doctor;
import com.global.medical.entity.Patient;
import com.global.medical.entity.Reservation;
import com.global.medical.error.CustomException;
import com.global.medical.repository.ReservationRepo;

import lombok.AllArgsConstructor;
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
		
		Clinic foundClinic =clinicService.findById(reservation.getClinic().getId());
		Doctor foundDoctor = doctorService.findById(reservation.getDoctor().getId());
		Patient foundPatient = patientService.findById(reservation.getPatient().getId());
		
		
		if(foundDoctor.getClinic().getId() != foundClinic.getId())
		{
			throw new CustomException("Dr with Id " +foundDoctor.getId()+" ("+foundDoctor.getFullName()+") "+" does not belong to the "+foundClinic.getName()+" clinic.");
		}
		
		reservation2.setClinic(foundClinic);
		reservation2.setDoctor(foundDoctor);
		reservation2.setPatient(foundPatient);
	
		
		if(reservation.getReservationDate().isBefore(LocalDate.now()) || (reservation.getReservationDate().isEqual(LocalDate.now()) && reservation.getReservationTime().isBefore(LocalTime.now().plusHours(1))))
		{
			throw new CustomException("Reservation date cannot be in the past.");
		}
		
		reservation2.setReservationDate(reservation.getReservationDate());
		
		reservation2.setReservationTime(reservation.getReservationTime());
		
		reservation2.setReservationDay(reservation.getReservationDate().getDayOfWeek());
		
		reservation2.setStatus(reservation.getStatus());
		
		reservation2.setShift();
		
		if(!reservation2.getShift().equals(foundDoctor.getShift()))
		{
			throw new CustomException("Doctor : "+foundDoctor.getFullName()+ " is not available in the " +reservation2.getShift().toString());
		}
		if(doesReservationExists(foundDoctor.getId(),reservation.getReservationDate(),reservation.getReservationTime()) != null)
		{
			throw new CustomException("sorry ,  the requested time and date for your reservation have already been reserved.");
		}
		
		
		
		return reservationRepo.save(reservation2);
	}
	
	private Reservation doesReservationExists(Long id, LocalDate reservationDate, LocalTime reservationTime) {
		
		return reservationRepo.findByDoctorIdAndReservationDateAndReservationTime(id,reservationDate,reservationTime);
	}

	public Reservation findReservation (Reservation reservation)
	{
		return reservationRepo.findByReservationDateAndReservationTimeAndShiftAndClinicIdAndDoctorIdAndPatientId(reservation.getReservationDate(),
				reservation.getReservationTime(),reservation.getShift(),reservation.getClinic().getId(),
				reservation.getDoctor().getId(),reservation.getPatient().getId());
	}


	public ReservationDto generateReservationReport( Map<Long, Long> clinicsMap, LocalDate date) {
		
		  validateReservationDate(date);
		  
          int totalPrice=0;		
          Map<String, List<LocalTime>> map= new HashMap<String, List<LocalTime>>();
        		  
         for (Map.Entry<Long, Long> entry : clinicsMap.entrySet()) {
			Long clinicId = entry.getKey();
			Long doctorId = entry.getValue();
			
			Clinic foundClinic = validateClinicExists(clinicId);
			Doctor foundDoctor = validateDoctorExists(doctorId);
			
			
			if(foundDoctor.getClinic().getId() != foundClinic.getId())
			{
				throw new CustomException("Dr with Id " +foundDoctor.getId()+" ("+foundDoctor.getFullName()+") "+" does not belong to the "+foundClinic.getName()+" clinic.");
			}
			
       	    List<LocalTime> unavailableTimes= findAllReservations(foundClinic,foundDoctor,date);
       	
       	    totalPrice=totalPrice+clinicService.findExaminationPriceById(foundClinic);
  
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

	public Clinic validateClinicExists(Long clinicId) {
		Clinic foundClinic = clinicService.findById(clinicId);
		return foundClinic;
	}
	public Doctor validateDoctorExists(Long doctorId) {
		Doctor foundDoctor = doctorService.findById(doctorId);
		return foundDoctor;
	}
	public List<LocalTime> findAllReservations(Clinic clinic,Doctor doctor,LocalDate date) {
		
		return reservationRepo.findReservationTimeByClinicAndReservationDate(clinic, doctor,date);
	}
	
	 public boolean doesDoctorBelongToClinic(Doctor doctor, Clinic clinicToCheck) {

	        return doctor.getClinic().equals(clinicToCheck);
	    }

		
	
	}


