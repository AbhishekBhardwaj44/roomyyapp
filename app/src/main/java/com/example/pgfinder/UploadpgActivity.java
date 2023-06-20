package com.example.pgfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class UploadpgActivity extends AppCompatActivity {

    TextView location, name, sharing, attachedB, price, available, contnumber;
    ImageView uploadbtn, uploadImage;
    Button submit;

    Uri ImageUri;

    RelativeLayout relativeLayout;


    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    ProgressDialog dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_rooms);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
//        dialog.setTitle("Room Uploading");
        dialog.setCanceledOnTouchOutside(false);

        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        sharing = findViewById(R.id.sharing);
        attachedB = findViewById(R.id.attachedB);
        available = findViewById(R.id.available);
        price = findViewById(R.id.price);
        contnumber = findViewById(R.id.contnumber);
        uploadbtn = findViewById(R.id.uploadbtn);
        uploadImage = findViewById(R.id.uploadImage);
        submit = findViewById(R.id.submit);
        relativeLayout = findViewById(R.id.relativeUpload);

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.GONE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namee,pricee,locationn,sharingg,attachedd,contact,availablee;

                namee=String.valueOf(name.getText());
                pricee=String.valueOf(price.getText());
                locationn=String.valueOf(location.getText());
                sharingg=String.valueOf(sharing.getText());
                attachedd=String.valueOf(attachedB.getText());
                availablee=String.valueOf(available.getText());
                contact=String.valueOf(contnumber.getText());

                if (TextUtils.isEmpty(namee)) {
                    name.setError("email is empty");


                }  if (TextUtils.isEmpty(pricee)) {
                    price.setError("price is empty");

                }
                if (TextUtils.isEmpty(locationn)) {
                    location.setError("Enter Location");

                }
                if (TextUtils.isEmpty(sharingg)) {
                    sharing.setError("");

                }
                if (TextUtils.isEmpty(attachedd)) {
                    attachedB.setError("please enter");


                }
                if (TextUtils.isEmpty(availablee)) {
                    available.setError("this can't be empty");


                }
                if (TextUtils.isEmpty(contact)) {
                    contnumber.setError("Enter contact number");

                }
                else {
                    dialog.show();


                    final StorageReference reference = firebaseStorage.getReference().child("pg")
                            .child(System.currentTimeMillis() + "");

                    reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ProjectModel model = new ProjectModel();
                                    model.setUploadImage(uri.toString());
                                    model.setName(name.getText().toString());
                                    model.setLocation(location.getText().toString());
                                    model.setPrice(price.getText().toString());
                                    model.setAvailable(available.getText().toString());
                                    model.setContnumber(contnumber.getText().toString());
                                    model.setAttachedB(attachedB.getText().toString());
                                    model.setSharing(sharing.getText().toString());

                                    database.getReference().child("pg").push().setValue(model)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    Toast.makeText(UploadpgActivity.this, "Room Upload Successfully", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    dialog.dismiss();
                                                    Toast.makeText(UploadpgActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    });


                }
            }});

    }

    private void UploadImage() {

        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        Toast.makeText(UploadpgActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        uploadbtn.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();

                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {

            assert data != null;
            ImageUri = data.getData();
            uploadImage.setImageURI(ImageUri);


        }
    }
}