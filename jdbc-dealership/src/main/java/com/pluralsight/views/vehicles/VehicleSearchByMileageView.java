package com.pluralsight.views.vehicles;

import com.pluralsight.models.Vehicle;
import com.pluralsight.views.ViewBase;

import java.util.List;

public class VehicleSearchByMileageView extends ViewBase
{
    public List<Vehicle> getVehiclesByMileageType() {
        System.out.println();
        System.out.println("Get Vehicles by Mileage Type");
        System.out.println("-".repeat(40));
        String mileageType = in.nextLine().strip();
        List<Vehicle> vehicles = getVehiclesByMileageType();
        return vehicles;
    }
}
