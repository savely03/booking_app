package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelValidator implements ConstraintValidator<HotelConstraint, Hotel> {

    private final HotelService hotelService;

    @Override
    public boolean isValid(Hotel hotel, ConstraintValidatorContext constraintValidatorContext) {
        return hotelService.findByHotelNameAndCity(hotel.getHotelName(), hotel.getCity()).isEmpty();
    }
}
