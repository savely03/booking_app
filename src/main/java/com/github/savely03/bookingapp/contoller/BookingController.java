package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public String createBooking(Model model, @Valid BookingCreateDto dto) {
        model.addAttribute("booking", bookingService.createBooking(dto));
        return "bookings/bookings-created";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        bookingService.deleteById(id);
        return "redirect:/bookings?username=" + userDetails.getUsername();
    }

    @GetMapping
    public String findAllByUserId(Model model, @RequestParam String username) {
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("bookings", bookingService.findAllByUsername(username));
        return "bookings/bookings-history";
    }
}
