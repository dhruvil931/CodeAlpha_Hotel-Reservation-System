package com.dhruvil.Hotel.Reservation.System.controller;

import com.dhruvil.Hotel.Reservation.System.dto.RoomRequestDto;
import com.dhruvil.Hotel.Reservation.System.model.Room;
import com.dhruvil.Hotel.Reservation.System.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody RoomRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(req));
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();

        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);

        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        Room updated = roomService.updateRoom(id, updatedRoom);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Room deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchRooms(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<Room> rooms = roomService.searchRooms(type, minPrice, maxPrice);

        return ResponseEntity.ok(rooms);
    }
}
