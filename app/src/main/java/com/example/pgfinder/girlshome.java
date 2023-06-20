package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.C;

public class girlshome extends AppCompatActivity {
    CardView cardView,cardView2,cardView3,cardView4;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,relativeLayout4;
    ImageView imageView;
    TextView textView12,textView3,textView4,textView5,textView;
    SearchView searchView;

    Animation anim_from_button,anim_from_top,anim_from_left;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girlshome);
        cardView=findViewById(R.id.cardView);
        cardView2=findViewById(R.id.cardView2);
        cardView3=findViewById(R.id.cardView3);
        searchView=findViewById(R.id.searchView);
        textView=findViewById(R.id.firstText);
        relativeLayout=findViewById(R.id.relative);
        relativeLayout2=findViewById(R.id.relative2);
        relativeLayout3=findViewById(R.id.relative3);
        relativeLayout4=findViewById(R.id.relative4);



//        Load Animation
        anim_from_button= AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom);
        anim_from_top= AnimationUtils.loadAnimation(this,R.anim.anim_from_top);
        anim_from_left= AnimationUtils.loadAnimation(this,R.anim.anim_from_left);

//        set animation

//        cardView.setAnimation(anim_from_button);
//        cardView2.setAnimation(anim_from_button);
//        cardView3.setAnimation(anim_from_button);
//        cardView4.setAnimation(anim_from_button);
        searchView.setAnimation(anim_from_left);
        textView.setAnimation(anim_from_top);
        relativeLayout.setAnimation(anim_from_button);
        relativeLayout2.setAnimation(anim_from_button);
        relativeLayout3.setAnimation(anim_from_button);
        relativeLayout4.setAnimation(anim_from_button);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(girlshome.this, Categories.class));
        finish();

    }
}