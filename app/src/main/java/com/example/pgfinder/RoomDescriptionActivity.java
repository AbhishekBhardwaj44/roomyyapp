package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RoomDescriptionActivity extends AppCompatActivity {

    TextView singleSharing,singleContact,singleAttached,singleLocation,singlePrice,singleHeadline;
    ImageView singleImage;
    Animation anim_from_button,anim_from_top,anim_from_left;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_description);

        singleAttached=findViewById(R.id.singleAttached);
        singleContact=findViewById(R.id.singleContact);
        singleLocation=findViewById(R.id.singleLocation);
        singlePrice=findViewById(R.id.singlePrice);
        singleSharing=findViewById(R.id.singleSharing);
        singleHeadline=findViewById(R.id.singleHeadline);
        singleImage=findViewById(R.id.singleImage);
        anim_from_button= AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom);
        anim_from_top= AnimationUtils.loadAnimation(this,R.anim.anim_from_top);
        anim_from_left= AnimationUtils.loadAnimation(this,R.anim.anim_from_left);
        singleImage.setAnimation(anim_from_left);
        singleHeadline.setAnimation(anim_from_left);
        singleSharing.setAnimation(anim_from_left);
        singlePrice.setAnimation(anim_from_left);
        singleLocation.setAnimation(anim_from_left);
        singleContact.setAnimation(anim_from_left);
        singleAttached.setAnimation(anim_from_left);
        button=findViewById(R.id.booknow);


        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.imageloading)
                .into(singleImage);

        singleHeadline.setText(getIntent().getStringExtra("singleHeadline"));
        singleAttached.setText(getIntent().getStringExtra("singleAttached"));
        singleContact.setText(getIntent().getStringExtra("singleContact"));
        singleLocation.setText(getIntent().getStringExtra("singleLocation"));
        singlePrice.setText(getIntent().getStringExtra("singlePrice"));
        singleSharing.setText(getIntent().getStringExtra("singleSharing"));

    }
}