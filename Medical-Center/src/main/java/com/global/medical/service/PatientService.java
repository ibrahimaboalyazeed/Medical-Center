package com.global.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.global.medical.entity.AppUser;
import com.global.medical.entity.Patient;
import com.global.medical.error.CustomException;
import com.global.medical.repository.PatientRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientService {
	
	private final PatientRepo patientRepo;
	
    private final UserService userService;
	
	public Patient findById(long id) {
		
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new CustomException("This Patient is not found"));
		
		return patient;
	}
    public List<Patient> findAll() {
		
		List<Patient> patients = patientRepo.findAll();
		
		return patients;
	}
    public Patient insert(Patient patient) {
		
		
		Patient patientToInsert= new Patient();
		patientToInsert.setFullName(patient.getFullName());
		patientToInsert.setPhoneNumber(patient.getPhoneNumber());
		patientToInsert.setAge(patient.getAge());
		
		AppUser appUser = userService.findById(patient.getAppUser().getId());
		
		patientToInsert.setAppUser(appUser);
		
	    Patient patientNew = patientRepo.save(patient);
		
		return patientNew;
	}
	public Patient updateFullName(long id, String fullName) {
		
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new CustomException("This Patient is not found"));
		
		patient.setFullName(fullName);
		
		return patientRepo.save(patient);
		
	
	}

	public Patient updatePhoneNumber(long id, String phoneNumber) {
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new CustomException("This Patient is not found"));

		patient.setPhoneNumber(phoneNumber);

		return patientRepo.save(patient);
	}
	public int deleteById(long id) {
		
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new CustomException("This Patient is not found"));

        patientRepo.deleteById(id);
        
		return 1;
	}
    
	

}
