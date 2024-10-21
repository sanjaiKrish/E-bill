package com.bill.bill.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.bill.bill.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByVehicleType(String vehicleType); // Add this method
}
