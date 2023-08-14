package com.global.medical.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_specialty_id")
    private MedicalSpecialty medicalSpecialty;
	
    private LocalDate reservationDate; // Date without time
    private LocalTime reservationTime; // Time without date

    private String status;

    // Constructors, getters, setters, and other methods

    // Create a method to set the reservation date and time together
    public void setReservationDateTime(LocalDate date, LocalTime time) {
        this.reservationDate = date;
        this.reservationTime = time;
    }

    // Create a method to get the formatted reservation date and time
    public String getFormattedReservationDateTime() {
        String dayOfWeek = reservationDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String formattedTime = reservationTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "Date: " + reservationDate + ", Time: " + dayOfWeek + " " + formattedTime;
    }
	

}
