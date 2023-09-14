package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.exception.BookingNotFoundException;
import com.github.savely03.bookingapp.mapper.BookingMapper;
import com.github.savely03.bookingapp.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto save(BookingDto bookingDto) {
        Booking savedBooking = bookingRepository.save(bookingMapper.toEntity(bookingDto));
        return bookingRepository.findByIdWithJoins(savedBooking.getId()).orElse(null);
    }

    public BookingDto findById(Long id) {
        return bookingRepository.findByIdWithJoins(id).orElseThrow(BookingNotFoundException::new);
    }

    public Iterable<BookingDto> findAll() {
        return bookingRepository.findAllWithJoins();
    }
}
