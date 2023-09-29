package com.github.savely03.bookingapp.exception;

import org.springframework.stereotype.Component;

@Component
public class BookingNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Бронирование не найдено";

    public BookingNotFoundException() {
        super(MESSAGE);
    }
}
