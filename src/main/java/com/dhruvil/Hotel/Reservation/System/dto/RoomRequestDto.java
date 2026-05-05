package com.dhruvil.Hotel.Reservation.System.dto;

import com.dhruvil.Hotel.Reservation.System.model.type.RoomType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomRequestDto {
    private Long roomNumber;
    private RoomType roomType;
    private BigDecimal price;
    private Long capacity;
}
