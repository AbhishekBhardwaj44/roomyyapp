package com.example.pgfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Captcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
//intialize variable
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
//    put sitekey as a string
    String Sitekey="6LdVytMkAAAAANfwAG9KhS_ZEZpC8wAh9Hzo2BgG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_captcha);
//        Assign variable
        checkBox=findViewById(R.id.checkbox);

//    Create google api client
        googleApiClient=new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Captcha.this)
                .build();
        googleApiClient.connect();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                set if condition when checkbox checked
                if (checkBox.isChecked()){
                    GoogleApiClient GoogleApiClient;
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,Sitekey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status=recaptchaTokenResult.getStatus();
                                    if (status.isSuccess()){
//                                        Display Successful message
                                        Toast.makeText(Captcha.this, "Successfully Verified", Toast.LENGTH_SHORT).show();
//                                        Change checkbox color
                                        checkBox.setTextColor(Color.GREEN);
                                        Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(),Navdraw2.class);
//                                    This will close the login and open the mainActivity
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
                else {
//                    Default checkbox color
                    checkBox.setTextColor(Color.BLACK);
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Captcha.this,Captcha.class));
        finish();

    }

}