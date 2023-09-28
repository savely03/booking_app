package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.RoomNotFoundException;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    @CacheEvict(value = "roomsByHotelId", key = "#room.hotelId")
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "rooms", key = "#id"),
                    @CachePut(value = "roomsByHotelIdAndRoomNumber", key = "{#room.hotelId, #room.roomNumber}")
            },
            evict = @CacheEvict(value = "roomsByHotelId", key = "#room.hotelId")
    )
    public Room update(Long id, Room room) {
        if (roomRepository.existsById(id)) {
            room.setId(id);
            roomRepository.save(room);
            return room;
        }
        throw new RoomNotFoundException();
    }

    @Cacheable(value = "rooms")
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }

    @Cacheable(value = "roomsByHotelIdAndRoomNumber")
    public Optional<Room> findByHotelIdAndRoomNumber(Long hotelId, Short roomNumber) {
        return roomRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber);
    }

    @Cacheable(value = "roomsByHotelId")
    public List<Room> findByHotelId(Long hotelId) {
        return roomRepository.findByHotelIdOrderByRoomNumber(hotelId);
    }

    public boolean existsById(Long id) {
        return roomRepository.existsById(id);
    }
}
