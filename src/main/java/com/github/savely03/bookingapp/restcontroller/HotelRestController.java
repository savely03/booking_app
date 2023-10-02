package com.github.savely03.bookingapp.restcontroller;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelRestController extends CrudController<Hotel, HotelDto, Long> {

    private final HotelService hotelService;

    public HotelRestController(HotelService hotelService) {
        super(hotelService);
        this.hotelService = hotelService;
    }

    @Override
    public ResponseEntity<HotelDto> create(HotelDto dto) {
        return super.create(dto);
    }
}
