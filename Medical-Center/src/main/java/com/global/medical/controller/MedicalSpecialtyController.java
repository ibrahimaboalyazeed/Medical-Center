package com.global.medical.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.entity.MedicalSpecialty;
import com.global.medical.error.CustomResponse;
import com.global.medical.error.SuccessResponsePage;
import com.global.medical.service.MedicalSpecialtyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/medical-specialties")
public class MedicalSpecialtyController {

    @Autowired
    private MedicalSpecialtyService medicalSpecialtyService;

    @Operation(summary = "Get a medical specialty by its id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.findById(id)));
    }

    @Operation(summary = "Get a medical specialty by its name")
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable @NotBlank String name) {
        return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.findByName(name)));
    }

    @Operation(summary = "Filter the medical specialties by name")
    @GetMapping("/filter")
    public ResponseEntity<?> getMedicalSpecialties(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.findMedicalSpecialtyByName(name)));
        } else {
            return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.findAll()));
        }
    }

    @Operation(summary = "Get all medical specialties")
    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "5") int pageSize,
                                     @RequestParam(defaultValue = "id") String sortcol,
                                     @RequestParam(defaultValue = "true") Boolean isAsc) {
        int totalPages = (int) Math.ceil(medicalSpecialtyService.countAllSpecialities() / Double.valueOf(pageSize));
        return ResponseEntity.ok(new SuccessResponsePage(medicalSpecialtyService.findAll(pageNo, pageSize, sortcol, isAsc), pageNo, totalPages));
    }

    @Operation(summary = "Add new medical specialty")
    @PostMapping("/create")
    public ResponseEntity<?> createMedicalSpecialty(@RequestBody @Valid MedicalSpecialty medicalSpecialty) {
        return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.createMedicalSpecialty(medicalSpecialty)));
    }

    @Operation(summary = "Update a medical specialty")
    @PutMapping("/update")
    public ResponseEntity<?> updateMedicalSpecialty(@RequestBody @Valid MedicalSpecialty medicalSpecialty) {
        return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.updateMedicalSpecialty(medicalSpecialty)));
    }

    @Operation(summary = "Delete a medical specialty by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicalSpecialty(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(medicalSpecialtyService.deleteMedicalSpecialty(id)));
    }
}
