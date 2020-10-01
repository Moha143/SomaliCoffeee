package com.example.moha.omar.smalicoffeee.Model;

public class Foods {
    String name, image, restaurant, date, Rating, price;

    public Foods(String name,String image,String restaurant,String date ,String rating ,String price) {
        this.name = name;
        this.price=price;
        this.image = image;
        this.restaurant = restaurant;
       this.date = date;
        this.Rating=rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getName() {
        return name;
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

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
