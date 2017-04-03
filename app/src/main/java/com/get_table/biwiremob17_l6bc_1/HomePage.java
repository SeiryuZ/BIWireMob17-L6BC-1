package com.get_table.biwiremob17_l6bc_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.DrawerLayout;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Title
        //setTitle("Get-Tables");
    }

    public void RestaurantSearchPressed(View view){
        Intent intent = new  Intent(this,RestaurantSearch.class);
        startActivity(intent);
    }

    public void  RestaurantPressed(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }



}
