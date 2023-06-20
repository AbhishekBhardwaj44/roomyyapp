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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextInputEditText editTextEmail;
    Button forgot;
    TextView textView;

    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        editTextEmail = findViewById(R.id.email);
        forgot = findViewById(R.id.forgot);
        textView = findViewById(R.id.registerNow);
        progressBar = findViewById(R.id.progressBar2);
        auth=FirebaseAuth.getInstance();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
//                to start the activity we use startactivity and pass the object intent
                startActivity(intent);
//                to finish the activity we call finish();
                finish();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                set the visibility for the progess bar
                progressBar.setVisibility(View.VISIBLE);
//                read the text from edittext
                String email;
                email = String.valueOf(editTextEmail.getText());

//if email and password empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "Check your Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgotPasswordActivity.this, Login.class));
        finish();

    }}