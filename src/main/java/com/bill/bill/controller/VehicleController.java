package com.bill.bill.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bill.bill.model.Vehicle;
import com.bill.bill.repository.VehicleRepository;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new vehicle (Single)
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Create multiple vehicles (Batch)
    @PostMapping("/batch")
    public List<Vehicle> createVehicles(@RequestBody List<Vehicle> vehicles) {
        return vehicleRepository.saveAll(vehicles);
    }

    // Get vehicles by type
    @GetMapping("/type/{vehicleType}")
    public List<Vehicle> getVehiclesByType(@PathVariable String vehicleType) {
        return vehicleRepository.findAllByVehicleType(vehicleType);
    }

    // Update an existing vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setName(vehicleDetails.getName());
                    vehicle.setVehicleType(vehicleDetails.getVehicleType()); // Set vehicle type
                    Vehicle updatedVehicle = vehicleRepository.save(vehicle);
                    return ResponseEntity.ok(updatedVehicle);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicleRepository.delete(vehicle);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
