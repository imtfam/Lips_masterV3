package com.example.convertrgbtogray;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHome,btnCameraHome;
    public static final int REQUEST_GALLERY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.imageButtonProcess);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GALLERY);


               /* Intent intent = new Intent(getApplicationContext(), Process.class);
                startActivity(intent);*/
            }

        });
        btnCameraHome = findViewById(R.id.imageButtonCam);
        btnCameraHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2  = new Intent(getApplicationContext(),CameraActivity.class);
                startActivity(intent2);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Intent intent = new Intent(MainActivity.this, Process.class);
                intent.putExtra("imageGallery",uri);
                startActivity(intent);

        }
    }
}