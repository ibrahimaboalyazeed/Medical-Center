package com.global.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.global.medical.entity.MedicalSpecialty;
import com.global.medical.error.CustomException;
import com.global.medical.repository.MedicalSpecialtyRepo;

@Service
public class MedicalSpecialtyService {

	@Autowired
	private MedicalSpecialtyRepo medicalSpecialtyRepo;


	public MedicalSpecialty findById(long id) {

		MedicalSpecialty medicalSpecialty = medicalSpecialtyRepo.findById(id).orElseThrow(() -> new CustomException("this medical Specialty is not found"));

		return medicalSpecialty;
	}

	public List<MedicalSpecialty> findAll() {

		return medicalSpecialtyRepo.findAll();
	}
	
	    public Page<MedicalSpecialty> findAll(int pageNo, int pageSize, String sortCol, Boolean isAsc) {
	        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortCol);
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
	        return medicalSpecialtyRepo.findAll(pageable);
	    }

	public List<MedicalSpecialty> insertAll(List<MedicalSpecialty> medicalSpecialties) {

		return medicalSpecialtyRepo.saveAll(medicalSpecialties);
	}


	public MedicalSpecialty updateMedicalSpecialty(MedicalSpecialty medicalSpecialty) {

		MedicalSpecialty medicalSpecialtyToUpdate = medicalSpecialtyRepo.findById(medicalSpecialty.getId())
				.orElseThrow(() -> new CustomException("this medical Specialty is not found"));
		medicalSpecialtyToUpdate.setName(medicalSpecialty.getName());
		medicalSpecialtyToUpdate.setExaminationPrice(medicalSpecialty.getExaminationPrice());
		medicalSpecialtyToUpdate.setExaminationPeriod(medicalSpecialty.getExaminationPeriod());
		medicalSpecialtyToUpdate.setDescription(medicalSpecialty.getDescription());
		
		return medicalSpecialtyRepo.save(medicalSpecialtyToUpdate);
	}

	
	public MedicalSpecialty createMedicalSpecialty(MedicalSpecialty medicalSpecialty) {

		Optional<MedicalSpecialty> medicalSpecialtyToInsert = medicalSpecialtyRepo.findByName(medicalSpecialty.getName());
		
		if (!medicalSpecialtyToInsert.isEmpty()) {
			throw  new CustomException("this medical Specialty is already exists");
		}
		return medicalSpecialtyRepo.save(medicalSpecialty);
	}

	public MedicalSpecialty findByName(String name) {

		MedicalSpecialty medicalSpecialty = medicalSpecialtyRepo.findByName(name).orElseThrow(() -> new CustomException("this medical Specialty is not found"));

		return medicalSpecialty;
	}
	
	public int deleteMedicalSpecialty(long id) {

		MedicalSpecialty medicalSpecialtyToDelete = medicalSpecialtyRepo.findById(id).orElseThrow(() -> new CustomException("this medical Specialty is not found"));
		 medicalSpecialtyRepo.deleteById(medicalSpecialtyToDelete.getId());
		 return 1;
	}

	public List<MedicalSpecialty> findMedicalSpecialtyByName(String name) {

		return medicalSpecialtyRepo.findByNameContaining(name);
	}
	
	 public int countAllSpecialities() {
			
			return (int) medicalSpecialtyRepo.count();
		}

}
