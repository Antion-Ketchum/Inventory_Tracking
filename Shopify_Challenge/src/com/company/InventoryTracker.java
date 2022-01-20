package com.company;

import java.util.ArrayList;
import java.util.Scanner;


enum MenuSelection {
    ADD_ITEMS("1"),
    EDIT_ITEMS("2"),
    REMOVE_ITEMS("3"),
    VIEW_ITEM_LIST("4"),
    CREATE_SHIPMENT("5"),
    VIEW_SHIPMENTS("6"),
    EXIT_PROGRAM("7"),
    // INNER MENU FOR EDITING INVENTORY
    NAME("1"),
    QUANTITY("2"),
    DESCRIPTION("3"),
    GO_BACK("4");


    public final String choiceNum;

    MenuSelection(String choiceNum){
        this.choiceNum = choiceNum;
    }
}

public class InventoryTracker {

    /*****---------- PROPERTIES ----------*****/
    ArrayList<Item> inventory;
    ArrayList<Shipment> shipments;
    String name;

    /*****---------- CONSTRUCTORS ----------*****/
    public InventoryTracker(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.shipments = new ArrayList<>();
    }

    /*****---------- METHODS ----------*****/
    //view old shipments?

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Shipment> getShipments() {
        return this.shipments;
    }

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Scanner inputScanner){
        System.out.println("You choose to add inventory items.");
        System.out.println("Enter name of item:");
        String response = inputScanner.nextLine();
        Item newItem = new Item(response);
        System.out.println("Enter quantity of item:");
        response = inputScanner.nextLine();
        newItem.setQuantity(Integer.parseInt(response));
        System.out.println("Enter description of item:");
        response = inputScanner.nextLine();
        newItem.setDescription(response);


        boolean namesMatch = false;
        boolean descriptionsMatch = false;

        for(Item item: this.inventory){
            namesMatch = newItem.getName().equalsIgnoreCase(item.getName());
            descriptionsMatch = newItem.getDescription().equalsIgnoreCase(item.getDescription());
            if(namesMatch && descriptionsMatch){
                int presentQuantity = item.getQuantity();
                item.setQuantity(presentQuantity + newItem.getQuantity());
                System.out.println("The following was just updated:");
                System.out.println("NAME: " + item.getName());
                System.out.println("QUANTITY: " + item.getQuantity());
                System.out.println("DESCRIPTION: " + item.getDescription());
            }
        }
        if( !(namesMatch && descriptionsMatch)) {
            this.inventory.add(newItem);
            System.out.println("The following was just added:");
            System.out.println("NAME: " + newItem.getName());
            System.out.println("QUANTITY: " + newItem.getQuantity());
            System.out.println("DESCRIPTION: " + newItem.getDescription());
        }
    }

    public void removeItem(Scanner inputScanner){
        if(this.inventory.size() == 0) {
            System.out.println("Cannot remove items: There are no items in the inventory.");

        } else {
            System.out.println("You choose to remove inventory items.");
            int lineNumber = 0;
            System.out.println("Below is the inventory:");
            for (Item inventoryItem : this.inventory) {
                lineNumber++;
                System.out.println(lineNumber + ". " + inventoryItem.getName());

            }

            System.out.println("Please enter the number line to remove item: ");
            String response = inputScanner.nextLine();

            int chosenIndex = Integer.parseInt(response) - 1;
            while( ( chosenIndex < 0)  || (chosenIndex >= this.inventory.size()) ) {

                System.out.println("Invalid response: Enter a number from 1 to " + lineNumber + ": ");
                response = inputScanner.nextLine();
                chosenIndex = Integer.parseInt(response) - 1;
            }
            Item itemToRemove = this.inventory.remove(Integer.parseInt(response) - 1);

            System.out.println("The removed entry:");
            System.out.println("NAME: " + itemToRemove.getName());
            System.out.println("QUANTITY: " + itemToRemove.getQuantity());
            System.out.println("DESCRIPTION: " + itemToRemove.getDescription());
        }
    }

    public void editItem(Scanner inputScanner){
        if(this.inventory.size() == 0) {
            System.out.println("Cannot edit: There are no items in the inventory.");

        } else{
            System.out.println("You choose to edit inventory items.");
            int lineNumber = 0;
            System.out.println("Below is the inventory:");
            for (Item inventoryItem : this.inventory) {
                lineNumber++;
                System.out.println(lineNumber + ". " + inventoryItem.getName());

            }
            System.out.println("Please enter the number line to edit: ");
            String response = inputScanner.nextLine();
            int chosenIndex = Integer.parseInt(response) - 1;
            while( ( chosenIndex < 0)  || (chosenIndex >= this.inventory.size()) ) {

                System.out.println("Invalid response: Enter a number from 1 to " + lineNumber + ": ");
                response = inputScanner.nextLine();
                chosenIndex = Integer.parseInt(response) - 1;
            }

            Item itemToEdit = this.inventory.get(Integer.parseInt(response) - 1);
            response = "0";
            while (!(response).equals(MenuSelection.GO_BACK.choiceNum)) {
                System.out.println("Please choose an option:");
                System.out.println("1. Edit item name");
                System.out.println("2. Edit item quantity");
                System.out.println("3. Edit item description");
                System.out.println("4. Go back to main menu");

                response = inputScanner.nextLine();
                if (response.equals(MenuSelection.NAME.choiceNum)) {
                    System.out.println("Please enter new name: ");
                    response = inputScanner.nextLine();
                    itemToEdit.setName(response);

                } else if (response.equals(MenuSelection.QUANTITY.choiceNum)) {
                    System.out.println("Please enter new quantity: ");
                    response = inputScanner.nextLine();
                    itemToEdit.setQuantity(Integer.parseInt(response));

                } else if (response.equals(MenuSelection.DESCRIPTION.choiceNum)) {
                    System.out.println("Please enter new description: ");
                    response = inputScanner.nextLine();
                    itemToEdit.setDescription(response);

                } else if (response.equals(MenuSelection.GO_BACK.choiceNum)) {
                } else {
                    System.out.println("You entered an invalid choice. Please enter a choice (1-4):");
                }
            }

            System.out.println("The edited entry:");
            System.out.println("NAME: " + itemToEdit.getName());
            System.out.println("QUANTITY: " + itemToEdit.getQuantity());
            System.out.println("DESCRIPTION: " + itemToEdit.getDescription());
        }
    }

    public void createShipment(Scanner inputScanner){
        if(this.inventory.size() == 0) {
            System.out.println("Cannot create a shipment: There are no items in the inventory.");

        } else {
            System.out.println("CREATING A SHIPMENT");
            Shipment newShipment = new Shipment("SHIPMENT_" + this.shipments.size());

            boolean keepAddingToShipment = true;
            while(keepAddingToShipment) {
                System.out.println("Below is the inventory:");
                int lineNumber = 0;
                for (Item inventoryItem : this.inventory) {
                    lineNumber++;
                    System.out.println(lineNumber + ". [Quantity: " + inventoryItem.getQuantity() + "] " + inventoryItem.getName());
                }

                System.out.println("Please enter the number line to ship: ");
                String selection = inputScanner.nextLine();
                int chosenIndex = Integer.parseInt(selection) - 1;
                while( ( chosenIndex < 0)  || (chosenIndex >= this.inventory.size()) ) {

                    System.out.println("Invalid response: Enter a number from 1 to " + lineNumber + ": ");
                    selection = inputScanner.nextLine();
                    chosenIndex = Integer.parseInt(selection) - 1;
                }

                Item itemToShip = this.inventory.get(chosenIndex);
                System.out.println("Please enter the quantity of the item to ship: ");
                selection = inputScanner.nextLine();
                int numToShip = Integer.parseInt(selection);
                int remainder = itemToShip.getQuantity() - numToShip;
                while(remainder < 0 ) {
                    System.out.println("Invalid response: Enter a number from 0 to " + itemToShip.getQuantity() + ": ");
                    selection = inputScanner.nextLine();
                    numToShip = Integer.parseInt(selection);
                    remainder = itemToShip.getQuantity() - numToShip;
                }

                Item itemAddedOnShipment = new Item(itemToShip.getName(), numToShip);
                itemAddedOnShipment.setDescription(itemToShip.getDescription());
                boolean namesMatch = false;
                boolean descriptionsMatch = false;
                for(Item item: newShipment.getItemList()){
                    namesMatch = itemAddedOnShipment.getName().equalsIgnoreCase(item.getName());
                    descriptionsMatch = itemAddedOnShipment.getDescription().equalsIgnoreCase(item.getDescription());
                    if(namesMatch && descriptionsMatch){
                        int presentQuantity = item.getQuantity();
                        item.setQuantity(presentQuantity + numToShip);
                    }
                }
                if( !(namesMatch && descriptionsMatch)){
                    newShipment.getItemList().add(itemAddedOnShipment);
                    this.shipments.add(newShipment);
                    itemToShip.setQuantity(remainder);
                }

                selection = "0";
                while( !selection.equals("1") && !selection.equals("2")) {
                    System.out.println("Please choose an option:");
                    System.out.println("1. Continue adding items to shipment.");
                    System.out.println("2. Stop adding items to shipment.");
                    selection = inputScanner.nextLine();
                    if(selection.equals("2")){
                        keepAddingToShipment = false;
                    }else {
                        System.out.println("You entered an invalid response.");
                    }
                }
            }
        }
    }

    public void viewInventory(){
        int indexCounter = 1;
        if(this.inventory.size() == 1){
            System.out.println("INVENTORY LIST: (" + this.inventory.size() + " item entry)");
        } else {
            System.out.println("INVENTORY LIST: (" + this.inventory.size() + " item entries)");
        }
        for (Item item: this.inventory) {
            System.out.println(indexCounter + ":");
            System.out.println(item.toString());
            indexCounter++;
        }
    }

    public void viewShipments(){
        if(this.shipments.size() == 1){
            System.out.println("INVENTORY LIST: (" + this.shipments.size() + " item entry)");
        } else {
            System.out.println("INVENTORY LIST: (" + this.shipments.size() + " item entries)");
        }
        int shipmentCounter = 1;
        for (Shipment shipment: this.shipments) {
            System.out.println("SHIPMENT " + shipmentCounter + ": ---------------------------------");
            shipmentCounter++;
            int indexCounter = 1;
            for (Item item: shipment.getItemList()) {
                System.out.println(indexCounter + ":");
                System.out.println(item.toString());
                indexCounter++;
            }
        }
    }

}
