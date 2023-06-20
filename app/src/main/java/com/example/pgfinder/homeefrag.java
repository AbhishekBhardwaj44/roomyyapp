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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.gms.ads.AdRequest;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class homeefrag extends Fragment {

    Activity homemain;
    SlideIten slideItem;
    CardView cardView;
    ViewPager2 viewPager2;
    private AdView adView;

    CardView cardView2;
    FloatingActionButton addroom,addpg,addf;
    ImageSlider imageSlider;
    private final Handler slideHandler = new Handler();


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homemain = getActivity();

        if (container != null) {
            container.removeAllViews();
        }


        View view = inflater.inflate(R.layout.fragment_homeefrag, container, false);

        adView=new AdView(getActivity());

        ViewPager2 viewPager2=(ViewPager2) homemain.findViewById(R.id.viewPagerr);

        List<SlideIten> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideIten(R.drawable.slider1));
        sliderItem.add(new SlideIten(R.drawable.slider3));
        sliderItem.add(new SlideIten(R.drawable.silder2));

        viewPager2.setAdapter(new SildeAdapter(sliderItem, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);


        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideerRunnable);
                slideHandler.postDelayed(slideerRunnable, 3000);
            }
        });
        return view;
    }
    private final Runnable slideerRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() +1);
        }
    };
    @Override
    public void onPause(){
        super.onPause();
        slideHandler.removeCallbacks(slideerRunnable,3000);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideerRunnable,3000);
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
