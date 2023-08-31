package com.global.medical.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.global.medical.entity.Clinic;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClinicRepoTest {
	
    @Mock
	ClinicRepo clinicRepo;
	

	@Test
	void findByNameNotFoundTest() {

		Optional<Clinic> clinic = clinicRepo.findByName("Cardiology0");

		assertEquals(false, clinic.isPresent());

	}
	
	@Test
	void findByNameFoundTest() {

		Optional<Clinic> clinic = clinicRepo.findByName("Cardiology");

		assertEquals(false, clinic.isPresent());

	}



}
