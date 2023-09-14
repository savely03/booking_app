package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @GetMapping
    public Iterable<Hotel> findAll() {
        return hotelService.findAll();
    }

    @GetMapping("/free")
    public Iterable<HotelWithCntRoomsDto> findAllFree(@RequestBody HotelFilterDto filter) {
        return hotelService.findAllFree(filter);
    }
}
