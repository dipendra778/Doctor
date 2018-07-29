package com.example.dipak.doctor;

public class artist {

    private String name;
    private String speciality;
    private String location;
    private int price;
    private String availablity;

    public artist(String name, String location, int price, String availablity, String speciality) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.availablity = availablity;
        this.speciality = speciality;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvailablity() {
        return availablity;
    }

    public void setAvailablity(String availablity) {
        this.availablity = availablity;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
