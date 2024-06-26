package com.example.project;

import com.google.gson.annotations.SerializedName;

public class Plants {
    private String ID;
    private String name;
    private String company;
    private String category;
    private String location;
    @SerializedName("size")
    private int centimeter;
    @SerializedName("auxdata")
    private String plantPhoto;


    public Plants(String ID, String name, String company, String category, String location, int centimeter, String plantPhoto) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.category = category;
        this.location = location;
        this.centimeter = centimeter;
        this.plantPhoto = plantPhoto;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public int getCentimeter() {
        return centimeter;

    }
    public String getPlantPhoto() {
        return plantPhoto;
    }
}
