package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingReadDto {
    private Long id;
    private Room room;
    private Users user;
    private Hotel hotel;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
