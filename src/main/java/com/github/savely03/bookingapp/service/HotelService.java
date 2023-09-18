package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
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

    @Transactional
    public void update(Long id, Hotel hotel) {
        if (hotelRepository.existsById(id)) {
            hotel.setId(id);
            hotelRepository.save(hotel);
            return;
        }
        throw new HotelNotFoundException();
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
    }

    public Iterable<HotelWithFullInfoByRoomsDto> findAllWithFullInfoByRooms(String city, Short stars) {
        if (city != null && stars != null) {
            return hotelRepository.findAllWithFullInfoByRooms(city, stars);
        }
        return hotelRepository.findAllWithFullInfoByRooms();
    }

    public Iterable<HotelWithCntRoomsDto> findAllFreeByFilter(HotelFilterDto filter) {
        return hotelRepository.findAllFreeHotelsByFilter(
                filter.getDateFrom(),
                filter.getDateTo(),
                filter.getStars(),
                filter.getCity()
        );
    }
}
