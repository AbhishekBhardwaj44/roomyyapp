package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();
//this take 4 sec to load NextActivity.
        Thread timer1 = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(4000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent= new Intent(SplashActivity.this,Login.class);
//                to start the activity we use startactivity and pass the object intent
                    startActivity(intent);
//                to finish the activity we call finish();
                    finish();

                }


            }
        };
        timer1.start();


    }
}