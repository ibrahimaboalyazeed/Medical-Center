package com.global.medical.dto;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.global.medical.entity.Clinic;
import com.global.medical.entity.Reservation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationDto {
	
	private Map<String, List<LocalTime>> map;
	
	private int totalPrice ;

}
