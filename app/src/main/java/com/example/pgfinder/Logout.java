package com.example.pgfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Logout extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;
    Activity logout;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        logout=getActivity();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


//        check if the user is null or not
        if (user == null) {
//            if the user is equal to null then open the login activity and close the mainActivity

            Intent intent = new Intent(logout, Login.class);
            startActivity(intent);


        }



        if (container!=null){
            container.removeAllViews();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        TextView textView=(TextView) logout.findViewById(R.id.user_detail);
        Button button=(Button) logout.findViewById(R.id.logout2) ;
            textView.setText(user.getEmail());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(logout,Login.class);
                startActivity(intent);
            }
        });
    }

}