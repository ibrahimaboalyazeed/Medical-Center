package com.global.medical.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.global.medical.entity.Clinic;
import com.global.medical.entity.Doctor;
import com.global.medical.entity.Reservation;
import com.global.medical.enums.Shift;


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
	    
	   // List<LocalTime> findReservationTimeByClinicAndReservationDate(Clinic clinic, LocalDate reservationDate);
	    
	    
	     //Define your custom query method here
	    @Query("SELECT r.reservationTime FROM Reservation r WHERE r.clinic = :clinic AND r.doctor = :doctor AND r.reservationDate = :reservationDate")
	    List<LocalTime> findReservationTimeByClinicAndReservationDate(@Param("clinic") Clinic clinic, @Param("doctor") Doctor doctor,@Param("reservationDate") LocalDate reservationDate );

	    Reservation findByDoctorIdAndReservationDateAndReservationTime(Long id, LocalDate reservationDate, LocalTime reservationTime);

		Reservation findByPatientIdAndReservationDateAndReservationTime(Long id, LocalDate reservationDate,LocalTime reservationTime);

		Reservation findByPatientIdAndReservationDateAndClinicId(Long patientId, LocalDate reservationDate,Long clinicId);

		List<Reservation> findByDoctorId(Long id);

		List<Reservation> findByPatientId(Long id);
	



}
