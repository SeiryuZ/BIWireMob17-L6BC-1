package com.quirk.qino.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by albertcahyawan on 5/29/2017.
 */

public class ListOfRestaurant {

    HashMap<String, ArrayList<Object>> RestaurantList = new HashMap<>();

    public ListOfRestaurant() {
    }

    public HashMap<String, ArrayList<Object>> getRestaurantList() {
        return RestaurantList;
    }

    public void setRestaurantList(HashMap<String, ArrayList<Object>> restaurantList) {
        RestaurantList = restaurantList;
    }
}
