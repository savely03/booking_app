package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import lombok.*;

import java.time.LocalDate;


@Builder
public record BookingReadDto(Long id,
                             Room room,
                             Users user,
                             Hotel hotel,
                             LocalDate dateFrom,
                             LocalDate dateTo) {
}
