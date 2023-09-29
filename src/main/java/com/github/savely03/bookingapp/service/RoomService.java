package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.RoomNotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.RoomMapper;
import com.github.savely03.bookingapp.repository.RoomRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService extends CrudService<Room, RoomDto, Long> {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomNotFoundException notFoundException, RoomMapper roomMapper) {
        super(roomRepository, notFoundException, roomMapper);
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Transactional
    @CacheEvict(value = "roomsByHotelId", key = "#roomDto.hotelId")
    @Override
    public RoomDto create(RoomDto roomDto) {
        return super.create(roomDto);
    }

    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "rooms", key = "#id"),
                    @CachePut(value = "roomsByHotelIdAndRoomNumber", key = "{#roomDto.hotelId, #roomDto.roomNumber}")
            },
            evict = @CacheEvict(value = "roomsByHotelId", key = "#roomDto.hotelId")
    )
    @Override
    public RoomDto update(Long id, RoomDto roomDto) {
        return super.update(id, roomDto);
    }

    @Cacheable(value = "rooms")
    @Override
    public RoomDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return super.existsById(id);
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
}
