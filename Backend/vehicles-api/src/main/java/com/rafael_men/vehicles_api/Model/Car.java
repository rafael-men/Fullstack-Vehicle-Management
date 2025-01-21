package com.rafael_men.vehicles_api.Model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("Carro")
public class Car extends Vehicle {

    private Integer quantidadePortas;
    private String tipoCombustivel;

    public Car() {
    }

    public Car(Integer quantidadePortas, String tipoCombustivel) {
        this.quantidadePortas = quantidadePortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    public Integer getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(Integer quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Car car = (Car) object;
        return Objects.equals(quantidadePortas, car.quantidadePortas) && Objects.equals(tipoCombustivel, car.tipoCombustivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantidadePortas, tipoCombustivel);
    }
}
