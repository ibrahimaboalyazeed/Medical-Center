package com.global.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.global.medical.entity.Clinic;
import com.global.medical.error.CustomException;
import com.global.medical.repository.ClinicRepo;

@Service
public class ClinicService {

	@Autowired
	private ClinicRepo clinicRepo;


	public Clinic findById(long id) {

		Clinic clinic = clinicRepo.findById(id).orElseThrow(() -> new CustomException("this clinic is not found"));

		return clinic;
	}

	public List<Clinic> findAll() {

		return clinicRepo.findAll();
	}
	
	    public Page<Clinic> findAll(int pageNo, int pageSize, String sortCol, Boolean isAsc) {
	        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortCol);
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
	        return clinicRepo.findAll(pageable);
	    }

	public List<Clinic> insertAll(List<Clinic> clinics) {

		return clinicRepo.saveAll(clinics);
	}


	public Clinic updateClinic(Clinic clinic) {

		Clinic clinicToUpdate = clinicRepo.findById(clinic.getId())
				.orElseThrow(() -> new CustomException("this clinic is not found"));
		clinicToUpdate.setName(clinic.getName());
		clinicToUpdate.setExaminationPrice(clinic.getExaminationPrice());
		clinicToUpdate.setExaminationPeriod(clinic.getExaminationPeriod());
		clinicToUpdate.setDescription(clinic.getDescription());
		
		return clinicRepo.save(clinicToUpdate);
	}

	
	public Clinic createClinic(Clinic clinic) {

		Optional<Clinic> clinicToInsert = clinicRepo.findByName(clinic.getName());
		
		if (!clinicToInsert.isEmpty()) {
			throw  new CustomException("this clinic is already exists");
		}
		return clinicRepo.save(clinic);
	}

	public Clinic findByName(String name) {

		Clinic clinic = clinicRepo.findByName(name).orElseThrow(() -> new CustomException("this Clinic is not found"));

		return clinic;
	}
	
	public int deleteClinic(long id) {

		Clinic clinicToDelete = clinicRepo.findById(id).orElseThrow(() -> new CustomException("this Clinic is not found"));
		 clinicRepo.deleteById(clinicToDelete.getId());
		 return 1;
	}

	public List<Clinic> findClinicByName(String name) {

		return clinicRepo.findByNameContaining(name);
	}
	
	 public int countAllClinics() {
			
			return (int) clinicRepo.count();
		}

}
