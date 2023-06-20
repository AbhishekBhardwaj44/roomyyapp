package com.example.pgfinder;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;


import android.annotation.SuppressLint;

import android.provider.DocumentsContract;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class NavDraw extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private int ROOT_FRAGMENT_TAG;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
//        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("Exit app");
//        alertDialog.setIcon(R.drawable.baseline_account_circle_24);
//        alertDialog.setMessage("Are you sure to exit the app");
//        alertDialog.show();
        setContentView(R.layout.activity_navdraw);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setCheckedItem(R.id.home_menu);
        loadFragment(new homeefrag(),0);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.home_menu) {

                    loadFragment(new homeefrag(),0);



                } else if (id==R.id.about) {

                    loadFragment(new useraccount(),1);

                }
                 else if (id==R.id.logout) {

                    loadFragment(new Logout(),1);

                }
                else if (id==R.id.my_profile) {

                    loadFragment(new myprofile(),1);

                }


                else{
                    loadFragment(new About(),1);

                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });



//set navigation options clickable
//        navigationView.bringToFront();




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


    private void loadFragment(Fragment fragment,int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0) {
            ft.add(R.id.container, fragment);
          fm.popBackStack(String.valueOf(ROOT_FRAGMENT_TAG),FragmentManager.POP_BACK_STACK_INCLUSIVE);
          ft.addToBackStack(String.valueOf(ROOT_FRAGMENT_TAG));
        } else {
            ft.replace(R.id.container,fragment);
            ft.addToBackStack(null);

        }

        ft.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return true;
    }

}


