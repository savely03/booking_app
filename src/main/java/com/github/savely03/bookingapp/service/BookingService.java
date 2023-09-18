package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.BookingNotFoundException;
import com.github.savely03.bookingapp.exception.HotelNotFoundException;
import com.github.savely03.bookingapp.exception.RoomsAvailableException;
import com.github.savely03.bookingapp.repository.BookingRepository;
import com.github.savely03.bookingapp.repository.HotelRepository;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;


    public BookingReadDto findById(Long id) {
        return bookingRepository.findByIdWithJoins(id).orElseThrow(BookingNotFoundException::new);
    }

    public Iterable<BookingReadDto> findAll() {
        return bookingRepository.findAllWithJoins();
    }

    @Transactional
    public BookingReadDto createBooking(BookingCreateDto dto) {
        if (hotelRepository.existsById(dto.getHotelId())) {
            Room room = roomRepository.findFreeRoomsByHotelAndDate(dto.getHotelId(), dto.getDateFrom(), dto.getDateTo())
                    .orElseThrow(RoomsAvailableException::new);
            Booking booking = bookingRepository.save(Booking.builder()
                    .userId(1L)
                    .dateFrom(dto.getDateFrom())
                    .dateTo(dto.getDateTo())
                    .roomId(room.getId())
                    .build());
            return bookingRepository.findByIdWithJoins(booking.getId()).orElse(null);
        }
        throw new HotelNotFoundException();
    }

    public Iterable<BookingReadDto> findAllByUserId(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteById(Long id) {
        if (bookingRepository.existsByIdAndDateFromIsAfter(id, LocalDate.now())) {
            bookingRepository.deleteById(id);
            return;
        }
        throw new BookingNotFoundException();
    }
}
