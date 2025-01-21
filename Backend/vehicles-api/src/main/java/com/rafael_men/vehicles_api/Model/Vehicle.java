package com.rafael_men.vehicles_api.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "veiculos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "Carro"),
        @JsonSubTypes.Type(value = Motorbike.class, name = "Moto")
})
@DiscriminatorColumn(name = "tipo_veiculo", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = true)
    private String cor;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Double preco;


    public Vehicle() {
    }

    public Vehicle(Integer id, String modelo, String fabricante, String cor, Integer ano, Double preco) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vehicle vehicle = (Vehicle) object;
        return Objects.equals(id, vehicle.id) && Objects.equals(modelo, vehicle.modelo) && Objects.equals(fabricante, vehicle.fabricante) && Objects.equals(cor, vehicle.cor) && Objects.equals(ano, vehicle.ano) && Objects.equals(preco, vehicle.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, fabricante, cor, ano, preco);
    }
}
