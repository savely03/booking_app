package com.github.savely03.bookingapp.exception;

import org.springframework.http.HttpStatus;


public class RoomNotAvailableException extends BaseException {
    private static final String MESSAGE = "Номер/номера недоступны";

    public RoomNotAvailableException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
