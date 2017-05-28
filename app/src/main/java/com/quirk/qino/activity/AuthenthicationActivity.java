package com.quirk.qino.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qino.qino.R;
import com.quirk.qino.fragment.ForgotPasswordFragment;
import com.quirk.qino.fragment.LoginFragment;
import com.quirk.qino.fragment.RegisterFragment;

public class AuthenthicationActivity extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";

    private EditText input_UserName, input_Email, input_Password, input_Phone, input_Name;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    private Toolbar toolbar;

    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(AuthenthicationActivity.this, HomePage.class));
            finish();
        }

        setContentView(R.layout.activity_authenthication);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new LoginFragment())
                .commit();

        LoginFragment loginFragment = new LoginFragment();
        loginFragment.getActivity();

        //Not Used
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //end of onCreate

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signIn(View view) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AuthenthicationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {
            // myView.findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Signed In!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AuthenthicationActivity.this, HomePage.class));
            finish();
        } else {
            // myView.findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }


    //Login Page Code Start

    public void login_btn_SignInClicked(View view) {
        input_UserName= (EditText) findViewById(R.id.login_input_UserName);
        input_Password = (EditText) findViewById(R.id.login_input_Password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String email = input_UserName.getText().toString();
        final String password = input_Password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(AuthenthicationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                input_Password.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(AuthenthicationActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(AuthenthicationActivity.this, HomePage.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    public void login_btn_SignUpClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    public void login_btn_ForgotPasswordClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new ForgotPasswordFragment())
                .addToBackStack(null)
                .commit();
    }

    //End Of Login Code


    //Register Page Code Start

    public void register_btn_ForgotPasswordClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Login_Frame, new ForgotPasswordFragment())
                .addToBackStack(null)
                .commit();
    }

    public void register_btn_RegisterClicked(View view) {
       /* RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.getActivity();
        registerFragment.CreateAccount(view);*/

        input_Email = (EditText) findViewById(R.id.register_input_Email);
        input_Password = (EditText) findViewById(R.id.register_input_Password);
        input_Phone = (EditText) findViewById(R.id.register_input_Phone);
        input_Name = (EditText) findViewById(R.id.register_input_Name);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        final String email = input_Email.getText().toString().trim();
        final String password = input_Password.getText().toString().trim();
        final String name =  input_Name.getText().toString().trim();
        final String phone =  input_Phone.getText().toString().trim();


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
                .addOnCompleteListener(AuthenthicationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AuthenthicationActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(AuthenthicationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(AuthenthicationActivity.this, HomePage.class));
                            writeNewUser(task.getResult().getUser().getUid(), name, email, phone);
                            finish();
                        }
                    }
                });
    }

    private void writeNewUser(String userId, String name, String email, String phone) {
        //User user = new User(name, email);
        mDatabase.child("users").child(userId).child("Name").setValue(name);
        mDatabase.child("users").child(userId).child("Email").setValue(email);
        mDatabase.child("users").child(userId).child("PhoneNumber").setValue(phone);
    }

    @Override
    protected void onResume() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    //Register End Of Code

    //ForgotPassword Page

    public void forgotpassword_btn_ResetPasswordClicked(View view) {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        input_Email = (EditText) findViewById(R.id.forgotpassword_input_Email);

        String email = input_Email.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AuthenthicationActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AuthenthicationActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    public void forgotpassword_btn_BackClicked(View view) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }
}