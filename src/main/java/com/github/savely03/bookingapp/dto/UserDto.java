package com.github.savely03.bookingapp.dto;

import com.github.savely03.bookingapp.security.Role;
import com.github.savely03.bookingapp.validation.annotation.UserConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@UserConstraint
public record UserDto(Long id,
                      @NotBlank(message = "Имя пользователя не должно быть пустым")
                      String username,
                      @NotBlank(message = "Пароль не должен быть пустым")
                      String password,
                      @NotBlank(message = "Email не должен быть пустым")
                      String email,
                      @NotNull(message = "Роль должна быть заполнена")
                      Role role) {
}
