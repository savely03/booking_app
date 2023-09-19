package com.github.savely03.bookingapp.exception;

public class HotelNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Отель не найден";
    public HotelNotFoundException() {
        super(MESSAGE);
    }
}
