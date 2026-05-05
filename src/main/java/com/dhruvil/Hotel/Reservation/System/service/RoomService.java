package com.dhruvil.Hotel.Reservation.System.service;

import com.dhruvil.Hotel.Reservation.System.dto.RoomRequestDto;
import com.dhruvil.Hotel.Reservation.System.dto.RoomResponseDto;
import com.dhruvil.Hotel.Reservation.System.model.Room;
import com.dhruvil.Hotel.Reservation.System.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    public RoomResponseDto createRoom(RoomRequestDto req) {
        if(roomRepository.existsByRoomNumber(req.getRoomNumber())) {
            throw new RuntimeException("Room already exists");
        }

        Room room = new Room();
        room.setRoomNumber(req.getRoomNumber());
        room.setRoomType(req.getRoomType());
        room.setPrice(req.getPrice());
        room.setCapacity(req.getCapacity());
        room.setCreatedAt(LocalDateTime.now());

        Room saved = roomRepository.save(room);

        return modelMapper.map(saved, RoomResponseDto.class);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with this id: " + id));
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room existing = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with this id: " + id));

        if(updatedRoom.getRoomNumber() != null) {
            existing.setRoomNumber(updatedRoom.getRoomNumber());
        }

        if(updatedRoom.getRoomType() != null) {
            existing.setRoomType(updatedRoom.getRoomType());
        }

        if(updatedRoom.getCapacity() != null) {
            existing.setCapacity(updatedRoom.getCapacity());
        }

        if(updatedRoom.getPrice() != null) {
            existing.setPrice(updatedRoom.getPrice());
        }

        return roomRepository.save(existing);
    }

    public void deleteRoom(Long id) {
        Room existing = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        roomRepository.deleteById(id);
    }

    public List<Room> searchRooms(String type, Double minPrice, Double maxPrice) {
        return roomRepository.searchRoom(type, minPrice, maxPrice);
    }
}
