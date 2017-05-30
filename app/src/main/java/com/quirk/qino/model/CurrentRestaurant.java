package com.quirk.qino.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by albertcahyawan on 5/28/2017.
 */


public class CurrentRestaurant extends Application {
    public String uid;
    public String name;
    public String address;
    public String Image;

    public CurrentRestaurant( ) {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
