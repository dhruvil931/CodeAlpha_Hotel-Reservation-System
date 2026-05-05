package com.dhruvil.Hotel.Reservation.System.repository;

import com.dhruvil.Hotel.Reservation.System.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomNumber(Long roomNumber);

    @Query("SELECT r FROM Room r WHERE " +
            "(:type IS NULL OR LOWER(r.roomType) = LOWER(:type)) AND " +
            "(:minPrice IS NULL OR r.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR r.price <= :maxPrice)")
    List<Room> searchRoom(@Param("type") String type,
                          @Param("minPrice") Double minPrice,
                          @Param("maxPrice") Double maxPrice
    );
}