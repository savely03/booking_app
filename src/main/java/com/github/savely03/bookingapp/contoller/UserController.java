package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.entity.Users;
import com.github.savely03.bookingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public Users create(@RequestBody Users user) {
        return userRepository.create(user);
    }


    @GetMapping
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/exists/{id}")
    public Boolean exists(@PathVariable Long id) {
        return userRepository.exists(id);
    }
}
