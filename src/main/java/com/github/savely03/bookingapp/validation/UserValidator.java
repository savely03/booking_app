package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.dto.UserDto;
import com.github.savely03.bookingapp.service.UserService;
import com.github.savely03.bookingapp.validation.annotation.UserConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UserConstraint, UserDto> {

    private final UserService userService;

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsByUsernameOrEmail(user.username(), user.email(), user.id());
    }
}
