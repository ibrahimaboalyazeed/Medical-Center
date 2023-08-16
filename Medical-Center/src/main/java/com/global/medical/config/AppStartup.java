package com.global.medical.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.global.medical.entity.AppUser;
import com.global.medical.entity.Clinic;
import com.global.medical.entity.Role;
import com.global.medical.service.ClinicService;
import com.global.medical.service.RoleService;
import com.global.medical.service.UserService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AppStartup implements CommandLineRunner{
	
	private final RoleService roleService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserService userService;
	
	private final ClinicService clinicService;


	@Override
    @Transactional
	public void run(String... args) throws Exception {	
		
		
		if (roleService.findAll().isEmpty()) {
		Role role = new Role();
		role.setName("ADMIN_ROLE");

		Role role1 = new Role();
		role1.setName("USER_ROLE");

		roleService.insertAll(Arrays.asList(role, role1));

	}
		 if (userService.findAll().isEmpty()) {
	            // Create demo roles
	            Role adminRole = roleService.findByName("ADMIN_ROLE");
	            Role userRole = roleService.findByName("USER_ROLE");

	            // Create demo AppUsers with roles
	            AppUser appUser = new AppUser();
	            appUser.setEmail("cardiology_clinic@gmail.com");
	            appUser.setPassword(passwordEncoder.encode("cardiology_password"));
	            appUser.setEnabled(true);
	            appUser.addRole(adminRole);
	            appUser.addRole(userRole);
	            
	            AppUser appUser1 = new AppUser();
	            appUser1.setEmail("dermatology_clinic@gmail.com");
	            appUser1.setPassword(passwordEncoder.encode("dermatology_password"));
	            appUser1.setEnabled(true);
	            appUser1.addRole(adminRole);
	            appUser1.addRole(userRole);
	            
	            AppUser appUser2 = new AppUser();
	            appUser2.setEmail("orthopedics_clinic@gmail.com");
	            appUser2.setPassword(passwordEncoder.encode("orthopedics_password"));
	            appUser2.setEnabled(true);
	            appUser2.addRole(adminRole);
	            appUser2.addRole(userRole);
	            
	            AppUser appUser3 = new AppUser();
	            appUser3.setEmail("gastroenterology_clinic@gmail.com");
	            appUser3.setPassword(passwordEncoder.encode("gastroenterology_password"));
	            appUser3.setEnabled(true);
	            appUser3.addRole(adminRole);
	            appUser3.addRole(userRole);
	            
	            AppUser appUser4 = new AppUser();
	            appUser4.setEmail("neurology_clinic@gmail.com");
	            appUser4.setPassword(passwordEncoder.encode("neurology_password"));
	            appUser4.setEnabled(true);
	            appUser4.addRole(adminRole);
	            appUser4.addRole(userRole);
	            
	            AppUser appUser5 = new AppUser();
	            appUser5.setEmail("ophthalmology_clinic@gmail.com");
	            appUser5.setPassword(passwordEncoder.encode("ophthalmology_password"));
	            appUser5.setEnabled(true);
	            appUser5.addRole(adminRole);
	            appUser5.addRole(userRole);
	            
	            AppUser appUser6 = new AppUser();
	            appUser6.setEmail("pediatrics_clinic@gmail.com");
	            appUser6.setPassword(passwordEncoder.encode("pediatrics_password"));
	            appUser6.setEnabled(true);
	            appUser6.addRole(adminRole);
	            appUser6.addRole(userRole);
	            
	            AppUser appUser7 = new AppUser();
	            appUser7.setEmail("psychiatry_clinic@gmail.com");
	            appUser7.setPassword(passwordEncoder.encode("psychiatry_password"));
	            appUser7.setEnabled(true);
	            appUser7.addRole(adminRole);
	            appUser7.addRole(userRole);
	            
	            AppUser appUser8 = new AppUser();
	            appUser8.setEmail("radiology_clinic@gmail.com");
	            appUser8.setPassword(passwordEncoder.encode("radiology_password"));
	            appUser8.setEnabled(true);
	            appUser8.addRole(adminRole);
	            appUser8.addRole(userRole);
	            
	            AppUser appUser9 = new AppUser();
	            appUser9.setEmail("urology_clinic@gmail.com");
	            appUser9.setPassword(passwordEncoder.encode("urology_password"));
	            appUser9.setEnabled(true);
	            appUser9.addRole(adminRole);
	            appUser9.addRole(userRole);
	            
	            // Save the AppUsers
	            userService.saveAll(Arrays.asList(appUser,appUser1,appUser2,appUser3,appUser4,
	            		appUser5,appUser6,appUser7,appUser8,appUser9));
	        }

		
		if (clinicService.findAll().isEmpty()) {
	        List<Clinic> clinics = new ArrayList<>();

	        Clinic clinic1 = new Clinic();
	        clinic1.setName("Cardiology");
	        clinic1.setDescription("Deals with heart-related issues.");
	        clinic1.setExaminationPeriod("30");
	        clinic1.setExaminationPrice(150.0);
            clinic1.setAppUser(userService.findById(1L));
	        clinics.add(clinic1);

	        Clinic clinic2 = new Clinic();
	        clinic2.setName("Dermatology");
	        clinic2.setDescription("Focuses on skin conditions.");
	        clinic2.setExaminationPeriod("15");
	        clinic2.setExaminationPrice(120.0);
            clinic2.setAppUser(userService.findById(2L));
	        clinics.add(clinic2);

	        Clinic clinic3 = new Clinic();
	        clinic3.setName("Orthopedics");
	        clinic3.setDescription("Deals with bone and joint problems.");
	        clinic3.setExaminationPeriod("15");
	        clinic3.setExaminationPrice(180.0);
            clinic3.setAppUser(userService.findById(3L));
	        clinics.add(clinic3);

	        Clinic clinic4 = new Clinic();
	        clinic4.setName("Gastroenterology");
	        clinic4.setDescription("Focuses on digestive system disorders.");
	        clinic4.setExaminationPeriod("30");
	        clinic4.setExaminationPrice(200.0);	   
            clinic4.setAppUser(userService.findById(4L));
	        clinics.add(clinic4);

	        Clinic clinic5 = new Clinic();
	        clinic5.setName("Neurology");
	        clinic5.setDescription("Deals with nervous system disorders.");
	        clinic5.setExaminationPeriod("15");
	        clinic5.setExaminationPrice(180.0);
            clinic5.setAppUser(userService.findById(5L));
	        clinics.add(clinic5);

	        Clinic clinic6 = new Clinic();
	        clinic6.setName("Ophthalmology");
	        clinic6.setDescription("Focuses on eye and vision issues.");
	        clinic6.setExaminationPeriod("15");
	        clinic6.setExaminationPrice(100.0);
            clinic6.setAppUser(userService.findById(6L));
	        clinics.add(clinic6);

	        Clinic clinic7 = new Clinic();
	        clinic7.setName("Pediatrics");
	        clinic7.setDescription("Deals with child healthcare.");
	        clinic7.setExaminationPeriod("30");
	        clinic7.setExaminationPrice(130.0);
            clinic7.setAppUser(userService.findById(7L));
	        clinics.add(clinic7);

	        Clinic clinic8 = new Clinic();
	        clinic8.setName("Psychiatry");
	        clinic8.setDescription("Focuses on mental health.");
	        clinic8.setExaminationPeriod("30");
	        clinic8.setExaminationPrice(170.0);
            clinic8.setAppUser(userService.findById(8L));
	        clinics.add(clinic8);

	        Clinic clinic9 = new Clinic();
	        clinic9.setName("Radiology");
	        clinic9.setDescription("Deals with medical imaging.");
	        clinic9.setExaminationPeriod("30");
	        clinic9.setExaminationPrice(190.0);
            clinic9.setAppUser(userService.findById(9L));
	        clinics.add(clinic9);

        Clinic clinic10 = new Clinic();
	        clinic10.setName("Urology");	        clinic10.setDescription("Focuses on urinary system disorders.");
      clinic10.setExaminationPeriod("30");
	        clinic10.setExaminationPrice(160.0);
           clinic10.setAppUser(userService.findById(10L));
	        clinics.add(clinic10);

	        clinicService.insertAll(clinics);
	    }
		

		 
		
		
	}

}
