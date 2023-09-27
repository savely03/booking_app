package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.BookingNotFoundException;
import com.github.savely03.bookingapp.exception.HotelNotFoundException;
import com.github.savely03.bookingapp.exception.RoomAvailableException;
import com.github.savely03.bookingapp.repository.BookingRepository;
import com.github.savely03.bookingapp.repository.HotelRepository;
import com.github.savely03.bookingapp.repository.RoomRepository;
import com.github.savely03.bookingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    @Transactional
    @CacheEvict(value = "bookings", key = "@authenticationProvider.getAuthentication().getName()")
    public BookingReadDto createBooking(BookingCreateDto dto) {
        if (hotelRepository.existsById(dto.hotelId())) {
            Room room = roomRepository.findFreeRoomByHotelAndDate(dto.hotelId(), dto.getDateFrom(), dto.getDateTo())
                    .orElseThrow(RoomAvailableException::new);
            Booking booking = bookingRepository.save(
                    Booking.builder()
                            .userId(userRepository.findByUsername(dto.username())
                                    .orElseThrow(() -> new UsernameNotFoundException(dto.username()))
                                    .getId())
                            .dateFrom(dto.getDateFrom())
                            .dateTo(dto.getDateTo())
                            .roomId(room.getId())
                            .build()
            );
            return bookingRepository.findByIdWithJoins(booking.getId()).orElse(null);
        }
        throw new HotelNotFoundException();
    }

    @Cacheable(value = "bookings")
    public Iterable<BookingReadDto> findAllByUsername(String username) {
        return bookingRepository.findAllByUsernameWithJoins(username);
    }

    @Transactional
    @CacheEvict(value = "bookings", key = "@authenticationProvider.getAuthentication().getName()")
    public void deleteById(Long id) {
        if (bookingRepository.existsByIdAndDateFromIsAfter(id, LocalDate.now())) {
            bookingRepository.deleteById(id);
            return;
        }
        throw new BookingNotFoundException();
    }
}
