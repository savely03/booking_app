package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{id}")
    public BookingReadDto findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings?userId=1"; // хард код
    }

    @GetMapping
    public String findAllByUserId(Model model, @RequestParam Long userId) {
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("bookings", bookingService.findAllByUserId(userId));
        return "bookings/bookings-history";
    }
}
