package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.validation.BookingCreateDtoConstraint;
import com.github.savely03.bookingapp.validation.DatesInfoConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@BookingCreateDtoConstraint
@DatesInfoConstraint
@Builder
public record BookingCreateDto(@NotNull(message = "Отель - обязательное поле") Long hotelId,
                               LocalDate dateFrom,
                               LocalDate dateTo) implements DatesInfo {
    @Override
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    @Override
    public LocalDate getDateTo() {
        return dateTo;
    }
}
