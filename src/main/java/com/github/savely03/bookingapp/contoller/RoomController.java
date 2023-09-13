package com.github.savely03.bookingapp.contoller;


import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    @PostMapping
    public Room create(@RequestBody Room room) {
        return roomRepository.create(room);
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}