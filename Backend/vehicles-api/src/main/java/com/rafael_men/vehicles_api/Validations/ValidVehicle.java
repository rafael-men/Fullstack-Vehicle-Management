package com.rafael_men.vehicles_api.Validations;


import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Definindo a anotação do validador
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VehicleValidator.class)
public @interface ValidVehicle {

    String message() default "Campo inválido para este tipo de veículo";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
