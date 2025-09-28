package com.example.demo.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = {PartInvValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPartInv {
    String message() default "Quantity must be at least the minimum inventory.";
    Class<?> [] groups() default {};
    Class<?> [] payload() default {};
}
