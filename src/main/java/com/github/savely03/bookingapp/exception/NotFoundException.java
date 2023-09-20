package com.github.savely03.bookingapp.exception;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    protected NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
