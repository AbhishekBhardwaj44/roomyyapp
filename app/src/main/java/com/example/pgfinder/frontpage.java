package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class frontpage extends AppCompatActivity {
    CardView cardView,cardView2;
    RelativeLayout relativeLayout,relativeLayout2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);
        cardView=findViewById(R.id.ownerOption);
        cardView2=findViewById(R.id.renterOption);
     cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Navdraw2.class);
                startActivity(intent);
                finish();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),NavDraw.class);
                startActivity(intent);
                finish();

            }
        });
    }
}