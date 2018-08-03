package com.example.dipak.doctor;

public class Product {

    private String name;
    private String location;
    private String price;
    private String availablity;

    public Product(){

    }

    public Product(String name, String location, String price, String availablity) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.availablity = availablity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailablity() {
        return availablity;
    }

    public void setAvailablity(String availablity) {
        this.availablity = availablity;
    }
}