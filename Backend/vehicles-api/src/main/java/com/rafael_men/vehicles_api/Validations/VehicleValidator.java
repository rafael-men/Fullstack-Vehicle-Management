package com.rafael_men.vehicles_api.Validations;

import com.rafael_men.vehicles_api.Model.Car;
import com.rafael_men.vehicles_api.Model.Motorbike;
import com.rafael_men.vehicles_api.Model.Vehicle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VehicleValidator implements ConstraintValidator<ValidVehicle, Vehicle> {

    @Override
    public void initialize(ValidVehicle constraintAnnotation) {
    }

    @Override
    public boolean isValid(Vehicle vehicle, ConstraintValidatorContext context) {
        if (vehicle == null) {
            return false;
        }

        // Validação para Carro
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            if (car.getQuantidadePortas() == null || car.getTipoCombustivel() == null) {
                return false;
            }
            return true;
        }

        // Validação para Moto
        if (vehicle instanceof Motorbike) {
            Motorbike motorbike = (Motorbike) vehicle;
            if (motorbike.getCilindrada() == null) {
                return false;
            }

            return true;
        }


        return true;
    }
}