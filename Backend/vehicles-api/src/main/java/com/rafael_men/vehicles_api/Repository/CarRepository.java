package com.rafael_men.vehicles_api.Repository;

import com.rafael_men.vehicles_api.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
