package com.global.medical.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.dto.ReservationReport;
import com.global.medical.entity.Clinic;
import com.global.medical.entity.Doctor;
import com.global.medical.entity.Reservation;
import com.global.medical.error.CustomResponse;
import com.global.medical.service.ReservationService;

import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/reservations")
@Validated
public class ReservationController {
	
	private final ReservationService reservationService;
	
	@Operation(summary = "Add new reservation")
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody @Valid  Reservation reservation) {
        return ResponseEntity.ok(new CustomResponse(reservationService.insert(reservation)));
    }
	
	@Operation(summary = "retrieve reservation report for a specific doctor  in a specific date ")
    @PostMapping("/report")
    public ResponseEntity<?> generateReservationReport(@RequestBody ReservationReport request )  {

		    LocalDate date = request.getDate();
	        Map<Long, Long> map=request.getClinics();
        return ResponseEntity.ok(new CustomResponse(reservationService.generateReservationReport(map,date)));
    }
	
	
//	@Operation(summary = "retrieve reservations for a specific clinic in a specific date ")
//    @PostMapping("/reserved")
//    public ResponseEntity<?> generatePatientReservation(@RequestBody Map<Long, LocalTime> map )  {
//		
//		    LocalDate date = request.getDate();
//		    List<Clinic> clinics = request.getClinics();
//        return ResponseEntity.ok(new CustomResponse(reservationService.generateReservationReport(clinics,date)));
//    }
	

}
