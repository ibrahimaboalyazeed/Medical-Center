package com.global.medical.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ReservationRequest {
	@NotNull(message = "report cant be null")
	private ReservationReport report;
	@NotNull(message = "Array of time cant be null")
    private LocalTime[] timeArray;
	@NotNull(message = "Patient Id cant be null")
	private Long patientId;
	
	  
	
	

}
