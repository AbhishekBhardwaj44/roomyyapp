package com.example.pgfinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class owner_home extends Fragment {

    Activity homemain;
    SlideIten slideItem;
    CardView cardView;
    CardView cardView2;
    FloatingActionButton addroom,addpg,addf;
    ImageSlider imageSlider;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homemain = getActivity();
        if (container != null) {
            container.removeAllViews();
        }


        View view = inflater.inflate(R.layout.fragment_owner_home, container, false);

        imageSlider = view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.slider4,null));
        imageList.add(new SlideModel(R.drawable.slider5,null));
        imageList.add(new SlideModel(R.drawable.slider3,null));
        imageSlider.setImageList(imageList);
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback=new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("roomy");
                builder.setMessage("Are you sure you want to exit?");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getActivity().finish();

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);

    }

    @Override
    public void onStart() {
        super.onStart();
        CardView cardView = (CardView) homemain.findViewById(R.id.cardView);
        CardView cardView11 = (CardView) homemain.findViewById(R.id.cardView2);
        CardView cardView3 = (CardView) homemain.findViewById(R.id.cardView3);
        FloatingActionButton addroom=(FloatingActionButton)homemain.findViewById(R.id.addroom) ;
        FloatingActionButton addpg=(FloatingActionButton)homemain.findViewById(R.id.addpg) ;
        FloatingActionButton addf=(FloatingActionButton)homemain.findViewById(R.id.addf) ;



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homemain, rentrooms.class);
                startActivity(intent);
            }
        });
      cardView11.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(homemain,rentpg.class);
              startActivity(intent);
          }
      });

        addroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homemain,roomadd.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homemain,rentflats.class);
                startActivity(intent);
            }
        });
        addpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homemain,pgadd.class);
                startActivity(intent);
            }
        });
        addf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homemain,flatadd.class);
                startActivity(intent);
            }
        });
    }

}
