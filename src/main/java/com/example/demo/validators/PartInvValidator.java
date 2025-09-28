package com.example.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import com.example.demo.domain.Part;

public class PartInvValidator implements ConstraintValidator<ValidPartInv, Part> {

    @Override
    public void initialize(ValidPartInv constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) return true;

        boolean valid = true;
        context.disableDefaultConstraintViolation(); // disable default message once

        if (part.getInv() < part.getMIN_INV()) {
            context.buildConstraintViolationWithTemplate(
                "Inventory (" + part.getInv() + ") must be at least " + part.getMIN_INV()
            ).addPropertyNode("inv").addConstraintViolation();
            valid = false;
        }

        if (part.getInv() > part.getMAX_INV()) {
            context.buildConstraintViolationWithTemplate(
                "Inventory (" + part.getInv() + ") must not exceed " + part.getMAX_INV()
            ).addPropertyNode("inv").addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
