package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelRepository hotelRepository;

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelRepository.create(hotel);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @GetMapping("/exists/{id}")
    public Boolean exists(@PathVariable Long id) {
        return hotelRepository.exists(id);
    }

    @GetMapping
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
}
