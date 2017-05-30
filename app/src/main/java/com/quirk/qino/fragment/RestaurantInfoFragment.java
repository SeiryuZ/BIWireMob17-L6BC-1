package com.quirk.qino.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qino.qino.R;
import com.quirk.qino.model.CurrentRestaurant;
import com.quirk.qino.model.ListOfRestaurant;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class RestaurantInfoFragment extends  Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.restaurant_info_layout, container, false);

        TextView cuisine = (TextView) myView.findViewById(R.id.info_text_Cuisine);
        TextView description = (TextView) myView.findViewById(R.id.info_text_Description);
        TextView phone = (TextView) myView.findViewById(R.id.info_text_phone);
        TextView price = (TextView) myView.findViewById(R.id.info_text_Price);

        String filename = "CurrentRestaurant";
        try {

            FileInputStream inputStream = getActivity().openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Map myNewlyReadInMap = (HashMap) objectInputStream.readObject();
            objectInputStream.close();

            cuisine.setText(myNewlyReadInMap.get("Cuisine") + "");
            description.setText(myNewlyReadInMap.get("Description") + "");
            phone.setText(myNewlyReadInMap.get("Phone") + "");
            price.setText("30$");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return myView;
    }




}


