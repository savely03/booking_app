package com.github.savely03.bookingapp.handler;

import com.github.savely03.bookingapp.dto.ErrorResponseDto;
import com.github.savely03.bookingapp.exception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class BaseExceptionHandler {
    private static final String PAGE = "errors/errors-page";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Model model, NotFoundException e) {
        fillModel(model, e.getMessage(), e.getHttpStatus());
        return PAGE;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(Model model, MethodArgumentNotValidException e) {
        String message = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        fillModel(model, message, HttpStatus.BAD_REQUEST);
        return PAGE;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Model model) {
        fillModel(model, "Что-то пошло не так...", HttpStatus.INTERNAL_SERVER_ERROR);
        return PAGE;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException() {
        return "errors/403-page";
    }

    private void fillModel(Model model, String message, HttpStatus httpStatus) {
        model.addAttribute("errors",
                ErrorResponseDto.builder()
                        .message(message)
                        .status(httpStatus)
                        .build());
    }
}

