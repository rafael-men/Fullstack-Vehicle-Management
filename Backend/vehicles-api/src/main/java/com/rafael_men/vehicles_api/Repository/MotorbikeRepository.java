package com.rafael_men.vehicles_api.Repository;

import com.rafael_men.vehicles_api.Model.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike,Integer> {
}
