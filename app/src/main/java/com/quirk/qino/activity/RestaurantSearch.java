package com.quirk.qino.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qino.qino.R;
import com.quirk.qino.adapter.CustomAdapter;
import com.quirk.qino.fragment.RestaurantInfoFragment;
import com.quirk.qino.fragment.SearchFragment;
import com.quirk.qino.model.CurrentRestaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantSearch extends AppCompatActivity {

    private String TAG = "Log";

    private FirebaseAuth auth;

    private GoogleApiClient mGoogleApiClient;

    private TextView test;

    private DatabaseReference mDatabase;

    private FirebaseUser user;

    private SharedPreferences sharedPref;

    private CurrentRestaurant currentRestaurant = new CurrentRestaurant();

    android.app.FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

        fragmentManager.beginTransaction()
                .replace(R.id.Search_Frame, new SearchFragment())
                .commit();

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

        //Get User info
        user = FirebaseAuth.getInstance().getCurrentUser();

        //If user is not signed in
        if (user != null) {
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());


    }

    public void search_btn_RestaurantClicked(View view) {
        test = (TextView) findViewById(R.id.search_text_test);

        // ged shared pref
        sharedPref =  PreferenceManager
                .getDefaultSharedPreferences(this);
        String UserId = sharedPref.getString("test1", "");
        Log.d(TAG, "Value is: " + UserId);

        test.setText( " " );

        //add Restaurant list
        ArrayList<Object> restaurant_Uid = new ArrayList<>();
        ArrayList<Object> restaurant_Name = new ArrayList<>();
        ArrayList<Object> restaurant_Address = new ArrayList<>();
        ArrayList<Object> restaurant_Image = new ArrayList<>();

        restaurant_Uid.add(1);
        restaurant_Uid.add(2);
        restaurant_Uid.add(3);
        restaurant_Uid.add(4);

        restaurant_Name.add("name1");
        restaurant_Name.add("name2");
        restaurant_Name.add("name3");
        restaurant_Name.add("name4");

        restaurant_Address.add("address1");
        restaurant_Address.add("address2");
        restaurant_Address.add("address3");
        restaurant_Address.add("address4");

        restaurant_Image.add(R.drawable.chinese);
        restaurant_Image.add(R.drawable.dessert);
        restaurant_Image.add(R.drawable.korea);
        restaurant_Image.add(R.drawable.japanese);

        // Create a hash map
        final HashMap<String, ArrayList<Object>> RestaurantList = new HashMap<>();

        //put array list to hash map
        RestaurantList.put("uid",restaurant_Uid);
        RestaurantList.put("name",restaurant_Name);
        RestaurantList.put("address",restaurant_Address);
        RestaurantList.put("image",restaurant_Image);

        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("Email").getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Make adapter for listView
        CustomAdapter restaurantAdapter = new CustomAdapter(this, RestaurantList.get("uid"), RestaurantList.get("name"), RestaurantList.get("address"), RestaurantList.get("image"));
        //ListAdapter restaurantAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, restaurantList);
        ListView restaurantListView = (ListView) findViewById(R.id.search_list_RestaurantList);
        restaurantListView.setAdapter(restaurantAdapter);

        //Listener for the restaurant list
        restaurantListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String restaurantinfo = String.valueOf(parent.getItemIdAtPosition(position));
                        int i = Integer.parseInt(restaurantinfo);
                        Toast.makeText(getApplicationContext(), restaurantinfo, Toast.LENGTH_SHORT).show();


                        currentRestaurant.setUid((int) RestaurantList.get("image").get(i) );
                        currentRestaurant.setName( (String) RestaurantList.get("name").get(i) );
                        currentRestaurant.setAddress( (String) RestaurantList.get("address").get(i) );
                        currentRestaurant.setImage( (int) RestaurantList.get("image").get(i) );

                        //Goes to restaurant page
                        fragmentManager.beginTransaction()
                                .replace(R.id.Search_Frame, new RestaurantInfoFragment())
                                .addToBackStack(null)
                                .commit();
                        setTitle( currentRestaurant.getName());
                    }
                }
        );

    }

    public void info_btn_OrderClicked (View view){

    }


}




