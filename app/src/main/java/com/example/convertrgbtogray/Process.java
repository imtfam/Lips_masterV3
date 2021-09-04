package com.example.convertrgbtogray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.PictureResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


public class Process extends AppCompatActivity {

    public static PictureResult pictureResult = null;
    Button colorButtonRed, colorButtonRed2, colorButtonRed3,button_Matte,button_Satin,button_Amplified,button_Lustre,button_Chemesheen;
    ImageView iv;
    String ColorRed1, ColorRed2;
    //take bitmap and bitmap drawable to get image form image view
    BitmapDrawable drawable;
    Bitmap bitmap;
    ImageButton btnBack;
    Dialog noDialog;
    String imageString="",colorText = "";

    private static final int BUFFER_SIZE = 1024 * 8;
    private String TAG = MainActivity.class.getSimpleName();

    ///////////////iv
    ImageView imageView;
    ImageView originalImageView;
    ImageButton btnSave;
        ///////////////about gallery
    public static final int REQUEST_GALLERY = 1;

    //Bitmap bitmap;
    ///////////////

    ///////////////about camera
    public static final int REQUEST_CAMERA = 2;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        PictureResult result = pictureResult;

        RelativeLayout Re = findViewById(R.id.Reletive_Process);
        View v = LayoutInflater.from(Re.getContext())
                .inflate(R.layout.activity_color, Re, false);
        Re.addView(v);


        iv = (ImageView)findViewById(R.id.imageView_show);
        originalImageView = new ImageView(this);
        if (result != null) {
            try {
                result.toBitmap(new BitmapCallback() {
                    @Override
                    public void onBitmapReady(@Nullable Bitmap bitmap) {
                        iv.setImageBitmap(bitmap);
                        originalImageView.setImageDrawable(iv.getDrawable());
                    }
                });
            } catch (UnsupportedOperationException e) {
                Log.e(TAG, "onCreate: ", e);
            }
        }else {
            originalImageView.setImageDrawable(iv.getDrawable());

        }
              ///////////////////////////////////////////Button_color
        button_Matte = findViewById(R.id.button_Matte);
        button_Satin = findViewById(R.id.button_Satin);
        button_Amplified = findViewById(R.id.button_Amplified);
        button_Lustre = findViewById(R.id.button_Lustre);
        button_Chemesheen = findViewById(R.id.button_Chemesheen);
        HorizontalScrollView scrollView_Matte = findViewById(R.id.scrollview_Matte);
        HorizontalScrollView scrollView_Satin = findViewById(R.id.scrollview_Satin);
        HorizontalScrollView scrollView_Amplified = findViewById(R.id.scrollview_Amplified);
        HorizontalScrollView scrollView_Lustre= findViewById(R.id.scrollview_Lustre);
        HorizontalScrollView scrollView_Chemesheen = findViewById(R.id.scrollview_Cremesheen);
        scrollView_Satin.setVisibility(View.INVISIBLE);
        scrollView_Amplified.setVisibility(View.INVISIBLE);
        scrollView_Lustre.setVisibility(View.INVISIBLE);
        scrollView_Chemesheen.setVisibility(View.INVISIBLE);
        scrollView_Satin.setVisibility(View.INVISIBLE);
        button_Matte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              scrollView_Matte.setVisibility(View.VISIBLE);
              scrollView_Satin.setVisibility(View.INVISIBLE);
              scrollView_Amplified.setVisibility(View.INVISIBLE);
              scrollView_Lustre.setVisibility(View.INVISIBLE);
              scrollView_Chemesheen.setVisibility(View.INVISIBLE);

            }
        });

        button_Satin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView_Satin.setVisibility(View.VISIBLE);
                scrollView_Matte.setVisibility(View.INVISIBLE);
                scrollView_Amplified.setVisibility(View.INVISIBLE);
                scrollView_Lustre.setVisibility(View.INVISIBLE);
                scrollView_Chemesheen.setVisibility(View.INVISIBLE);


            }
        });
        button_Amplified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView_Amplified.setVisibility(View.VISIBLE);
                scrollView_Satin.setVisibility(View.INVISIBLE);
                scrollView_Matte.setVisibility(View.INVISIBLE);
                scrollView_Lustre.setVisibility(View.INVISIBLE);
                scrollView_Chemesheen.setVisibility(View.INVISIBLE);

            }
        });
        button_Lustre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView_Lustre.setVisibility(View.VISIBLE);
                scrollView_Satin.setVisibility(View.INVISIBLE);
                scrollView_Matte.setVisibility(View.INVISIBLE);
                scrollView_Amplified.setVisibility(View.INVISIBLE);
                scrollView_Chemesheen.setVisibility(View.INVISIBLE);


            }
        });
        button_Chemesheen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView_Chemesheen.setVisibility(View.VISIBLE);
                scrollView_Satin.setVisibility(View.INVISIBLE);
                scrollView_Matte.setVisibility(View.INVISIBLE);
                scrollView_Amplified.setVisibility(View.INVISIBLE);
                scrollView_Lustre.setVisibility(View.INVISIBLE);


            }
        });

        /////////////////////////////////////////// BackHome
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        ///////////////////////////////////////////open gallery
        ImageButton gallery_button = findViewById(R.id.open_gellery);
        gallery_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);
            }
        });

        ///////////////////////////////////////////open camera
        ImageButton buttonIntent = findViewById(R.id.btn_camera);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               startActivity(new Intent(Process.this, CameraActivity.class));
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////
        //submit
        colorButtonRed = (Button)findViewById(R.id.Peachy_New_Year);
        colorButtonRed2 = (Button)findViewById(R.id.Chili);
        colorButtonRed3 = (Button)findViewById(R.id.Russian_Red);
        noDialog = new Dialog(this);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        /////////////////////////////////////////////red1
        final Python py = Python.getInstance();

        colorButtonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv.setImageDrawable(originalImageView.getDrawable());
                Progress progress = new Progress(Process.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.show();

                            }
                        });
                        //get image from image view

                        drawable = (BitmapDrawable)iv.getDrawable();
                        bitmap = drawable.getBitmap();
                        imageString = getStringImage(bitmap);

                        //imageString we get encoded iamge string
                        //pass this string in python script

                        //call .py file
                        PyObject pyo = py.getModule("lips");
                        //call module in .py file
                        PyObject obj = pyo.callAttr("main",imageString,String.valueOf(colorButtonRed.getText()));
                        //return value
                        String str = obj.toString();
                        if(str.equals("null") ){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    openNodialog();
                                    Toast.makeText(Process.this,"\n" + "Please select a new image.",Toast.LENGTH_SHORT).show();
                                }
                                private void openNodialog() {
                                    noDialog.setContentView(R.layout.no_face_dialog);
                                    noDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                    ImageView imageDialog = noDialog.findViewById(R.id.imageNodialog);
                                    Button btnOK = noDialog.findViewById(R.id.btn_no_face);

                                    btnOK.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            noDialog.dismiss();
                                            /*Toast.makeText(Process.this,"Button OK", Toast.LENGTH_SHORT).show();*/
                                        }
                                    });
                                    noDialog.show();

                                }
                            });

                        }
                        else{
                            //convert bytearray
                            byte[]data = Base64.decode(str, Base64.DEFAULT);
                            //conver to bitmap
                            Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                            ////////////Show Process
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.dismiss();
                                    //set this bitmap to imageView2
                                    iv.setImageBitmap(bmp);
                                }
                            });

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                //set this bitmap to imageView2
                            }
                        });
                    }
                }).start();
            }
        });
        ///////////////////////////////////////////////
        ///////////////////////////////////////////////red2
        colorButtonRed2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv.setImageDrawable(originalImageView.getDrawable());
                Progress progress = new Progress(Process.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.show();

                            }
                        });

                        //get image from image view

                        drawable = (BitmapDrawable)iv.getDrawable();
                        bitmap = drawable.getBitmap();
                        imageString = getStringImage(bitmap);

                        ////////////////////save image



                        //imageString we get encoded iamge string
                        //pass this string in python script

                        //call .py file
                        PyObject pyo = py.getModule("lips");
                        //call module in .py file
                        PyObject obj = pyo.callAttr("main",imageString,String.valueOf(colorButtonRed2.getText()));
                        //return value
                        String str = obj.toString();
                        if(str.equals("null") ){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Process.this,"No Face!!!",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            //convert bytearray
                            byte[]data = android.util.Base64.decode(str, Base64.DEFAULT);
                            //conver to bitmap
                            Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                            ////////////Show Process
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.dismiss();
                                    //set this bitmap to imageView2
                                    iv.setImageBitmap(bmp);
                                }
                            });

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                //set this bitmap to imageView2
                            }
                        });
                    }
                }).start();
            }
        });

        /////////////////////////////////////red3
        colorButtonRed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageDrawable(originalImageView.getDrawable());
                Progress progress = new Progress(Process.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.show();

                            }
                        });

                        //get image from image view

                        drawable = (BitmapDrawable)iv.getDrawable();
                        bitmap = drawable.getBitmap();
                        imageString = getStringImage(bitmap);

                        //imageString we get encoded iamge string
                        //pass this string in python script

                        //call .py file
                        PyObject pyo = py.getModule("lips");
                        //call module in .py file
                        PyObject obj = pyo.callAttr("main",imageString,String.valueOf(colorButtonRed2.getText()));
                        //return value
                        String str = obj.toString();
                        if(str.equals("null") ){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Process.this,"No Face!!!",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            //convert bytearray
                            byte[]data = android.util.Base64.decode(str, Base64.DEFAULT);
                            //conver to bitmap
                            Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                            ////////////Show Process
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.dismiss();
                                    //set this bitmap to imageView2
                                    iv.setImageBitmap(bmp);
                                }
                            });

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                //set this bitmap to imageView2
                            }
                        });
                    }
                }).start();
            }
        });


        ///////////////////////////Camera

                ////////////////////////Save Image
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable_save = (BitmapDrawable)iv.getDrawable();
                Bitmap bitmap_save = drawable_save.getBitmap();
                SaveImageGallery(bitmap_save);
            }
        });
        }

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        //store in bytearray
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                iv.setImageBitmap(bitmap);
                originalImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            getContentResolver().notifyChange(uri, null);
            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, uri);
                iv.setImageBitmap(bitmap);
                originalImageView.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext()
                        , uri.getPath(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
           }

    /*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            getContentResolver().notifyChange(uri, null);
            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, uri);
                imageView.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext()
                        , uri.getPath(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
    private void SaveImageGallery(Bitmap bitmap_save){
        OutputStream outputStream;
        try {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                ContentResolver con_resolver = getContentResolver();
                ContentValues con_Values = new ContentValues();
                con_Values.put(MediaStore.MediaColumns.DISPLAY_NAME,"Image_"+"jpg");
                con_Values.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+File.separator+getString(R.string.app_name));
                Uri saveimageUri = con_resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,con_Values);

                outputStream = con_resolver.openOutputStream(Objects.requireNonNull(saveimageUri));
                bitmap_save.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                Objects.requireNonNull(outputStream);
                Toast.makeText(Process.this,"Save success",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(Process.this,"No save ",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}