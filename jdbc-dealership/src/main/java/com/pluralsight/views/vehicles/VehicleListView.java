package com.pluralsight.views.vehicles;

import com.pluralsight.models.Vehicle;

import java.util.List;

public class VehicleListView
{
    public void displayVehicles(List<Vehicle> vehicles) {

        System.out.println();
        System.out.println("-".repeat(125));
        System.out.println();
        System.out.println("                                                        All Vehicles");

        System.out.println("\n"+"-".repeat(125));
        System.out.printf("%-15s  %-14s  %-15s  %-15s  %-14s  %-15s  %-13s  %-15s\n",
                "Vin", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("-".repeat(125));
        if(vehicles.isEmpty()){
            System.out.println("Sorry no matches found!");
            return;
        }

        vehicles.forEach(v -> System.out.println(v));
    }
}
