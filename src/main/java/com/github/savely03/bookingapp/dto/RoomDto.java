package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.validation.annotation.RoomConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@RoomConstraint
public record RoomDto(Long id,
                      @NotNull(message = "Отель не должен быть пустым")
                      Long hotelId,
                      @NotNull(message = "Номер не должен быть пустым")
                      @Min(value = 1, message = "Минмальное значение номера - 1")
                      Short roomNumber,
                      @NotNull(message = "Этаж не должен быть пустым")
                      Short roomFloor) {
}
