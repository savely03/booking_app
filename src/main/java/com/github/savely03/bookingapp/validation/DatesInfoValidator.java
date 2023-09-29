package com.github.savely03.bookingapp.validation;

import com.github.savely03.bookingapp.dto.DatesInfo;
import com.github.savely03.bookingapp.validation.annotation.DatesInfoConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatesInfoValidator implements ConstraintValidator<DatesInfoConstraint, DatesInfo> {
    @Override
    public boolean isValid(DatesInfo datesInfo, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.now();
        return datesInfo.getDateFrom() != null &&
               datesInfo.getDateTo() != null &&
               (datesInfo.getDateFrom().isEqual(currentDate) || datesInfo.getDateFrom().isAfter(currentDate)) &&
               datesInfo.getDateFrom().isBefore(datesInfo.getDateTo());
    }
}
