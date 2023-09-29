package com.github.savely03.bookingapp.validation.annotation;

import com.github.savely03.bookingapp.validation.DatesInfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DatesInfoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DatesInfoConstraint {
    String message() default "Проверьте правильность заполненных дат";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
