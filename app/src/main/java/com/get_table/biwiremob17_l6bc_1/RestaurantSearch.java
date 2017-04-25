package com.get_table.biwiremob17_l6bc_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RestaurantSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

    }


    public void  RestaurantPressed(View view){
        Intent intent = new Intent(this,RestaurantPage.class);
        startActivity(intent);
    }
}
