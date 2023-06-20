package com.example.pgfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;//Declaring objects
    Button buttonLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    TextView textView2;

//    When initializing your Activity, check to see if the user is currently signed in

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        it current user is not equal to null we open the main activity
        if(currentUser != null){
            Intent intent= new Intent(Login.this, com.example.pgfinder.Navdraw2.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        To access full screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
        setContentView(R.layout.activity_login);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                    }
                });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAuth= FirebaseAuth.getInstance();

        //initialize objects
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.login);
        progressBar=findViewById(R.id.progressBar2);
        textView=findViewById(R.id.registerNow);
        textView2=findViewById(R.id.logforgot);

//        onclicklistner for the textview
        textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
//                create an intent to open the login activity
                                            Intent intent = new Intent(getApplicationContext(), Register.class);
//                to start the activity we use startactivity and pass the object intent
                                            startActivity(intent);
//                to finish the activity we call finish();
                                            finish();

                                        }
                                    });

//                onclicklistner for the textview
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                create an intent to open the login activity
                Intent intent= new Intent(getApplicationContext(),ForgotPasswordActivity.class);
//                to start the activity we use startactivity and pass the object intent
                startActivity(intent);
//                to finish the activity we call finish();
                finish();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
//                read the text from edittext
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
//if email and password empty
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("email is empty");

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.equals(mAuth)) {


                }


//            call a funtion
//            When a user signs in to your app, pass the user's email address and password to signInWithEmailAndPassword

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {


                                        Toast.makeText(getApplicationContext(), "Verify that you are a human", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Captcha.class);
//                                    This will close the login and open the mainActivity
                                        startActivity(intent);
                                        finish();


                                } else {

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

}