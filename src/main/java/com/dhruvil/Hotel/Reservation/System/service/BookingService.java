package com.dhruvil.Hotel.Reservation.System.service;

import com.dhruvil.Hotel.Reservation.System.dto.BookingRequest;
import com.dhruvil.Hotel.Reservation.System.model.Booking;
import com.dhruvil.Hotel.Reservation.System.model.Room;
import com.dhruvil.Hotel.Reservation.System.model.User;
import com.dhruvil.Hotel.Reservation.System.model.type.BookStatus;
import com.dhruvil.Hotel.Reservation.System.model.type.PaymentStatus;
import com.dhruvil.Hotel.Reservation.System.repository.BookingRepository;
import com.dhruvil.Hotel.Reservation.System.repository.RoomRepository;
import com.dhruvil.Hotel.Reservation.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    public final UserRepository userRepository;
    public final RoomRepository roomRepository;

    public Booking createBooking(String username, BookingRequest req) {
        if(req.getCheckOut().isBefore(req.getCheckIn())) {
            throw new RuntimeException("Invalid date range");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(req.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        boolean isBooked = bookingRepository.existsOverlappingBooking(
                room.getId(),
                req.getCheckIn(),
                req.getCheckOut()
        );

        if(isBooked) {
            throw new RuntimeException("Room already booked for these dates");
        }

        long days = ChronoUnit.DAYS.between(req.getCheckIn(), req.getCheckOut());
        if(days <= 0) days = 1;

        BigDecimal totalPrice = room.getPrice()
                .multiply(BigDecimal.valueOf(days));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckIn(req.getCheckIn());
        booking.setCheckOut(req.getCheckOut());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookStatus.BOOKED);

        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking cancelBooking(Long bookingId, String username) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(!booking.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You cannot cancel this booking");
        }

        if(booking.getStatus() == BookStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus(BookStatus.CANCELLED);

        return bookingRepository.save(booking);
    }

    public Booking payBooking(Long bookingId, String username) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(booking.getStatus() == BookStatus.CANCELLED) {
            throw new RuntimeException("Cannot pay cancelled booking");
        }

        if(booking.getPaymentStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Already paid");
        }

        booking.setPaymentStatus(PaymentStatus.PAID);
        booking.setPaidAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
}
