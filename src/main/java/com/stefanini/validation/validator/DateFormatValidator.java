package com.stefanini.validation.validator;

import com.stefanini.validation.constraints.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    private String pattern;

    @Override
    public void initialize(DateFormat constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if (value == null) {
            return true;
        }
        try {
            var formatter = DateTimeFormatter.ofPattern(pattern);
            formatter.parse(value);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }
}
