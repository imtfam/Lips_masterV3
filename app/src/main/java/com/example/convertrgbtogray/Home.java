package com.example.convertrgbtogray;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static com.example.convertrgbtogray.R.id.imageButton4;

public class Home extends AppCompatActivity {

    Button btnHome;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btnHome = findViewById(imageButton4);
         btnHome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), Home.class);
                 startActivity(intent);
             }
         });
    }
}