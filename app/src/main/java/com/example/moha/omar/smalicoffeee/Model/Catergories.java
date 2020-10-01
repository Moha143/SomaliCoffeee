package com.example.moha.omar.smalicoffeee.Model;

public class Catergories {
    String name, image, places;

    public String getName() {
        return name;
    }

    public Catergories(String name,String image,String places) {
        this.name = name;
        this.image = image;
        this.places = places;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
