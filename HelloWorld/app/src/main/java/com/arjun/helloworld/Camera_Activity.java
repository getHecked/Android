package com.arjun.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera_Activity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    private Button button;
    private Button toParent;
    private ImageView imgView;
    private Uri img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_);

        imgView = findViewById(R.id.imageView);
        button = findViewById(R.id.imageButton);
        toParent = findViewById(R.id.backButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                    String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //shows popup asking for permission if denied before
                    requestPermissions(permission,PERMISSION_CODE);
                }
                else{
                    openCamera();
                }
            }
        });

        toParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Camera_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openCamera() {
        //config for images
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From camera app");
        img_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,img_uri);
        startActivityForResult(intent,IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         //called when user clicks yes or no on permission prompt
        switch (requestCode){
            case PERMISSION_CODE:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT);
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //called when image is captured from camera
        if(resultCode==RESULT_OK){
            imgView.setImageURI(img_uri);
        }
    }
}
