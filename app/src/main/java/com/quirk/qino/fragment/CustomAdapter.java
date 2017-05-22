package com.quirk.qino.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qino.qino.R;

/**
 * Created by albertcahyawan on 5/9/2017.
 */

public class CustomAdapter extends ArrayAdapter<String>{

    private final Context context;
    private final String[] list_of_restaurant;
    private final String[] list_of_restaurant_Address;
    private final Integer[] list_of_restaurant_Image;
    private final Integer[] list_of_restaurant_Uid;

    public CustomAdapter(Context context,Integer[] list_of_restaurant_Uid,String[] list_of_restaurant, String[] list_of_restaurant_Address, Integer[] list_of_restaurant_Image) {
        super(context, R.layout.custom_restaurant_list_layout, list_of_restaurant);

        this.context=context;
        this.list_of_restaurant = list_of_restaurant;
        this.list_of_restaurant_Address = list_of_restaurant_Address;
        this.list_of_restaurant_Image = list_of_restaurant_Image;
        this.list_of_restaurant_Uid = list_of_restaurant_Uid;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater restaurantInflater = LayoutInflater.from(getContext());
        View customeView = restaurantInflater.inflate(R.layout.custom_restaurant_list_layout, parent, false);

        Integer UidRestaurantItem = list_of_restaurant_Uid[position];
        String nameRestaurantItem = list_of_restaurant[position];
        String addressRestaurantItem = list_of_restaurant_Address[position];
        Integer imgRestaurantItem = list_of_restaurant_Image[position];

        TextView restaurantName = (TextView) customeView.findViewById(R.id.restaurant_list_name);
        TextView restaurantAddress = (TextView) customeView.findViewById(R.id.restaurant_list_address);
        ImageView restaurantImage = (ImageView) customeView.findViewById(R.id.restaurant_list_image);

        restaurantName.setText(nameRestaurantItem);
        restaurantAddress.setText(addressRestaurantItem);
        restaurantImage.setImageResource(imgRestaurantItem);
        return customeView;
    }
}