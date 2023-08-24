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
		
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new CustomException("Patient of Id " + id + " is not found"));
		
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
		
		serchPatient(patientToInsert);
	
		
	    Patient patientNew = patientRepo.save(patientToInsert);
		
		return patientNew;
	}
	public boolean serchPatient(Patient patientToInsert) {
	
		if(patientRepo.findByFullNameAndPhoneNumberAndAge(patientToInsert.getFullName(),patientToInsert.getPhoneNumber(),patientToInsert.getAge()) != null )
		{
			throw new CustomException("this Patient is already exists");
		}
		else {
			return true;
		}
	}
	public Patient updateFullName(long id, String fullName) {
		
		Patient patient = findById(id);
		
		patient.setFullName(fullName);
		
		return patientRepo.save(patient);
		
	
	}

	public Patient updatePhoneNumber(long id, String phoneNumber) {
		Patient patient = findById(id);

		patient.setPhoneNumber(phoneNumber);

		return patientRepo.save(patient);
	}
	public int deleteById(long id) {
		
		Patient patient =findById(id);

        patientRepo.deleteById(id);
        
		return 1;
	}
	public Patient findByAppUserID(long id) {


		return patientRepo.findByAppUserId(id);
	}
    
	

}
