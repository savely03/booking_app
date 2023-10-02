package com.github.savely03.bookingapp.web.restcontroller;

import com.github.savely03.bookingapp.dto.UserDto;
import com.github.savely03.bookingapp.entity.Users;
import com.github.savely03.bookingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController extends CrudRestController<Users, UserDto, Long> {

    private final UserService userService;

    public UserRestController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
