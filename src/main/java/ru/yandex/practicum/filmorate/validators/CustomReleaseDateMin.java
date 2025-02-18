package ru.yandex.practicum.filmorate.validators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ru.yandex.practicum.filmorate.validators.ReleaseDateValidator.class)
public @interface CustomReleaseDateMin {
    String message() default "дата релиза — менее заданной";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}