package com.spectro.konf.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Details {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min =2, max=50, message = "Name of detail should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Type should not be empty")
    private String type;

    @NotEmpty(message = "Date should not be empty")
    private String date;


    @Min(value = 0, message = "You cannot give negative numbers")
    @NotEmpty(message = "Enter price of Detail please")
    private int price;


    @NotEmpty(message = "Enter quantity of Detail please")
    @Min(value = 0, message = "You cannot give negative numbers")
    private int quantity;



    public Details(int id, String name, String type, String date, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public Details() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
