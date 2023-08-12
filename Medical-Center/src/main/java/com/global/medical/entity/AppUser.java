package com.global.medical.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AppUser {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull(message ="password is mandatory")
	private String password ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles" ,
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OrderColumn(name = "id")
	private Set<Role> roles = new HashSet<>();
	
	@Value("1")
	private boolean isEnabled ;	
	
	@CreatedBy
	private String createdBy;
	@CreatedDate
	private LocalDateTime createdDateTime;
	
	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	
	public AppUser(Long id) {
		super();
		this.id = id;
	}



	public AppUser() {
		super();
		this.isEnabled = true;

	}

}
