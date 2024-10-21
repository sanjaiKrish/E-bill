package com.bill.bill.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.bill.model.Vehicle;
import com.bill.bill.repository.VehicleRepository;




@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    // Method for saving a single vehicle
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    // Method for saving a list of vehicles
    public void saveAll(List<Vehicle> vehicles) {
        vehicleRepository.saveAll(vehicles);
    }
}
