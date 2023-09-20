package com.github.savely03.bookingapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HotelValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HotelConstraint {
    String message() default "Отель не может быть добавлен/изменен";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
