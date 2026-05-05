package com.dhruvil.Hotel.Reservation.System.repository;

import com.dhruvil.Hotel.Reservation.System.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.room.id = :roomId
        AND b.checkOut > :checkIn
        AND b.checkIn < :checkOut
    """)
    boolean existsOverlappingBooking(Long roomId, LocalDateTime checkIn, LocalDateTime checkOut);
}