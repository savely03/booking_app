package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.validation.annotation.DatesInfoConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

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
