package com.github.savely03.bookingapp.validation.annotation;

import com.github.savely03.bookingapp.validation.RoomValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoomValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RoomConstraint {
    String message() default "Номер не может быть добавлен/изменен";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

