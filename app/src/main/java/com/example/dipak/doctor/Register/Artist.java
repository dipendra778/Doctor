package com.example.dipak.doctor.Register;

public class Artist {

    private String name;
    private String speciality;
    private String location;
    //private int phone;
    private String phone;
    private String hospital;
    private String price;
    private String availablity;
    private String email;
    private String search;

    public Artist(String name, String location, String price, String availablity, String speciality, String phone, String hospital, String email ,String search) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.availablity = availablity;
        this.speciality = speciality;
        this.phone = phone;
        this.hospital = hospital;
        this.email = email;
        this.search=search;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    /* public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }
    */
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
