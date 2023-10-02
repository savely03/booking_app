package com.github.savely03.bookingapp.restcontroller;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingReadDto> create(@RequestBody @Valid BookingCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(dto));
    }

    @GetMapping
    public ResponseEntity<Iterable<BookingReadDto>> findAllByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(bookingService.findAllByUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
