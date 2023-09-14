package com.github.savely03.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HotelFilterDto {
    Short stars;
    String city;
    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDate dateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDate dateTo;
}