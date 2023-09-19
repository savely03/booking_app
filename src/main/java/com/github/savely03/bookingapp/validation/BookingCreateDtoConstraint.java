package com.github.savely03.bookingapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookingCreateDtoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BookingCreateDtoConstraint {
    String message() default "Бронирование не может быть создано";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
