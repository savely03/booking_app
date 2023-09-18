package com.github.savely03.bookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingCreateDto {
    private Long hotelId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
