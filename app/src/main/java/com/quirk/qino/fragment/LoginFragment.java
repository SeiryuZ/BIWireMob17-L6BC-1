package com.quirk.qino.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qino.qino.R;
import com.quirk.qino.activity.AuthenthicationActivity;


/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class LoginFragment extends Fragment {

    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.authenthication_login_layout, container, false);

        myView.findViewById(R.id.login_btn_GoogleLogin).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                AuthenthicationActivity activity = (AuthenthicationActivity) getActivity();
                activity.signIn(myView);
            }
        });

        return myView;

    }


}
