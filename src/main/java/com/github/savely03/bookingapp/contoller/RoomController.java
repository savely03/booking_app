package com.github.savely03.bookingapp.contoller;


import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    @PostMapping
    public Room create(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }
}