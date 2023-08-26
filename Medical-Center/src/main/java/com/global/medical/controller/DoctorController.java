package com.global.medical.controller;

import javax.validation.Valid;

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

import com.global.medical.entity.Doctor;
import com.global.medical.error.CustomResponse;
import com.global.medical.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Get a doctor by his Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(new CustomResponse(doctorService.findById(id)));
    }
    @Operation(summary = "Get all doctors")
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(new CustomResponse(doctorService.findAll()));
    }

    @Operation(summary = "Add new doctor")
    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@RequestBody @Valid Doctor doctor) {
        return ResponseEntity.ok(new CustomResponse(doctorService.insert(doctor)));
    }

    @Operation(summary = "Update the doctor's name")
    @PutMapping("/name/update")
    public ResponseEntity<?> updateFullName(@RequestParam long id, @RequestParam String fullName) {
        return ResponseEntity.ok(new CustomResponse(doctorService.updateFullName(id, fullName)));
    }
	@Operation(summary = "update the doctor phone number")
	@PutMapping("/phonenumber/update")
	public ResponseEntity<?> updatePhoneNumber(@RequestParam long id , @RequestParam String phoneNumber) {
		
		return ResponseEntity.ok(new CustomResponse(doctorService.updatePhoneNumber(id,phoneNumber)));
	}


    @Operation(summary = "Delete a doctor by his id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(new CustomResponse(doctorService.deleteById(id)));
    }
}
