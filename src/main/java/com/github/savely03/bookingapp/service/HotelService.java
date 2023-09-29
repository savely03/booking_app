package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.exception.HotelNotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.HotelMapper;
import com.github.savely03.bookingapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Transactional
    @Caching(
            evict = @CacheEvict(value = "hotelsByCityAndStars", allEntries = true)
    )
    public HotelDto create(HotelDto hotelDto) {
        return hotelMapper.toDto(hotelRepository.save(hotelMapper.toEntity(hotelDto)));
    }

    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "hotels", key = "#id"),
                    @CachePut(value = "hotelsByNameAndCity", key = "{#hotelDto.hotelName, #hotelDto.city}")
            }
    )
    public HotelDto update(Long id, HotelDto hotelDto) {
        if (hotelRepository.existsById(id)) {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            hotel.setId(id);
            hotelRepository.save(hotel);
            return hotelDto;
        }
        throw new HotelNotFoundException();
    }

    @Cacheable(value = "hotels")
    public HotelDto findById(Long id) {
        return hotelRepository.findById(id).map(hotelMapper::toDto).orElseThrow(HotelNotFoundException::new);
    }


    public Iterable<HotelWithFullInfoByRoomsDto> findAllWithFullInfoByRooms(String city, Short stars) {
        if (StringUtils.isNotBlank(city) && stars != null) {
            return hotelRepository.findAllWithFullInfoByRooms(city, stars);
        }
        return hotelRepository.findAllWithFullInfoByRooms();
    }

    public Iterable<HotelWithCntRoomsDto> findAllFreeByFilter(HotelFilterDto filter) {
        return hotelRepository.findAllFreeHotelsByFilter(
                filter.getDateFrom(),
                filter.getDateTo(),
                filter.stars(),
                filter.city()
        );
    }

    public boolean existsById(Long id) {
        return hotelRepository.existsById(id);
    }

    @Cacheable(value = "hotelsByNameAndCity")
    public Optional<HotelDto> findByHotelNameAndCity(String hotelName, String city) {
        return hotelRepository.findByHotelNameAndCity(hotelName, city).map(hotelMapper::toDto);
    }
}
