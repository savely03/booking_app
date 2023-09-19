package com.github.savely03.bookingapp.handler;

import com.github.savely03.bookingapp.dto.ErrorResponseDto;
import com.github.savely03.bookingapp.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
    public String handleException(Model model, NotFoundException e) {
        model.addAttribute("errors",
                ErrorResponseDto.builder()
                        .status(e.getHttpStatus())
                        .message(e.getMessage()).build()
        );
        return PAGE;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Model model, MethodArgumentNotValidException e) {
        String message = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        model.addAttribute("errors",
                ErrorResponseDto.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        return PAGE;
    }
}

