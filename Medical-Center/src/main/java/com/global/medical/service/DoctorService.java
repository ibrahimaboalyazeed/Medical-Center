package com.global.medical.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.medical.entity.AppUser;
import com.global.medical.entity.Doctor;
import com.global.medical.error.CustomException;
import com.global.medical.repository.DoctorRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepo doctorRepository;
    private final UserService userService;

    public Doctor findById(long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new CustomException("This Doctor is not found"));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor insert(Doctor doctor) {
        Doctor doctorToInsert = new Doctor();
        doctorToInsert.setFullName(doctor.getFullName());
        doctorToInsert.setPhoneNumber(doctor.getPhoneNumber());
        doctorToInsert.setClinic(doctor.getClinic());

        AppUser appUser = userService.findById(doctor.getAppUser().getId());
        doctorToInsert.setAppUser(appUser);

        Doctor newDoctor = doctorRepository.save(doctorToInsert);
        return newDoctor;
    }

    public Doctor updateFullName(long id, String fullName) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new CustomException("This Doctor is not found"));

        doctor.setFullName(fullName);
        return doctorRepository.save(doctor);
    }

    public Doctor updatePhoneNumber(long id, String phoneNumber) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new CustomException("This Doctor is not found"));

        doctor.setPhoneNumber(phoneNumber);
        return doctorRepository.save(doctor);
    }

    public int deleteById(long id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new CustomException("This Doctor is not found"));

        doctorRepository.deleteById(id);
        return 1;
    }
}

