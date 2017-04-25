package com.get_table.biwiremob17_l6bc_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private EditText Input_REmail, Input_RPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new LoginFragment())
                .addToBackStack(null)
                .commit();

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        Input_REmail = (EditText) findViewById(R.id.Input_REmail);
        Input_RPassword = (EditText) findViewById(R.id.Input_RPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

    }

    //Login Page

    public void SignInClicked(View view) {

    }

    public void SignUpOnPressed(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }


    //Register Page

    public void ForgotPasswordClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    public void LoginClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new LoginFragment())
                .addToBackStack(null)
                .commit();
        //finish();
    }

    public void RegisterClicked(View view) {
       /* RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.getActivity();
        registerFragment.CreateAccount(view);*/

        Input_REmail = (EditText) findViewById(R.id.Input_REmail);
        Input_RPassword = (EditText) findViewById(R.id.Input_RPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String email = Input_REmail.getText().toString().trim();
        String password = Input_RPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Login.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(Login.this, HomePage.class));
                            finish();
                        }
                    }
                });
    }


    //ForgotPassword Page

    public void ResetPasswordClicked(View view) {
    }
}






