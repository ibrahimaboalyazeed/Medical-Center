package com.global.medical.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.global.medical.entity.Reservation;
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
		
		
		Reservation reservation2 = new Reservation();
		
		reservation2.setClinic(clinicService.findById(reservation.getClinic().getId()));
		
		reservation2.setDoctor(doctorService.findById(reservation.getDoctor().getId()));
		
		reservation2.setPatient(patientService.findById(reservation.getPatient().getId()));
		
		reservation2.setReservationDate(reservation.getReservationDate());
		
		reservation2.setReservationTime(reservation.getReservationTime());
		
		reservation2.setReservationDay(reservation.getReservationDay());
		
		reservation2.setStatus(reservation.getStatus());
		
		
		
		return reservationRepo.save(reservation2);
	}

}
