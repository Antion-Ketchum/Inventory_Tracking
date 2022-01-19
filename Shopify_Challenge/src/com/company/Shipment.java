package com.company;

import java.util.ArrayList;

public class Shipment {

    /*****---------- PROPERTIES ----------*****/
    private int invoice_Num;
    private String description;
    private String customer;
    private ArrayList<Item> itemList;

    /*****---------- CONSTRUCTORS ----------*****/
    public Shipment(int invoice_Num) {
        this.invoice_Num = invoice_Num;
        itemList = new ArrayList<>();
    }

    public Shipment(String description) {
        this.description = description;
        itemList = new ArrayList<>();
    }
    /*****---------- METHODS ----------*****/
    public int getInvoice_Num() {
        return this.invoice_Num;
    }

    public void setInvoice_Num(int invoice_Num) {
        this.invoice_Num = invoice_Num;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
