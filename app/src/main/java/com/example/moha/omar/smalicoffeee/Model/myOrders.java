package com.example.moha.omar.smalicoffeee.Model;

public class myOrders {

    String Foodname, restuarant, Rating, name, userid, email, phone, price;
    public myOrders(String foodname,String restuarant,String rating,String name,String userid,String email,String phone,String price) {

        this.Foodname = foodname;
        this.restuarant = restuarant;
        Rating = rating;
        this.name = name;
        this.userid = userid;
        this.email = email;
        this.phone = phone;
        this.price = price;

    }

    public String getFoodname() {
        return Foodname;
    }

    public void setFoodname(String foodname) {
        Foodname = foodname;
    }

    public String getRestuarant() {
        return restuarant;
    }

    public void setRestuarant(String restuarant) {
        this.restuarant = restuarant;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
