package com.get_table.biwiremob17_l6bc_1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class ForgotPasswordFragment extends  Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.forgotpassword_layout, container, false);
         //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return myView;
    }


}

