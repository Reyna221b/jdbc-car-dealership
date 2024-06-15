package com.pluralsight.views.vehicles;

import com.pluralsight.views.ViewBase;

public class VehicleSearchByMakeModelView extends ViewBase
{
    public String getMakeToSearch() {
        System.out.println();
        System.out.println("Search vehicles by make and model");
        System.out.println("-".repeat(40));
        System.out.print("Enter the make: ");
        String make = in.nextLine().strip();
        return make;
    }

    public String getModelToSearch() {
        System.out.print("Enter the model: ");
        String model = in.nextLine().strip();
        return model;
    }
}
