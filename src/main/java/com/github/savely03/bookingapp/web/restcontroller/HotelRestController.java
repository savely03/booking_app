package com.github.savely03.bookingapp.web.restcontroller;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelRestController extends CrudRestController<Hotel, HotelDto, Long> {

    private final HotelService hotelService;

    public HotelRestController(HotelService hotelService) {
        super(hotelService);
        this.hotelService = hotelService;
    }
}
