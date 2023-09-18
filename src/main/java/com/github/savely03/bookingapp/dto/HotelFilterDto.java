package com.github.savely03.bookingapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HotelFilterDto {
    Short stars;
    String city;
    LocalDate dateFrom;
    LocalDate dateTo;
}