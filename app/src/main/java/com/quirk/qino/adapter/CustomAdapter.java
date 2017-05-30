package com.quirk.qino.adapter;

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

import java.util.ArrayList;

/**
 * Created by albertcahyawan on 5/9/2017.
 */

public class CustomAdapter extends ArrayAdapter{

    private final Context context;
    private final ArrayList<Object> list_of_restaurant;
    private final ArrayList<Object> list_of_restaurant_Address;
    private final ArrayList<Object> list_of_restaurant_Image;
    private final ArrayList<Object> list_of_restaurant_Uid;

    public CustomAdapter(Context context,@Nullable ArrayList<Object> list_of_restaurant_Uid,@Nullable ArrayList<Object> list_of_restaurant,
                         @Nullable ArrayList<Object> list_of_restaurant_Address,@Nullable ArrayList<Object>list_of_restaurant_Image) {

        super(context, R.layout.custom_restaurant_list_layout, list_of_restaurant_Uid);

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

        String UidRestaurantItem = (String) list_of_restaurant_Uid.get(position);
        String nameRestaurantItem = (String) list_of_restaurant.get(position);
        String addressRestaurantItem = (String) list_of_restaurant_Address.get(position);
        //Integer imgRestaurantItem = (Integer) list_of_restaurant_Image.get(position);

        TextView restaurantName = (TextView) customeView.findViewById(R.id.restaurant_list_name);
        TextView restaurantAddress = (TextView) customeView.findViewById(R.id.restaurant_list_address);
        ImageView restaurantImage = (ImageView) customeView.findViewById(R.id.restaurant_list_image);

        restaurantName.setText(nameRestaurantItem);
        restaurantAddress.setText(addressRestaurantItem);
        restaurantImage.setImageResource(R.drawable.chinese);//imgRestaurantItem
        return customeView;
    }
}