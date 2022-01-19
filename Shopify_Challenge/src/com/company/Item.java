package com.company;

public class Item {

    /*****---------- PROPERTIES ----------*****/
    private String name;
    private String description = "{no description yet}";
    private int quantity = 0;

    /*****---------- CONSTRUCTORS ----------*****/
    public Item(String name, String description, int quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item(String name) {
        this.name = name;
    }

    /*****---------- METHODS ----------*****/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public String toString(){
        return "Item name: " + this.name +
                "\nDescription: " + this.description +
                "\nQuantity: " + this.quantity;
    }
}
