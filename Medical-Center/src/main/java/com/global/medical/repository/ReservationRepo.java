package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{

}
