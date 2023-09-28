package com.github.savely03.bookingapp.contoller;


import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String create(@Valid Room room) {
        Room createdRoom = roomService.create(room);
        return "redirect:/rooms/" + createdRoom.getId();
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String update(@PathVariable Long id, @Valid Room room) {
        roomService.update(id, room);
        return "redirect:/rooms/" + id;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String findById(Model model, @PathVariable Long id) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "rooms/room";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public String findByHotelId(Model model, @RequestParam("hotelId") Long hotelId) {
        List<Room> rooms = roomService.findByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        return "/rooms/rooms";
    }
}