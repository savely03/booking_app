package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.exception.BookingNotFoundException;
import com.github.savely03.bookingapp.mapper.BookingMapper;
import com.github.savely03.bookingapp.repository.BookingRepository;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto create(BookingDto bookingDto) {
        return bookingMapper.toDto(bookingRepository.create(bookingMapper.toEntity(bookingDto)));
    }

    public BookingDto findById(Long id) {
        return bookingRepository.findById(id).map(bookingMapper::toDto).orElseThrow(BookingNotFoundException::new);
    }

    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream().map(bookingMapper::toDto).toList();
    }
}
