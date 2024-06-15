package com.pluralsight.services;

import com.pluralsight.models.Vehicle;

import java.util.List;

public interface VehiclesDao {

    Vehicle saveVehicle(Vehicle vehicle);
    Vehicle getVehicleByVin(int vin);
    List<Vehicle> getAllVehicles();
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(int vin);
    List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> getVehiclesByMakeAndModel(String make, String model);
    List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear);
    List<Vehicle> getVehiclesByColor(String color);
    List<Vehicle> getVehiclesByMileageType(String mileageType);
    List<Vehicle> getVehiclesByType(String type);
}