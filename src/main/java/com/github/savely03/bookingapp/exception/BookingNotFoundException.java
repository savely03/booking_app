package com.github.savely03.bookingapp.exception;

public class BookingNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Бронирование не найдено";

    public BookingNotFoundException() {
        super(MESSAGE);
    }
}
