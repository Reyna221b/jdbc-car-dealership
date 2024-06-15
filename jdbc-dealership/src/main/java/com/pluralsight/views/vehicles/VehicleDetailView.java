package com.pluralsight.views.vehicles;

import com.pluralsight.models.Vehicle;
import com.pluralsight.views.ViewBase;

import java.util.List;

public class VehicleDetailView extends ViewBase
{
    public void displayVehicles(Vehicle vehicles) {

        System.out.println();
        System.out.println("-".repeat(125));
        System.out.println();
        System.out.println("                                                        All Vehicles");

        System.out.println("\n"+"-".repeat(125));
        System.out.printf("%-15s  %-14s  %-15s  %-15s  %-14s  %-15s  %-13s  %-15s\n",
                "Vin", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("-".repeat(125));
        if(vehicles == null){
            System.out.println("Sorry no matches found!");
            return;
        }


    }

}
