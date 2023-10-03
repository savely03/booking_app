package com.github.savely03.bookingapp.web.restcontroller;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController extends CrudRestController<Room, RoomDto, Long> {

    private final RoomService roomService;

    public RoomRestController(RoomService roomService) {
        super(roomService);
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> findByHotelId(@RequestParam("hotelId") Long hotelId) {
        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
