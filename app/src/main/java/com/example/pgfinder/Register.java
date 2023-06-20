package com.example.pgfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword,editTextPass;//Declaring objects
    Button buttonReg;
    FirebaseAuth mAuth;
    TextInputEditText getEditTextPassword;
    ProgressBar progressBar;
    TextView textView;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){


//        it current user is not equal to null we open the main activity


            Intent intent = new Intent(Register.this,com.example.pgfinder.Login.class);
            startActivity(intent);
            finish();

        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //        To access full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mAuth= FirebaseAuth.getInstance();

        //initialize objects
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonReg=findViewById(R.id.register);
        progressBar=findViewById(R.id.progressBar2);
        textView=findViewById(R.id.loginNow);
        editTextPass=findViewById(R.id.pass);

//        onClicklistner for the textview
        textView.setOnClickListener(view -> {
//                create an intent to open the login activity
            Intent intent= new Intent(Register.this, Login.class);
//                to start the activity we use startactivity and pass the object intent
            startActivity(intent);
//                to finish the activity we call finish();
            finish();

        });
//            button onClick listener
        buttonReg.setOnClickListener(view -> {
//                set the visibility for the progess bar
            progressBar.setVisibility(View.VISIBLE);
//                read the text from edittext
           String email,pass,password;
           email=String.valueOf(editTextEmail.getText());
           password=String.valueOf(editTextPassword.getText());
           pass=String.valueOf(editTextPass.getText());
//if email and password empty

                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(password)) {
                            Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                            return;
                        }
//            If password is smaller than 6 characters
                        if (Objects.requireNonNull(editTextPassword.getText()).toString().length() < 6) {

                            Toast.makeText(Register.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                            editTextPassword.requestFocus();

                        }
                        if (!password.equals(pass)) {


                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        }







            final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
//                                After the task is completed set the visibility to gone
                        progressBar.setVisibility(View.GONE);


                        if (task.isSuccessful()) {
                         Objects.requireNonNull(firebaseAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(task1 -> {


                             if (task1.isSuccessful()) {
                                 Toast.makeText(Register.this, "Email sent.Please verify your email id",
                                         Toast.LENGTH_SHORT).show();
                                 if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                     Toast.makeText(Register.this, "Email Verified",
                                             Toast.LENGTH_SHORT).show();

                                     Intent intent = new Intent(Register.this, Login.class);
                                     startActivity(intent);
                                     finish();




                                 }
                             } else {

                                 // If sign in fails, display a message to the user.


                                 Toast.makeText(Register.this, "Authentication failed or Acccount already exist",
                                         Toast.LENGTH_SHORT).show();

                             }


                         });}});});}
    //check if user is already login . In such case ,take the user to main page
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Register.this,Login.class));
        finish();

    }

}

