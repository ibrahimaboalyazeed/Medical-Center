package com.global.medical.dto;

import java.time.LocalDate;
import java.util.List;

import com.global.medical.entity.Clinic;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationRequest {
	
	private List<Clinic> clinics;
    private LocalDate date;

}
