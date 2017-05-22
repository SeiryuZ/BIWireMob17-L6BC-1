package com.quirk.qino.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qino.qino.R;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class SearchFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.restaurant_search_layout, container, false);

        //For Creating Restaurant List
        String[] restaurant_Name_List = {"Name1", "Name2", "Name3","Name4"};
        String[] restaurant_Address_List = {"Address1", "Address2", "Address3","Address4"};
        Integer[] list_of_restaurant_Image = {R.drawable.chinese,R.drawable.dessert,R.drawable.fastfood,R.drawable.japanese};
        final Integer[] list_of_restaurant_Uid = {1,2,3,4};

        CustomAdapter restaurantAdapter = new CustomAdapter(getActivity(),list_of_restaurant_Uid,restaurant_Name_List,restaurant_Address_List,list_of_restaurant_Image);
        //ListAdapter restaurantAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, restaurantList);
        ListView restaurantListView = (ListView) myView.findViewById(R.id.search_list_RestaurantList);
        restaurantListView.setAdapter(restaurantAdapter);

        //Listener for the restaurant list
        restaurantListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String restaurantinfo = String.valueOf(parent.getItemAtPosition(position));
                        String restaurantinfo = String.valueOf(parent.getItemIdAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), restaurantinfo, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return myView;
    }

}