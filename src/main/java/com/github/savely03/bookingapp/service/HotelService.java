package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.exception.HotelNotFoundException;
import com.github.savely03.bookingapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
    }

    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Iterable<HotelWithCntRoomsDto> findAllFree(HotelFilterDto filter) {
        return hotelRepository.findAllFree(
                filter.getDateFrom(),
                filter.getDateTo(),
                filter.getStars(),
                filter.getCity()
        );
    }
}
