package com.quirk.qino.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by albertcahyawan on 5/28/2017.
 */


public class CurrentRestaurant {
    public Integer uid;
    public String name;
    public String address;
    public Integer Image;

    public CurrentRestaurant() {
        this.uid = 0;
        this.name = "";
        this.address = "";
        Image = 0;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getImage() {
        return Image;
    }

    public void setImage(Integer image) {
        Image = image;
    }
}
