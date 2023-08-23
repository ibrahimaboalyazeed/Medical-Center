package com.global.medical.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.global.medical.entity.Clinic;
import com.global.medical.entity.Doctor;

public class ReservationRequest {
	
	private Map<Long, LocalTime> datesMap;
	
	private Map<Long, Long> doctorClinic;
	
	private LocalDate date;
	
	private Long patientId;
	
	  
	
	

}
