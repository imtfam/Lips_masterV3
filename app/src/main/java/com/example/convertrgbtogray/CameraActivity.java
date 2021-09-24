package com.example.convertrgbtogray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

public class CameraActivity extends AppCompatActivity {

   private String TAG = CameraActivity.class.getSimpleName();
    CameraView cv;
    ImageButton ibCamera, ibToggle, ibFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        cv = findViewById(R.id.camera_view);
        ibCamera = findViewById(R.id.capturePicture);
        ibToggle = findViewById(R.id.toggleCamera);

        ibCamera.setOnClickListener(this::onClick);
        ibToggle.setOnClickListener(this::onClick);

        cv.setLifecycleOwner(this);
        cv.addCameraListener(cl);
    }

    private CameraListener cl = new CameraListener() {
        @Override
        public void onPictureTaken(@NonNull PictureResult result) {
            super.onPictureTaken(result);

            Process.pictureResult = result;
            startActivity(new Intent(CameraActivity.this, Process.class));
        }

        @Override
        public void onCameraError(@NonNull CameraException exception) {
            super.onCameraError(exception);
            Toast.makeText(CameraActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCameraError: ", exception);
        }
    };

    public void onClick(View v) {
        if (v == ibCamera) {
            Toast.makeText(this, "please wait.....", Toast.LENGTH_SHORT).show();
            cv.takePicture();
        }
        if (v == ibToggle) {
            switch (cv.toggleFacing()) {
                case FRONT:
                    Toast.makeText(this, "Ganti ke kamera depan", Toast.LENGTH_SHORT).show();
                    break;
                case BACK:
                    Toast.makeText(this, "Ganti ke kamera belakang", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

                }
    }
