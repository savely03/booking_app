package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.exception.HotelNotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.HotelMapper;
import com.github.savely03.bookingapp.repository.HotelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HotelService extends CrudService<Hotel, HotelDto, Long> {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository,
                        HotelNotFoundException notFoundException,
                        HotelMapper hotelMapper) {
        super(hotelRepository, notFoundException, hotelMapper);
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @Transactional
    @Override
    public HotelDto create(HotelDto hotelDto) {
        return super.create(hotelDto);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @Transactional
    @Caching(
            put = {
                    @CachePut(value = "hotels", key = "#id"),
                    @CachePut(value = "hotelsByNameAndCity", key = "{#hotelDto.hotelName, #hotelDto.city}")
            }
    )
    @Override
    public HotelDto update(Long id, HotelDto hotelDto) {
        return super.update(id, hotelDto);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @Cacheable(value = "hotels")
    @Override
    public HotelDto findById(Long id) {
        return super.findById(id);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
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

    @PreAuthorize("hasAuthority('MANAGER')")
    @Cacheable(value = "hotelsByNameAndCity")
    public Optional<HotelDto> findByHotelNameAndCity(String hotelName, String city) {
        return hotelRepository.findByHotelNameAndCity(hotelName, city).map(hotelMapper::toDto);
    }
}
