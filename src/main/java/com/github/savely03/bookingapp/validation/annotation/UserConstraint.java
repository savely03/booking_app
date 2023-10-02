package com.github.savely03.bookingapp.validation.annotation;

import com.github.savely03.bookingapp.validation.UserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UserConstraint {
    String message() default "Пользователь не может быть добавлен/изменен";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
