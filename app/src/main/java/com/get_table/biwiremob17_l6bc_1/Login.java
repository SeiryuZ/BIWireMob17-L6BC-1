package com.get_table.biwiremob17_l6bc_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginOnPressed (View view){
        EditText UserName = (EditText)findViewById(R.id.LoginEmail);
        EditText Password = (EditText)findViewById(R.id.LoginPassword);


        if(UserName.getText().toString().equals("admin@gmail.com") || Password.getText().toString().equals("password")){
            //If Username/Password is Right

            Intent intent = new Intent(this,HomePage.class);
            startActivity(intent);
        }else{
            //If Username/Password is wrong

        }
    }



    public void  SignUpOnPressed(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}
