package com.quirk.qino;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.qino.qino.R;

public class RestaurantSearch extends AppCompatActivity {

    private FirebaseAuth auth;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Search_Frame, new SearchFragment())
                .commit();
        getSupportActionBar().setTitle("Search Restaurant");
        // change icon to arrow drawable
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_camera);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        auth = FirebaseAuth.getInstance();

    }

    public void RestaurantPressed(View view) {

        /*
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Search_Frame, new RestaurantInfoFragment())
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Restaurant Page");
        // change icon to arrow drawable
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_camera);
        */
    }



}




