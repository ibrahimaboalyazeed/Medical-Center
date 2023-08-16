package com.global.medical.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotNull(message = "Full Name is mandatory")
	private String fullName;

	private String phoneNumber;

	 @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "clinic_id")
	 private  Clinic clinic;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private AppUser appUser;

}
