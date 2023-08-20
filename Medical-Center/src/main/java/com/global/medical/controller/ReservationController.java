package com.global.medical.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.entity.Reservation;
import com.global.medical.error.CustomResponse;
import com.global.medical.service.ReservationService;

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

}
