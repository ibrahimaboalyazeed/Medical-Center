package com.global.medical.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "patients")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotNull(message = "Full Name is mandatory")
	private String fullName;

	private String phoneNumber;

	private int age;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id",unique = true)
	private AppUser appUser;

	
}
