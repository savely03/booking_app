package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.service.HotelService;
import com.github.savely03.bookingapp.service.RoomService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomValidator implements ConstraintValidator<RoomConstraint, Room> {

    private final HotelService hotelService;
    private final RoomService roomService;

    @Override
    public boolean isValid(Room room, ConstraintValidatorContext constraintValidatorContext) {
        return hotelService.existsById(room.getHotelId()) &&
               roomService.findByHotelIdAndRoomNumber(room.getHotelId(), room.getRoomNumber()).isEmpty();
    }
}
