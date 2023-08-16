package com.global.medical.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.entity.Clinic;
import com.global.medical.error.CustomResponse;
import com.global.medical.error.SuccessResponsePage;
import com.global.medical.service.ClinicService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Operation(summary = "Get a clinic by its id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(clinicService.findById(id)));
        
    }

    @Operation(summary = "Get a clinic by its name")
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable @NotBlank String name) {
        return ResponseEntity.ok(new CustomResponse(clinicService.findByName(name)));
    }

    @Operation(summary = "Filter the clinics by name")
    @GetMapping("/filter")
    public ResponseEntity<?> getClinics(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(new CustomResponse(clinicService.findClinicByName(name)));
        } else {
            return ResponseEntity.ok(new CustomResponse(clinicService.findAll()));
        }
    }

    @Operation(summary = "Get all clinic")
    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "5") int pageSize,
                                     @RequestParam(defaultValue = "id") String sortcol,
                                     @RequestParam(defaultValue = "true") Boolean isAsc) {
        int totalPages = (int) Math.ceil(clinicService.countAllClinics() / Double.valueOf(pageSize));
        return ResponseEntity.ok(new SuccessResponsePage(clinicService.findAll(pageNo, pageSize, sortcol, isAsc), pageNo, totalPages));
    }

    @Operation(summary = "Add new clinic")
    @PostMapping("/create")
    public ResponseEntity<?> createClinic(@RequestBody @Valid Clinic clinic) {
        return ResponseEntity.ok(new CustomResponse(clinicService.createClinic(clinic)));
    }

    @Operation(summary = "Update a clinic")
    @PutMapping("/update")
    public ResponseEntity<?> updateClinic(@RequestBody @Valid Clinic clinic) {
        return ResponseEntity.ok(new CustomResponse(clinicService.updateClinic(clinic)));
    }

    @Operation(summary = "Delete a clinic by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClinic(@PathVariable Long id) {
        return ResponseEntity.ok(new CustomResponse(clinicService.deleteClinic(id)));
    }
}
