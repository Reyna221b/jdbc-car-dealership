package com.pluralsight.controllers;

import com.pluralsight.models.Vehicle;
import com.pluralsight.services.VehiclesDao;

import java.util.List;

public class VehiclesController
{
    private VehiclesDao vehiclesDao;

    public VehiclesController(VehiclesDao vehicleDao) {
        this.vehiclesDao = vehicleDao;
    }
    public List<Vehicle> findAllVehicles() {
        return vehiclesDao.getAllVehicles();
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        return vehiclesDao.getVehiclesByPriceRange(minPrice, maxPrice);
    }

    public List<Vehicle> searchByMakeAndModel(String make, String model) {
        return vehiclesDao.getVehiclesByMakeAndModel(make, model);
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        return vehiclesDao.getVehiclesByYearRange(minYear, maxYear);
    }

    public List<Vehicle> searchByColor(String color) {
        return vehiclesDao.getVehiclesByColor(color);
    }

    public List<Vehicle> searchByMileageType(String mileageType) {
        return vehiclesDao.getVehiclesByMileageType(mileageType);
    }

    public List<Vehicle> searchByType(String type) {
        return vehiclesDao.getVehiclesByType(type);
    }

    public Vehicle searchById(int id) {
        return vehiclesDao.getVehicleByVin(id);
    }

    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehiclesDao.saveVehicle(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehiclesDao.updateVehicle(vehicle);
    }

    public void deleteVehicle(int vehicleId) {
        vehiclesDao.deleteVehicle(vehicleId);
    }
}
