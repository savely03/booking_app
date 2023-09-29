package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.validation.annotation.HotelConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@HotelConstraint
public record HotelDto(Long id,
                       @NotBlank(message = "Название отеля не должно быть пустым")
                       String hotelName,
                       @NotNull(message = "Звезды не должны быть пустыми")
                       @Min(value = 1, message = "Минимальное кол-во звезд - 1")
                       @Max(value = 5, message = "Максимальное кол-во звезд - 5")
                       Short stars,
                       @NotBlank(message = "Город не должен быть пустым")
                       String city) {
}
