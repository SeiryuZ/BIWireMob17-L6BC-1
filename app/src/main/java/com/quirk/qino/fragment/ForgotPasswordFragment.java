package com.quirk.qino.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qino.qino.R;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class ForgotPasswordFragment extends  Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.authenthication_forgotpassword_layout, container, false);
         //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return myView;
    }


}

