package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.service.HotelService;
import com.github.savely03.bookingapp.validation.annotation.HotelConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelValidator implements ConstraintValidator<HotelConstraint, HotelDto> {

    private final HotelService hotelService;

    @Override
    public boolean isValid(HotelDto hotel, ConstraintValidatorContext constraintValidatorContext) {
        if (hotel.id() != null && hotelService.existsById(hotel.id())) {
            HotelDto foundHotel = hotelService.findById(hotel.id());
            if (hotel.hotelName().equals(foundHotel.hotelName()) &&
                hotel.city().equals(foundHotel.city())) {
                return true;
            }
        }
        return hotelService.findByHotelNameAndCity(hotel.hotelName(), hotel.city()).isEmpty();
    }
}
