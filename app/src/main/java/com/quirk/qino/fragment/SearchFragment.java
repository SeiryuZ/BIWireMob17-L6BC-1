package com.quirk.qino.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qino.qino.R;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class SearchFragment extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.restaurant_search_layout, container, false);

        return myView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // Set title
        getActivity().setTitle("Search Restaurant resume");
    }


}