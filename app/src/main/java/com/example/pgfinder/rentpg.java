package com.example.pgfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rentpg extends AppCompatActivity {
    Button upload;
    RecyclerView recyclerView2;

    ArrayList<ProjectModel> recycleList2;

    FirebaseDatabase firebaseDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentpg);
        recyclerView2=findViewById(R.id.recyclerView2);
        recycleList2=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();

        ProjectAdapter recyclerAdapter=new ProjectAdapter(recycleList2,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView2.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setAdapter(recyclerAdapter);

        firebaseDatabase.getReference().child("pg").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    ProjectModel projectModel=dataSnapshot.getValue(ProjectModel.class);

                    recycleList2.add(projectModel);
                }
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}