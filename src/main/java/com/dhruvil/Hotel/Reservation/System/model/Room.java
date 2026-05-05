package com.dhruvil.Hotel.Reservation.System.model;

import com.dhruvil.Hotel.Reservation.System.model.type.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private BigDecimal price;

    private Long capacity;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
