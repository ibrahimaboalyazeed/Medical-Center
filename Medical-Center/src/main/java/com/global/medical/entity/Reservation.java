package com.global.medical.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.global.medical.enums.Status;
import com.global.medical.error.InvalidStatusInputException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reservations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private LocalDate reservationDate;

  
    private LocalTime reservationTime;

    //@Enumerated(EnumType.STRING) // Store the DayOfWeek enum as a string in the database
    //private ReservationDay reservationDay; // Store the day as an enum value
    
    @Transient
    private DayOfWeek reservationDay;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    
    
    public void setReservationTime(LocalTime reservationTime) {
        if (reservationTime != null) {
            int minutes = reservationTime.getMinute();
            if (minutes != 0 && minutes != 30) {
                throw new HttpMessageNotReadableException("Reservation time must be in increments of 30 minutes.");
            }
        }
        this.reservationTime = reservationTime;
    }
    @PostLoad
    public void calcReservationDay()
    {
    	  String dayOfWeek = reservationDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
    }
    
    public void setStatus(Status status) {
    	
        if (status == null) {
            throw new InvalidStatusInputException("Status cannot be null");
        } else if (!isValidStatus(status)) {
            throw new InvalidStatusInputException("Invalid status provided: " + status);
        }

        this.status = status;
    }

    private boolean isValidStatus(Status status) {
        for (Status validStatus : Status.values()) {
            if (validStatus == status) {
                return true;
            }
        }
        return false;
    }
    



}