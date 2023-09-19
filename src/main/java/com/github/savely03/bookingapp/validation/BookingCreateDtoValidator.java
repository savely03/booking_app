package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.dto.BookingCreateDto;
import com.github.savely03.bookingapp.service.RoomService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingCreateDtoValidator implements ConstraintValidator<BookingCreateDtoConstraint, BookingCreateDto> {

    private final RoomService roomService;

    @Override
    public boolean isValid(BookingCreateDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return roomService.existsFreeRoomByHotelAndDate(dto.hotelId(), dto.getDateFrom(), dto.getDateTo());
    }
}
