package com.quirk.qino;

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

    public CustomAdapter(Context context, String[] list_of_restaurant) {
        super(context, R.layout.custom_restaurant_list_layout, list_of_restaurant);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater restaurantInflater = LayoutInflater.from(getContext());
        View customeView = restaurantInflater.inflate(R.layout.custom_restaurant_list_layout, parent, false);

        String singleRestaurantItem = getItem(position);
        TextView restaurantName = (TextView) customeView.findViewById(R.id.restaurant_list_name);
        ImageView restaurantImage = (ImageView) customeView.findViewById(R.id.restaurant_list_image);

        restaurantName.setText(singleRestaurantItem);
        restaurantImage.setImageResource(R.drawable.chinese);
        return customeView;
    }
}
