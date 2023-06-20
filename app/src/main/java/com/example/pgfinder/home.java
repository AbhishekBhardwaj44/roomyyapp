package com.example.pgfinder;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;


import android.annotation.SuppressLint;

import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ViewPager2 viewPager2;
    RelativeLayout relativeLayout;

//    implementing autoslide facility



    private final Handler slideHandler = new Handler();








    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        viewPager2 = findViewById(R.id.viewPagerr);
        relativeLayout=findViewById(R.id.rel);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.about){

                    loadFragment(new useraccount());






                } else if (id==R.id.home_menu) {

                    loadFragment(new myprofile());



                }else {

                    loadFragment(new myprofile());


                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });



//set navigation options clickable
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        relativeLayout.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        });



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
    }
    private final Runnable slideerRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() +1);
        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        slideHandler.removeCallbacks(slideerRunnable,3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideerRunnable,3000);
    }
//        when we click on side menu , side menu will open



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);


        } else {
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
       ft.add(R.id.container,fragment);
       ft.commit();
    }





}


