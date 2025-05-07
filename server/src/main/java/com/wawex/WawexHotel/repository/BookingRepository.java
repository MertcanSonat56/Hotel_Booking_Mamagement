package com.wawex.WawexHotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wawex.WawexHotel.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
