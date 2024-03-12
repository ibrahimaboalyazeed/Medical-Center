package com.global.medical.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.dto.ReservationReport;
import com.global.medical.dto.ReservationRequest;
import com.global.medical.entity.Reservation;
import com.global.medical.error.CustomResponse;
import com.global.medical.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
//@Log4j2
@RequestMapping("/reservations")
@Validated
public class ReservationController {

	private final ReservationService reservationService;

	@Operation(summary = "Admin: Create a New Reservation")
	@PostMapping("/create")
	public ResponseEntity<?> createReservation(@RequestBody @Valid Reservation reservation) {
		return ResponseEntity.ok(new CustomResponse(reservationService.insert(reservation)));
	}

	@Operation(summary = "retrieve reservation report for a specific doctor  in a specific date ")
	@PostMapping("/report")
	public ResponseEntity<?> generateReservationReport(@RequestBody ReservationReport report) {

		LocalDate date = report.getDate();
		Map<Long, Long> map = report.getClinics();
		return ResponseEntity.ok(new CustomResponse(reservationService.generateReservationReport(map, date)));
	}

	@Operation(summary = "Add a new reservation")
	@PostMapping("")
	public ResponseEntity<?> addNewReservation(@RequestBody @Valid ReservationRequest request) {

		ReservationReport report = request.getReport();
		LocalDate date = report.getDate();
		Map<Long, Long> map = report.getClinics();
		Long patientId = request.getPatientId();
		LocalTime[] times = request.getTimeArray();

		return ResponseEntity.ok(new CustomResponse(reservationService.addNewReservation(map, times, date, patientId)));
	}

	@Operation(summary = "Delete a rservation by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClinic(@PathVariable Long id) {
		return ResponseEntity.ok(new CustomResponse(reservationService.deleteReservation(id)));
	}
	@Operation(summary = "retreive all rservations")
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(new CustomResponse(reservationService.findAll()));
	}
	@Operation(summary = "retreive a Doctors' reservations")
	@GetMapping("/doctor/{id}")
	public ResponseEntity<?> findDoctorReservations(@PathVariable Long id) {
		return ResponseEntity.ok(new CustomResponse(reservationService.findDoctorReservations(id)));
	}
	@Operation(summary = "retreive a Patients' reservations")
	@GetMapping("/patient/{id}")
	public ResponseEntity<?> findPatientReservations(@PathVariable Long id) {
		return ResponseEntity.ok(new CustomResponse(reservationService.findPatientReservations(id)));
	}

}
