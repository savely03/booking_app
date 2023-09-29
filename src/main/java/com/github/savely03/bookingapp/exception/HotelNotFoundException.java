package com.github.savely03.bookingapp.exception;

import org.springframework.stereotype.Component;

@Component
public class HotelNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Отель не найден";
    public HotelNotFoundException() {
        super(MESSAGE);
    }
}
