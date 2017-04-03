package com.get_table.biwiremob17_l6bc_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.get_table.biwiremob17_l6bc_1.R.id.editText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private void RegisterOnPressed(View view){
        EditText UserName = (EditText)findViewById(R.id.RegisterEmail);
        EditText Password = (EditText)findViewById(R.id.RegisterPassword);
        EditText RepeatPassword = (EditText)findViewById(R.id.RegisterRepeatPassword);
        EditText Phone = (EditText)findViewById(R.id.RegisterPhone);
        EditText FirstName = (EditText)findViewById(R.id.RegisterFirstName);
        EditText LastName = (EditText)findViewById(R.id.RegisterLastName);

        startActivity(new Intent(Register.this, Login.class));
    }
}
