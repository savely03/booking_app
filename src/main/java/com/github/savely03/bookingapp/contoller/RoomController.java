package com.github.savely03.bookingapp.contoller;


import com.github.savely03.bookingapp.dto.RoomDto;
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
    public String create(@Valid RoomDto roomDto) {
        RoomDto createdRoom = roomService.create(roomDto);
        return "redirect:/rooms/" + createdRoom.id();
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String update(@PathVariable Long id, @Valid RoomDto roomDto) {
        roomService.update(id, roomDto);
        return "redirect:/rooms/" + id;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String findById(Model model, @PathVariable Long id) {
        RoomDto room = roomService.findById(id);
        model.addAttribute("room", room);
        return "rooms/room";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public String findByHotelId(Model model, @RequestParam("hotelId") Long hotelId) {
        List<RoomDto> rooms = roomService.findByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        return "/rooms/rooms";
    }
}