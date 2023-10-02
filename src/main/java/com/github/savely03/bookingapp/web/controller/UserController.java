package com.github.savely03.bookingapp.web.controller;

import com.github.savely03.bookingapp.dto.UserDto;
import com.github.savely03.bookingapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String create(@Valid UserDto dto) {
        UserDto createdUser = userService.create(dto);
        return "redirect:/users/" + createdUser.id();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid UserDto dto) {
        UserDto updatedUser = userService.update(id, dto);
        return "redirect:/users/" + updatedUser.id();
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        UserDto user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
