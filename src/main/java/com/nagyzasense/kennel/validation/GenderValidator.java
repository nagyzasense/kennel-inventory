package com.nagyzasense.kennel.validation;

import java.util.Arrays;

import com.nagyzasense.kennel.model.Gender;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(Gender.values()).anyMatch(gender -> gender.name().equals(value));
    }
}
