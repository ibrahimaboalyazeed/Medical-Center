package com.global.medical.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Reservation;
import com.global.medical.enums.Shift;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{

//	@Query(value = "SELECT * FROM `reservations` WHERE reservation_date=:#{#reservation.getReservationDate()} "
//			+ "AND reservation_time=:#{#reservation.getReservationTime()}"
//			+ "AND shift=:#{#reservation.getShift()}"
//			+ "AND clinic_id=:#{#reservation.getClinic().getId()} "
//			+ "AND doctor_id=:#{#reservation.getDoctor().getId()} "
//			+ "AND patient_id=:#{#reservation.getPatient().getId()}" , nativeQuery = true)
//	Reservation findReservation(@Param("reservation")Reservation reservation);
	
	
//	@Query(value = "SELECT * FROM `reservations` WHERE reservation_date = :#{#reservation.getReservationDate()} "
//	        + "AND reservation_time = :#{#reservation.getReservationTime()} "
//	        + "AND shift = :#{#reservation.getShift()} "
//	        + "AND clinic_id = :#{#reservation.getClinic().getId()} "
//	        + "AND doctor_id = :#{#reservation.getDoctor().getId()} "
//	        + "AND patient_id = :#{#reservation.getPatient().getId()}", nativeQuery = true)
//	Reservation findReservation(@Param("reservation") Reservation reservation);
	

	    Reservation findByReservationDateAndReservationTimeAndShiftAndClinicIdAndDoctorIdAndPatientId(
	        LocalDate reservationDate, LocalTime reservationTime, Shift shift, Long clinicId, Long doctorId, Long patientId);
	



}
