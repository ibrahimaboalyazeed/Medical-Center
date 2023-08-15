package com.global.medical.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.medical.entity.MedicalSpecialty;
import com.global.medical.entity.Role;
import com.global.medical.service.MedicalSpecialtyService;
import com.global.medical.service.RoleService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AppStartup implements CommandLineRunner{
	
	private final RoleService roleService;
	
	private final MedicalSpecialtyService medicalSpecialtyService;


	@Override
	public void run(String... args) throws Exception {	
		
		
		if (roleService.findAll().isEmpty()) {
		Role role = new Role();
		role.setName("ADMIN_ROLE");

		Role role1 = new Role();
		role1.setName("USER_ROLE");

		roleService.insertAll(Arrays.asList(role, role1));

	}
		
		if (medicalSpecialtyService.findAll().isEmpty()) {
	        List<MedicalSpecialty> defaultSpecialties = new ArrayList<>();

	        MedicalSpecialty specialty1 = new MedicalSpecialty();
	        specialty1.setName("Cardiology");
	        specialty1.setDescription("Deals with heart-related issues.");
	        specialty1.setExaminationPeriod("30");
	        specialty1.setExaminationPrice(150.0);
	        defaultSpecialties.add(specialty1);

	        MedicalSpecialty specialty2 = new MedicalSpecialty();
	        specialty2.setName("Dermatology");
	        specialty2.setDescription("Focuses on skin conditions.");
	        specialty2.setExaminationPeriod("15");
	        specialty2.setExaminationPrice(120.0);
	        defaultSpecialties.add(specialty2);

	        MedicalSpecialty specialty3 = new MedicalSpecialty();
	        specialty3.setName("Orthopedics");
	        specialty3.setDescription("Deals with bone and joint problems.");
	        specialty3.setExaminationPeriod("15");
	        specialty3.setExaminationPrice(180.0);
	        defaultSpecialties.add(specialty3);

	        MedicalSpecialty specialty4 = new MedicalSpecialty();
	        specialty4.setName("Gastroenterology");
	        specialty4.setDescription("Focuses on digestive system disorders.");
	        specialty4.setExaminationPeriod("30");
	        specialty4.setExaminationPrice(200.0);
	        defaultSpecialties.add(specialty4);

	        MedicalSpecialty specialty5 = new MedicalSpecialty();
	        specialty5.setName("Neurology");
	        specialty5.setDescription("Deals with nervous system disorders.");
	        specialty5.setExaminationPeriod("15");
	        specialty5.setExaminationPrice(180.0);
	        defaultSpecialties.add(specialty5);

	        MedicalSpecialty specialty6 = new MedicalSpecialty();
	        specialty6.setName("Ophthalmology");
	        specialty6.setDescription("Focuses on eye and vision issues.");
	        specialty6.setExaminationPeriod("15");
	        specialty6.setExaminationPrice(100.0);
	        defaultSpecialties.add(specialty6);

	        MedicalSpecialty specialty7 = new MedicalSpecialty();
	        specialty7.setName("Pediatrics");
	        specialty7.setDescription("Deals with child healthcare.");
	        specialty7.setExaminationPeriod("30");
	        specialty7.setExaminationPrice(130.0);
	        defaultSpecialties.add(specialty7);

	        MedicalSpecialty specialty8 = new MedicalSpecialty();
	        specialty8.setName("Psychiatry");
	        specialty8.setDescription("Focuses on mental health.");
	        specialty8.setExaminationPeriod("30");
	        specialty8.setExaminationPrice(170.0);
	        defaultSpecialties.add(specialty8);

	        MedicalSpecialty specialty9 = new MedicalSpecialty();
	        specialty9.setName("Radiology");
	        specialty9.setDescription("Deals with medical imaging.");
	        specialty9.setExaminationPeriod("30");
	        specialty9.setExaminationPrice(190.0);
	        defaultSpecialties.add(specialty9);

	        MedicalSpecialty specialty10 = new MedicalSpecialty();
	        specialty10.setName("Urology");
	        specialty10.setDescription("Focuses on urinary system disorders.");
	        specialty10.setExaminationPeriod("30");
	        specialty10.setExaminationPrice(160.0);
	        defaultSpecialties.add(specialty10);

	        medicalSpecialtyService.insertAll(defaultSpecialties);
	    }
		

		 
		
		
	}

}
