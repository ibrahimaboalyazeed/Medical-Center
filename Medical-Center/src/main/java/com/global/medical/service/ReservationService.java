package com.global.medical.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.global.medical.dto.ReservationDto;
import com.global.medical.entity.Clinic;
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
		
		System.out.println(reservation.getReservationDate().isBefore(LocalDate.now()));
		
		System.out.println(reservation.getReservationDate().isEqual(LocalDate.now()));

		
		System.out.println(reservation.getReservationTime().isBefore(LocalTime.now()));

		
		
		if(reservation.getReservationDate().isBefore(LocalDate.now()) || (reservation.getReservationDate().isEqual(LocalDate.now()) && reservation.getReservationTime().isBefore(LocalTime.now())))
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


	public ReservationDto chooseClinics(List<Clinic> clinics, LocalDate date) {
		
		if(date.isBefore(LocalDate.now()))
		{
			throw new CustomException("Reservation date cannot be in the past.");
		}
          int totalPrice=0;		
          Map<String, List<LocalTime>> map= new HashMap<String, List<LocalTime>>();
          ReservationDto reservationDto = new ReservationDto();
        		  
         for (Iterator iterator = clinics.iterator(); iterator.hasNext();) {
       	 Clinic clinic = (Clinic) iterator.next();
       	 if(clinicService.findById(clinic.getId())==null)
       	 {
       		throw new CustomException("this clinic is not found");

       	 }
       	 List<LocalTime> unavailableTimes= findAllReservations(clinic,date);
       	
       	 totalPrice=totalPrice+clinicService.findExaminationPriceById(clinic);
  
       	 map.put(clinicService.findById(clinic.getId()).getName(), unavailableTimes);
       	 
         }
         reservationDto.setMap(map);
         reservationDto.setTotalPrice(totalPrice);
         
         return reservationDto;
	
}

	private List<LocalTime> findAllReservations(Clinic clinic,LocalDate date) {
		
		return reservationRepo.findReservationTimeByClinicAndReservationDate(clinic, date);
	}
		
	
	}


