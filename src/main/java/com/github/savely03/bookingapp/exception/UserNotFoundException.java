package com.github.savely03.bookingapp.exception;

import org.springframework.stereotype.Component;

@Component
public class UserNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Пользователь не найден";

    protected UserNotFoundException() {
        super(MESSAGE);
    }
}
