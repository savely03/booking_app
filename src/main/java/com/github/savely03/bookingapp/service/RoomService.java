package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.RoomNotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.RoomMapper;
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
    private final RoomMapper roomMapper;

    @Transactional
    @CacheEvict(value = "roomsByHotelId", key = "#roomDto.hotelId")
    public RoomDto create(RoomDto roomDto) {
        return roomMapper.toDto(roomRepository.save(roomMapper.toEntity(roomDto)));
    }

    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "rooms", key = "#id"),
                    @CachePut(value = "roomsByHotelIdAndRoomNumber", key = "{#roomDto.hotelId, #roomDto.roomNumber}")
            },
            evict = @CacheEvict(value = "roomsByHotelId", key = "#roomDto.hotelId")
    )
    public RoomDto update(Long id, RoomDto roomDto) {
        if (roomRepository.existsById(id)) {
            Room room = roomMapper.toEntity(roomDto);
            room.setId(id);
            roomRepository.save(room);
            return roomDto;
        }
        throw new RoomNotFoundException();
    }

    @Cacheable(value = "rooms")
    public RoomDto findById(Long id) {
        return roomMapper.toDto(roomRepository.findById(id).orElseThrow(RoomNotFoundException::new));
    }

    @Cacheable(value = "roomsByHotelIdAndRoomNumber")
    public Optional<RoomDto> findByHotelIdAndRoomNumber(Long hotelId, Short roomNumber) {
        return roomRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber).map(roomMapper::toDto);
    }

    @Cacheable(value = "roomsByHotelId")
    public List<RoomDto> findByHotelId(Long hotelId) {
        return roomRepository.findByHotelIdOrderByRoomNumber(hotelId).stream()
                .map(roomMapper::toDto)
                .toList();
    }

    public boolean existsById(Long id) {
        return roomRepository.existsById(id);
    }
}
