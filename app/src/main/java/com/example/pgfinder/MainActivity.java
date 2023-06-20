package com.example.pgfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        To access full screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_detail);
//        initialize the current user
        user = auth.getCurrentUser();

//        check if the user is null or not
        if (user == null) {
//            if the user is equal to null then open the login activity and close the mainActivity

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        }

        else {
      textView.setText(user.getEmail());
       }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                in this we sign out the user
                FirebaseAuth.getInstance().signOut();
//                we have to close the current activity and open the login activity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();


            }

        });
    }
}
