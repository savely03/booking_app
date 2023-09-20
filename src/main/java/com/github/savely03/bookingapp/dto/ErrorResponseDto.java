package com.github.savely03.bookingapp.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponseDto(HttpStatus status,
                               String message) {

}
