package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.validation.annotation.DatesInfoConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;


@DatesInfoConstraint
@Builder
public record HotelFilterDto(@NotNull(message = "Звезды - обязательное поле") Short stars,
                             @NotBlank(message = "Поле город не должно быть пустым") String city,
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