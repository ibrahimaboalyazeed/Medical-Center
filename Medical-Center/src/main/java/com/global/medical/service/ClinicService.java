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
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private UserService userService;


	public Clinic findById(long id) {

		Clinic clinic = clinicRepo.findById(id).orElseThrow(() -> new CustomException("Clinic with ID " + id + " not found."));

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
				.orElseThrow(() -> new CustomException("Clinic with ID " + clinic.getId() + " not found."));
		clinicToUpdate.setName(clinic.getName());
		clinicToUpdate.setExaminationPrice(clinic.getExaminationPrice());
		clinicToUpdate.setExaminationPeriod(clinic.getExaminationPeriod());
		clinicToUpdate.setDescription(clinic.getDescription());
		
		return clinicRepo.save(clinicToUpdate);
	}

	
	public Clinic createClinic(Clinic clinic) {

		Optional<Clinic> clinicToInsert = clinicRepo.findByName(clinic.getName());
		
		if (!clinicToInsert.isEmpty()) {
			throw  new CustomException(clinic.getName() + " Clinic is already exists");
		}
		if(userService.findUserById(clinic.getAppUser().getId()).isEmpty())
		{
			throw  new CustomException("User is not found");
		}

		if(findClinicUserId(clinic.getAppUser().getId())!=null)
		{
			throw new CustomException("This user is already exists");
		}

		Clinic clinicToAdd= new Clinic();
		clinicToAdd.setName(clinic.getName());
		clinicToAdd.setDescription(clinic.getDescription());
		clinicToAdd.setExaminationPeriod(clinic.getExaminationPeriod());
		clinicToAdd.setExaminationPrice(clinic.getExaminationPrice());
		clinicToAdd.setAppUser(clinic.getAppUser());
		
		return clinicRepo.save(clinic);
	}

	private Object findClinicUserId(Long id) {
	
		return clinicRepo.findClinicUserId(id);
	}

	public Clinic findByName(String name) {

		Clinic clinic = clinicRepo.findByName(name).orElseThrow(() -> new CustomException(name + " Clinic is already exists"));

		return clinic;
	}
	
	public int deleteClinic(long id) {

		Clinic clinicToDelete = clinicRepo.findById(id).orElseThrow(() -> new CustomException("Clinic with ID " + id + " not found"));
		 clinicRepo.deleteById(clinicToDelete.getId());
		 return 1;
	}

	public List<Clinic> findClinicByName(String name) {

		return clinicRepo.findByNameContaining(name);
	}
	
	 public int countAllClinics() {
			
			return (int) clinicRepo.count();
		}



	public Clinic findByUserID(Long id) {
		
		return clinicRepo.findByAppUserId(id);
	}

	public int findExaminationPriceById(Clinic clinic) {
		return clinicRepo.findExaminationPriceById(clinic.getId()).getExaminationPrice();
	}


}
