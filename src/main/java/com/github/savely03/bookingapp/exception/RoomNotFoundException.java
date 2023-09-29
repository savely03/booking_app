package com.github.savely03.bookingapp.exception;

import org.springframework.stereotype.Component;

@Component
public class RoomNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Номер не найден";

    public RoomNotFoundException() {
        super(MESSAGE);
    }
}
