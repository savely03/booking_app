package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestBody BookingDto booking) {
        return bookingService.save(booking);
    }

    @GetMapping("/{id}")
    public BookingDto findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @GetMapping
    public Iterable<BookingDto> findAll() {
        return bookingService.findAll();
    }
}
