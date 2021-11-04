package com.example.convertrgbtogray;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class MainActivity extends AppCompatActivity {

    ImageButton btnHome,btnCameraHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.imageButtonProcess);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Process.class);
                startActivity(intent);
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
}