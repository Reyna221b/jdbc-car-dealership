package com.pluralsight.views;

public class HomeView extends ViewBase
{
    public int getUserSelection()
    {
        System.out.println();
        System.out.println("Home");
        System.out.println("-".repeat(40));
        System.out.println("Find Vehicles by: ");
        System.out.println("[1] - Price Range");
        System.out.println("[2] - Make / Model");
        System.out.println("[3] - Year");
        System.out.println("[4] - Color");
        System.out.println("[5] - Mileage Range");
        System.out.println("[6] - Type(car, suv, truck, van)");
        System.out.println("[7] - List All Vehicles");
        System.out.println("[8] - Add a Vehicle");
        System.out.println("[9] - Remove a Vehicle");
        System.out.println("[10] - Sell / Lease a Vehicle");
        System.out.println("[11] - Admin Menu");
        System.out.println("[99] - Quit");
        System.out.println("-".repeat(40));

        System.out.print("Please select an option: ");
        return Integer.parseInt(in.nextLine());
    }
}
