package com.quirk.qino.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qino.qino.R;

/**
 * Created by albertcahyawan on 4/4/2017.
 */

public class AccountFragement extends  Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.main_account_layout, container, false);

        SharedPreferences sharedPref;

        sharedPref = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        TextView UserName = (TextView) myView.findViewById(R.id.account_text_UserName);
        TextView Email = (TextView) myView.findViewById(R.id.account_text_Email);
        TextView Phone = (TextView) myView.findViewById(R.id.account_text_Phone);

        UserName.setText(sharedPref.getString("Name", ""));
        Email.setText(sharedPref.getString("Email", ""));
        Phone.setText(sharedPref.getString("Phone", ""));
        return myView;
    }

}
