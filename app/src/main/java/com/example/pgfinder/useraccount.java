package com.example.pgfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class useraccount extends Fragment {

    Activity account;
    // onlu use for relational database
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseFirestore fStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//to remove overlap of fragments on each other
        account = getActivity();
        if (container!=null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.fragment_useraccount, container, false);

    }

    public void onStart() {
        super.onStart();
        EditText email = (EditText) account.findViewById(R.id.email);
        EditText namee = (EditText) account.findViewById(R.id.fullname);
        EditText numberr = (EditText) account.findViewById(R.id.phone);
        EditText addresss = (EditText) account.findViewById(R.id.address);
        Button save = (Button) account.findViewById(R.id.save);
        fStore=FirebaseFirestore.getInstance();




       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               checkcredentials();

           }

            // this method is user too check data enter is valid or not
            private void checkcredentials() {

                // string get (input )input by user
                final String name = namee.getText().toString().trim();
                final String number = numberr.getText().toString().trim();
                final String address = addresss.getText().toString().trim();



                // msg me email is not empty
                if (name.isEmpty()) {
                    namee.setError("Enter correct email");
                }
                // password is not empty
                else if (number.isEmpty() || number.length() < 10) {
                    numberr.setError("enter correct number");
                } else if (address.isEmpty()) {
                    addresss.setError("enter address");
                } else {
                    database =FirebaseDatabase.getInstance();
                    reference =database.getReference("users");

                    final String names = namee.getText().toString().trim();
                    final String numbers = numberr.getText().toString().trim();
                    final String addresse = addresss.getText().toString().trim();


                    HelperClass helperClass= new HelperClass(names,numbers,addresse);
                    reference.child(names).setValue(helperClass);
                    datastore();
                }


            }
            private void datastore() {
                Map<String,String> v=new HashMap<>();
                v.put("name", namee.getText().toString());
                v.put("number", numberr.getText().toString());
                v.put("address", addresss.getText().toString());

                fStore.collection("user Data").add(v)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                namee.setText("");
                                numberr.setText("");
                                addresss.setText("");
                                Toast.makeText(account, "save data sucessfully", Toast.LENGTH_SHORT).show();
                            }
                        });
            }





        });







    }



}