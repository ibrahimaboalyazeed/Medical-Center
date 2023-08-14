package com.global.medical.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "medical_specialty")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalSpecialty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private String name;
	
	private String description;
	
	private String examinationPeriod;
	
	private Double examinationPrice;

}
