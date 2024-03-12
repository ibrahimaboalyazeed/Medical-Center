package com.global.medical.dto;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationReport {
	@NotNull(message = "clinics can not be null")
	private Map<Long,Long> clinics;
	@NotNull(message = "Date can not be null")
    private LocalDate date;

}
