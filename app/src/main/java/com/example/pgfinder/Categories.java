package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Categories extends AppCompatActivity {
    //java file
    ImageView backBtn;
    CardView girls;
    CardView boys;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        backBtn=findViewById(R.id.back);
        boys=findViewById(R.id.boysOption);
        girls=findViewById(R.id.girlsOption);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Categories.super.onBackPressed();
            }

        });
        girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),girlshome.class);
                startActivity(intent);
                finish();
            }
        });
        boys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),boyshome.class);
                startActivity(intent);
                finish();
            }
        });

    }
}