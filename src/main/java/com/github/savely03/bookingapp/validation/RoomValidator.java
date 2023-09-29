package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.service.HotelService;
import com.github.savely03.bookingapp.service.RoomService;
import com.github.savely03.bookingapp.validation.annotation.RoomConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomValidator implements ConstraintValidator<RoomConstraint, RoomDto> {

    private final HotelService hotelService;
    private final RoomService roomService;

    @Override
    public boolean isValid(RoomDto room, ConstraintValidatorContext constraintValidatorContext) {
        if (room.id() != null && roomService.existsById(room.id())) {
            RoomDto foundRoom = roomService.findById(room.id());
            if (room.hotelId().equals(foundRoom.id()) &&
                room.roomNumber().equals(foundRoom.roomNumber())) {
                return true;
            }
        }
        return hotelService.existsById(room.hotelId()) &&
               roomService.findByHotelIdAndRoomNumber(room.hotelId(), room.roomNumber()).isEmpty();
    }
}
