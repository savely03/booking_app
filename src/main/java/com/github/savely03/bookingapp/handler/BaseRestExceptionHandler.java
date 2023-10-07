package com.github.savely03.bookingapp.handler;

import com.github.savely03.bookingapp.dto.ErrorResponseDto;
import com.github.savely03.bookingapp.exception.NotFoundException;
import com.github.savely03.bookingapp.exception.RoomNotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class BaseRestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e) {
        logException(e);
        return buildResponse(e.getHttpStatus(), e.getMessage());
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(RoomNotAvailableException e) {
        logException(e);
        return buildResponse(e.getHttpStatus(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException e) {
        logException(e);
        String message = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        logException(e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Что-то пошло не так...");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        logException(e);
        return buildResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    private void logException(Exception e) {
        log.error(String.format("%s - %s", e.getClass().getName(), e.getMessage()), e);
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus).body(
                ErrorResponseDto.builder()
                        .status(httpStatus)
                        .message(message)
                        .build()
        );
    }
}
