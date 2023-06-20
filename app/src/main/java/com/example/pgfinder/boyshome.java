package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class boyshome extends AppCompatActivity {

    CardView cardView,cardView2,cardView3;
    ImageView imageView;
    TextView textView12,textView3,textView4,textView5,textView;
    SearchView searchView;

    Animation anim_from_button,anim_from_top,anim_from_left;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boyshome);

        cardView=findViewById(R.id.cardViewb);
        cardView2=findViewById(R.id.cardViewb2);
        cardView3=findViewById(R.id.cardViewb3);
        searchView=findViewById(R.id.searchView);
        textView=findViewById(R.id.firstText);
        textView12=findViewById(R.id.secondText);

//        Load Animation
        anim_from_button= AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom);
        anim_from_top= AnimationUtils.loadAnimation(this,R.anim.anim_from_top);
        anim_from_left= AnimationUtils.loadAnimation(this,R.anim.anim_from_left);

//        set animation

        cardView.setAnimation(anim_from_button);
        cardView2.setAnimation(anim_from_button);
        cardView3.setAnimation(anim_from_button);
        searchView.setAnimation(anim_from_left);
        textView.setAnimation(anim_from_top);
        textView12.setAnimation(anim_from_top);



    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(boyshome.this,Categories.class));
        finish();

    }
}
