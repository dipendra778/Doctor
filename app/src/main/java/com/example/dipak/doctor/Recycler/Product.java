package com.example.dipak.doctor.Recycler;

public class Product {

    private String name;
    private String location;
    private String price;
    private String availablity;
    private String hospital;
    private long phone;
    private String speciality;


    public Product() {

    }

    public Product(String name, String location, String price, String availablity, String speciality, long phone, String hospital) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.availablity = availablity;
        this.speciality = speciality;
        this.phone = phone;
        this.hospital = hospital;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}