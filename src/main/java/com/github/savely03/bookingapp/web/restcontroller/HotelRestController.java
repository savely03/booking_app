package com.github.savely03.bookingapp.web.restcontroller;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelRestController extends CrudRestController<Hotel, HotelDto, Long> {

    private final HotelService hotelService;

    public HotelRestController(HotelService hotelService) {
        super(hotelService);
        this.hotelService = hotelService;
    }

    @PostMapping("/free")
    public ResponseEntity<Iterable<HotelWithCntRoomsDto>> findAllFreeByFilter(@RequestBody @Valid HotelFilterDto filterDto) {
        return ResponseEntity.ok(hotelService.findAllFreeByFilter(filterDto));
    }

    @GetMapping
    public ResponseEntity<Iterable<HotelWithFullInfoByRoomsDto>> findAllWithFullInfoByRooms(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "stars", required = false) Short stars) {
        return ResponseEntity.ok(hotelService.findAllWithFullInfoByRooms(city, stars));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
