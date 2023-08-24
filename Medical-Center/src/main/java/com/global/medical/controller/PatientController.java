package com.global.medical.controller;

import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.entity.Patient;
import com.global.medical.error.CustomResponse;
import com.global.medical.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
//@Log4j2
@RequestMapping("/patients")
@Validated
public class PatientController {
	
	private final PatientService patientService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById (@PathVariable long id){
		
		return ResponseEntity.ok(new CustomResponse(patientService.findById(id)));
	}
	@GetMapping("/all")
	public ResponseEntity<?> findAll (){

		return ResponseEntity.ok(new CustomResponse(patientService.findAll()));
	}
	@Operation(summary = "Add new patient")
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@RequestBody Patient patient) {
		
		return ResponseEntity.ok(new CustomResponse(patientService.insert(patient)));
	}
	@Operation(summary = "update the patient name")
	@PutMapping("/name/update")
	public ResponseEntity<?> updateFullName(@RequestParam long id , @RequestParam String fullName) {
		
		return ResponseEntity.ok(new CustomResponse(patientService.updateFullName(id,fullName)));
	}
	@Operation(summary = "update the patient phone number")
	@PutMapping("/phonenumber/update")
	public ResponseEntity<?> updatePhoneNumber(@RequestParam long id , @RequestParam String phoneNumber) {
		
		return ResponseEntity.ok(new CustomResponse(patientService.updatePhoneNumber(id,phoneNumber)));
	}
	@Operation(summary = "Delete a patient by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomResponse> deleteById(@PathVariable long id){
		
		return ResponseEntity.ok(new CustomResponse(patientService.deleteById(id)));
	}
	
	

}
