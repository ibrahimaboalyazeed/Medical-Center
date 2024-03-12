package com.global.medical.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.global.medical.enums.Shift;
import com.global.medical.enums.Status;
import com.global.medical.error.CustomException;
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    
    @Enumerated(EnumType.STRING)          // Store the DayOfWeek enum as a string in the database
    private DayOfWeek reservationDay;     // Store the day as an enum value
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    
    @Enumerated(EnumType.STRING)
    private Shift shift;
    
    
    
    
    
    public void setReservationTime(LocalTime reservationTime) {
        if (reservationTime != null) {
            int minutes = reservationTime.getMinute();
            if (minutes != 0 && minutes != 30) {
                throw new HttpMessageNotReadableException("Reservation time must be in increments of 30 minutes.");
            }
        }
        this.reservationTime = reservationTime;
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

    public void setShift() {
        if (reservationTime != null) {
            LocalTime morningStartTime = LocalTime.parse("08:59:00");
            LocalTime morningEndTime = LocalTime.parse("14:59:00");
            LocalTime eveningStartTime = LocalTime.parse("14:59:00");
            LocalTime eveningEndTime = LocalTime.parse("20:59:00");

            if (reservationTime.isAfter(morningStartTime) && reservationTime.isBefore(morningEndTime)) {
                this.shift = Shift.MORNING;
            } else if (reservationTime.isAfter(eveningStartTime) && reservationTime.isBefore(eveningEndTime)) {
                this.shift = Shift.EVENING;
            } else {
            	 throw new CustomException("Cannot set reservation for this shift at the current time.");
                 }
        }
    }
    



}