package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Loop here. Exit. option.
        // SETUP DISPLAY
        System.out.println("INVENTORY TRACKER");
        InventoryTracker inventoryTracker = new InventoryTracker("Inventory_1");
        Scanner mainInputScanner = new Scanner(System.in);
        //MAIN MENU CHOICE
        String selection = "0";

        while(!(selection).equals(MenuSelection.EXIT_PROGRAM.choiceNum)) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Please choose an option:");
            System.out.println("1. Add inventory items");
            System.out.println("2. Edit inventory items");
            System.out.println("3. Remove inventory items");
            System.out.println("4. View full inventory list");
            System.out.println("5. Create a Shipment");
            System.out.println("6. View Shipments");
            System.out.println("7. Exit program");
            System.out.println("------------------------------------------------------------------------");

            selection = mainInputScanner.nextLine();
            if (selection.equals(MenuSelection.ADD_ITEMS.choiceNum)) {
                inventoryTracker.addItem(mainInputScanner);

            } else if (selection.equals(MenuSelection.EDIT_ITEMS.choiceNum)) {
               inventoryTracker.editItem(mainInputScanner);

            } else if (selection.equals(MenuSelection.REMOVE_ITEMS.choiceNum)) {
                inventoryTracker.removeItem(mainInputScanner);

            } else if (selection.equals(MenuSelection.VIEW_ITEM_LIST.choiceNum)) {
                System.out.println("VIEWING FULL INVENTORY");
                inventoryTracker.viewInventory();

            } else if (selection.equals(MenuSelection.CREATE_SHIPMENT.choiceNum)) {
                inventoryTracker.createShipment(mainInputScanner);

            } else if(selection.equals(MenuSelection.VIEW_SHIPMENTS.choiceNum)) {
                System.out.println("VIEWING ALL SHIPMENTS");
                inventoryTracker.viewShipments();

            } else if (selection.equals(MenuSelection.EXIT_PROGRAM.choiceNum)) {
                System.out.println("EXITING PROGRAM");

            } else {
                System.out.println("You entered an invalid choice. Please enter a choice (1-7):");
            }
        }
        System.out.println("INVENTORY TRACKING ENDED");
    }
}
