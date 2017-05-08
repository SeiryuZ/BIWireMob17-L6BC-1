package com.quirk.qino;

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

public class OrderFragement extends  Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.order_layout, container, false);
        return myView;
    }


}
