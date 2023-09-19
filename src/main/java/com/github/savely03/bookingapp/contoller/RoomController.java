package com.github.savely03.bookingapp.contoller;


import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public String create(@Valid Room room) {
        Room createdRoom = roomService.create(room);
        return "redirect:/rooms/" + createdRoom.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid Room room) {
        roomService.update(id, room);
        return "redirect:/rooms/" + id;
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "rooms/room";
    }
}