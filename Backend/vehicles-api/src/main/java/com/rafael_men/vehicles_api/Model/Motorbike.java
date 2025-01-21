package com.rafael_men.vehicles_api.Model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("Moto")
public class Motorbike extends Vehicle{

    private Integer cilindrada;

    public Motorbike() {
    }

    public Motorbike(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Integer getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Motorbike motorbike = (Motorbike) object;
        return Objects.equals(cilindrada, motorbike.cilindrada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cilindrada);
    }
}
