package com.github.savely03.bookingapp.exception;

public class RoomNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Номер не найден";

    public RoomNotFoundException() {
        super(MESSAGE);
    }
}
