package com.quirk.qino;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
        myView = inflater.inflate(R.layout.search_layout, container, false);

        //For Creating Restaurant List
        String[] restaurantList = {"test1", "test2", "test3"};
        ListAdapter restaurantAdapter = new CustomAdapter(getActivity(),restaurantList);
        //ListAdapter restaurantAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, restaurantList);
        ListView restaurantListView = (ListView) myView.findViewById(R.id.restaurant_list);
        restaurantListView.setAdapter(restaurantAdapter);

        //Listener for the restaurant list
        restaurantListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String restaurantinfo = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), restaurantinfo, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return myView;
    }

}
