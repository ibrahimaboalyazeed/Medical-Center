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
import com.global.medical.entity.Doctor;
import com.global.medical.entity.Patient;
import com.global.medical.entity.Role;
import com.global.medical.enums.Shift;
import com.global.medical.service.ClinicService;
import com.global.medical.service.DoctorService;
import com.global.medical.service.PatientService;
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
	
	private final PatientService patientService;
	
	private final DoctorService doctorService;


	@Override
    @Transactional
	public void run(String... args) throws Exception {	
		
		
		if (roleService.findAll().isEmpty()) {
		Role role = new Role();
		role.setName("ROLE_ADMIN");

		Role role1 = new Role();
		role1.setName("ROLE_USER");

		roleService.insertAll(Arrays.asList(role, role1));

	}
		 if (userService.findAll().isEmpty()) {
	            // Create demo roles
	            Role adminRole = roleService.findByName("ROLE_ADMIN");
	            Role userRole = roleService.findByName("ROLE_USER");

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
	            
	            AppUser appUser10 = new AppUser();
	            appUser10.setEmail("mohamed@gmail.com");
	            appUser10.setPassword(passwordEncoder.encode("mohamed"));
	            appUser10.setEnabled(true);
	            appUser10.addRole(userRole);      
	            
	            AppUser appUser11 = new AppUser();
	            appUser11.setEmail("ahmed@gmail.com");
	            appUser11.setPassword(passwordEncoder.encode("ahemd"));
	            appUser11.setEnabled(true);
	            appUser11.addRole(userRole);
	            
	            AppUser appUser12 = new AppUser();
	            appUser12.setEmail("hema@gmail.com");
	            appUser12.setPassword(passwordEncoder.encode("hema"));
	            appUser12.setEnabled(true);
	            appUser12.addRole(userRole);
	            
	            AppUser appUser13 = new AppUser();
	            appUser13.setEmail("amora@gmail.com");
	            appUser13.setPassword(passwordEncoder.encode("amora"));
	            appUser13.setEnabled(true);
	            appUser13.addRole(userRole);
	            
	            AppUser appUser14 = new AppUser();
	            appUser14.setEmail("mahmoud@gmail.com");
	            appUser14.setPassword(passwordEncoder.encode("mahmoud"));
	            appUser14.setEnabled(true);
	            appUser14.addRole(userRole);
	            
	            AppUser appUser15 = new AppUser();
	            appUser15.setEmail("zezo@gmail.com");
	            appUser15.setPassword(passwordEncoder.encode("zezo"));
	            appUser15.setEnabled(true);
	            appUser15.addRole(userRole);
	            
	            
	            
	            
	    
	            
	            // Save the AppUsers
	            userService.saveAll(Arrays.asList(appUser,appUser1,appUser2,appUser3,appUser4,
	            		appUser5,appUser6,appUser7,appUser8,appUser9,appUser10,appUser11,appUser12,appUser13,appUser14,appUser15));
	        }

		
		if (clinicService.findAll().isEmpty()) {
	        List<Clinic> clinics = new ArrayList<>();

	        Clinic clinic1 = new Clinic();
	        clinic1.setName("Cardiology");
	        clinic1.setDescription("Deals with heart-related issues.");
	        clinic1.setExaminationPeriod("30");
	        clinic1.setExaminationPrice(150);
            clinic1.setAppUser(userService.findById(1L));
	        clinics.add(clinic1);

	        Clinic clinic2 = new Clinic();
	        clinic2.setName("Dermatology");
	        clinic2.setDescription("Focuses on skin conditions.");
	        clinic2.setExaminationPeriod("15");
	        clinic2.setExaminationPrice(120);
            clinic2.setAppUser(userService.findById(2L));
	        clinics.add(clinic2);

	        Clinic clinic3 = new Clinic();
	        clinic3.setName("Orthopedics");
	        clinic3.setDescription("Deals with bone and joint problems.");
	        clinic3.setExaminationPeriod("15");
	        clinic3.setExaminationPrice(180);
            clinic3.setAppUser(userService.findById(3L));
	        clinics.add(clinic3);

	        Clinic clinic4 = new Clinic();
	        clinic4.setName("Gastroenterology");
	        clinic4.setDescription("Focuses on digestive system disorders.");
	        clinic4.setExaminationPeriod("30");
	        clinic4.setExaminationPrice(200);	   
            clinic4.setAppUser(userService.findById(4L));
	        clinics.add(clinic4);

	        Clinic clinic5 = new Clinic();
	        clinic5.setName("Neurology");
	        clinic5.setDescription("Deals with nervous system disorders.");
	        clinic5.setExaminationPeriod("15");
	        clinic5.setExaminationPrice(180);
            clinic5.setAppUser(userService.findById(5L));
	        clinics.add(clinic5);

	        Clinic clinic6 = new Clinic();
	        clinic6.setName("Ophthalmology");
	        clinic6.setDescription("Focuses on eye and vision issues.");
	        clinic6.setExaminationPeriod("15");
	        clinic6.setExaminationPrice(100);
            clinic6.setAppUser(userService.findById(6L));
	        clinics.add(clinic6);

	        Clinic clinic7 = new Clinic();
	        clinic7.setName("Pediatrics");
	        clinic7.setDescription("Deals with child healthcare.");
	        clinic7.setExaminationPeriod("30");
	        clinic7.setExaminationPrice(130);
            clinic7.setAppUser(userService.findById(7L));
	        clinics.add(clinic7);

	        Clinic clinic8 = new Clinic();
	        clinic8.setName("Psychiatry");
	        clinic8.setDescription("Focuses on mental health.");
	        clinic8.setExaminationPeriod("30");
	        clinic8.setExaminationPrice(170);
            clinic8.setAppUser(userService.findById(8L));
	        clinics.add(clinic8);

	        Clinic clinic9 = new Clinic();
	        clinic9.setName("Radiology");
	        clinic9.setDescription("Deals with medical imaging.");
	        clinic9.setExaminationPeriod("30");
	        clinic9.setExaminationPrice(190);
            clinic9.setAppUser(userService.findById(9L));
	        clinics.add(clinic9);

            Clinic clinic10 = new Clinic();
	        clinic10.setName("Urology");	      
	        clinic10.setDescription("Focuses on urinary system disorders.");
            clinic10.setExaminationPeriod("30");
	        clinic10.setExaminationPrice(160);
            clinic10.setAppUser(userService.findById(10L));
	        clinics.add(clinic10);

	        clinicService.insertAll(clinics);
	    }
		
		if (doctorService.findAll().isEmpty()) {

			Doctor doctor = new Doctor();

			doctor.setFullName("Mohamed");
			doctor.setClinic(clinicService.findById(1L));
			doctor.setAppUser(userService.findById(11L));
			doctor.setPhoneNumber("01098157536");
			doctor.setShift(Shift.MORNING);
			doctorService.insert(doctor);
			
			Doctor doctor1 = new Doctor();

			doctor1.setFullName("Ahemd");
			doctor1.setClinic(clinicService.findById(2L));
			doctor1.setAppUser(userService.findById(12L));
			doctor1.setPhoneNumber("01173497810");
			doctor1.setShift(Shift.EVENING);
			doctorService.insert(doctor1);
			
			Doctor doctor2 = new Doctor();

			doctor2.setFullName("Mahmoud");
			doctor2.setClinic(clinicService.findById(3L));
			doctor2.setAppUser(userService.findById(15L));
			doctor2.setPhoneNumber("01475397415");
			doctor2.setShift(Shift.MORNING);
			doctorService.insert(doctor2);


		}
		
		if (patientService.findAll().isEmpty()) {

			Patient patient = new Patient();

			patient.setFullName("Saeed");
			patient.setAge(25);
			patient.setAppUser(userService.findById(13L));
			patient.setPhoneNumber("01025367849");
			patientService.insert(patient);
			
			
			Patient patient1 = new Patient();

			patient1.setFullName("Fathi");
			patient1.setAge(35);
			patient1.setAppUser(userService.findById(14L));
			patient1.setPhoneNumber("01224583112");
			patientService.insert(patient1);
			
			Patient patient2 = new Patient();

			patient2.setFullName("Karem");
			patient2.setAge(35);
			patient2.setAppUser(userService.findById(16L));
			patient2.setPhoneNumber("01596347569");
			patientService.insert(patient2);


		}
		

		 
		
		
	}

}
