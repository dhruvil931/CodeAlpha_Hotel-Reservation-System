package com.dhruvil.Hotel.Reservation.System.controller;

import com.dhruvil.Hotel.Reservation.System.dto.BookingRequest;
import com.dhruvil.Hotel.Reservation.System.model.Booking;
import com.dhruvil.Hotel.Reservation.System.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest req, Principal principal) {
        Booking booking = bookingService.createBooking(
                principal.getName(),
                req
        );

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id, Principal principal) {
        Booking booking = bookingService.cancelBooking(id, principal.getName());

        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<?> payBooking(@PathVariable Long id, Principal principal) {
        Booking booking = bookingService.payBooking(id, principal.getName());

        return ResponseEntity.ok(booking);
    }
}
