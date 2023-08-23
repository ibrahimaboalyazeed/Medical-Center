package com.global.medical.dto;

import java.time.LocalDate;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationReport {
	
	private Map<Long,Long> clinics;
    private LocalDate date;

}
