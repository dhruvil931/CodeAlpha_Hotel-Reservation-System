package com.dhruvil.Hotel.Reservation.System.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
