package com.github.savely03.bookingapp.restcontroller;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController extends CrudController<Room, RoomDto, Long> {

    private final RoomService roomService;

    public RoomRestController(RoomService roomService) {
        super(roomService);
        this.roomService = roomService;
    }
}
