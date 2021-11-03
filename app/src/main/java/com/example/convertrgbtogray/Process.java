package com.example.convertrgbtogray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.ortiz.touchview.TouchImageView;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.PictureResult;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Process extends AppCompatActivity {

    RecyclerView mList1,mList;
    List<App> appList1,appListColor;
    Button button_Matte,button_Satin,button_Amplified,button_Lustre,button_Chemesheen;
     public static PictureResult pictureResult = null;
    Button colorButtonRed, colorButtonRed2, colorButtonRed3;
    ImageView iv;
       //take bitmap and bitmap drawable to get image form image view
    BitmapDrawable drawable;
    Bitmap bitmap;
    ImageButton btnBack;
    Dialog noDialog;
    String imageString="";

    private static final int BUFFER_SIZE = 1024 * 8;
    private String TAG = MainActivity.class.getSimpleName();

    ///////////////iv
    ImageView imageView;
    ImageView originalImageView;
    ImageButton btnSave,btnshare;
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

        RelativeLayout Re_process = findViewById(R.id.Reletive_Process);
        View v = LayoutInflater.from(Re_process.getContext())
                .inflate(R.layout.activity_color_recycleview, Re_process, false);
        Re_process.addView(v);


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
        mList = findViewById(R.id.list2);
        appListColor = new ArrayList<>();
        appListColor.add(new App("#9B381E","Marrakesh","#9B381E"));
        appListColor.add(new App("#A71E0B","Chili","#A71E0B"));
        appListColor.add(new App("#B3181D","Russian red","#B3181D"));
        appListColor.add(new App("#E8181D","Red rock","#E8181D"));
        appListColor.add(new App("#691B1C","Sin","#691B1C"));
        appListColor.add(new App("#671B25","Diva","#671B25"));
        appListColor.add(new App("#7D508A","Punk couture","#7D508A"));
        appListColor.add(new App("#F75753","Tropic tonic","#F75753"));
        appListColor.add(new App("#B76449","Taste me","#B76449"));
        appListColor.add(new App("#D27A66","Velvet teddy","#D27A66"));
        appListColor.add(new App("#AE6C5B","Taupe","#AE6C5B"));
        appListColor.add(new App("#CF9688","Honeylove","#CF9688"));
        appListColor.add(new App("#C48075","Kinda sexy","#C48075"));
        appListColor.add(new App("#945246","Whirl","#945246"));
        appListColor.add(new App("#8AD8FE","Dreampot","#8AD8FE"));
        appListColor.add(new App("#FF5590","Candy Yum-Yum","#FF5590"));
        appListColor.add(new App("#964546","Soar","#964546"));
        appListColor.add(new App("#F15D73","Please me","#F15D73"));
        appListColor.add(new App("#B483C2","4EVA","#B483C2"));
        appListColor.add(new App("#E97AE5","Lavender Jade","#E97AE5"));
        appListColor.add(new App("#846164","Lightly Charred","#846164"));
        appListColor.add(new App("#ECC3C8","Lazy Lullaby","#ECC3C8"));
        appListColor.add(new App("#876564","Deep Rooted","#876564"));
        appListColor.add(new App("#F9F6F0","Frosting","#F9F6F0"));
        appListColor.add(new App("#52413E","Inmy Fashion","#52413E"));

        LinearLayoutManager manager2 = new LinearLayoutManager(Process.this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList.setLayoutManager(manager2);

        CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
        mList.setAdapter(adaptor2);


        button_Matte = findViewById(R.id.button_Matte);
        button_Satin = findViewById(R.id.button_Satin);
        button_Amplified = findViewById(R.id.button_Amplified);
        button_Lustre = findViewById(R.id.button_Lustre);
        button_Chemesheen = findViewById(R.id.button_Chemesheen);

        button_Matte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList1 = findViewById(R.id.list2);
                appList1 = new ArrayList<>();
                appList1.add(new App("#9B381E","Marrakesh","#9B381E"));
                appList1.add(new App("#A71E0B","Chili","#A71E0B"));
                appList1.add(new App("#B3181D","Russian red","#B3181D"));
                appList1.add(new App("#E8181D","Red rock","#E8181D"));
                appList1.add(new App("#691B1C","Sin","#691B1C"));
                appList1.add(new App("#671B25","Diva","#671B25"));
                appList1.add(new App("#7D508A","Punk couture","#7D508A"));
                appList1.add(new App("#F75753","Tropic tonic","#F75753"));
                appList1.add(new App("#B76449","Taste me","#B76449"));
                appList1.add(new App("#D27A66","Velvet teddy","#D27A66"));
                appList1.add(new App("#AE6C5B","Taupe","#AE6C5B"));
                appList1.add(new App("#CF9688","Honeylove","#CF9688"));
                appList1.add(new App("#C48075","Kinda sexy","#C48075"));
                appList1.add(new App("#945246","Whirl","#945246"));
                appList1.add(new App("#8AD8FE","Dreampot","#8AD8FE"));
                appList1.add(new App("#FF5590","Candy Yum-Yum","#FF5590"));
                appList1.add(new App("#964546","Soar","#964546"));
                appList1.add(new App("#F15D73","Please me","#F15D73"));
                appList1.add(new App("#B483C2","4EVA","#B483C2"));
                appList1.add(new App("#E97AE5","Lavender Jade","#E97AE5"));
                appList1.add(new App("#846164","Lightly Charred","#846164"));
                appList1.add(new App("#ECC3C8","Lazy Lullaby","#ECC3C8"));
                appList1.add(new App("#876564","Deep Rooted","#876564"));
                appList1.add(new App("#F9F6F0","Frosting","#F9F6F0"));
                appList1.add(new App("#52413E","Inmy Fashion","#52413E"));

                appListColor = appList1;
                CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
                mList.setAdapter(adaptor2);

            }
        });

        button_Satin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList = findViewById(R.id.list2);
                appList1 = new ArrayList<>();
                appList1.add(new App("#E76E5B","Peachy New Year","#E76E5B"));
                appList1.add(new App("#CA6257","Good Health","#CA6257"));
                appList1.add(new App("#B15641","Mocha","#B15641"));
                appList1.add(new App("#8F4F47","Paramount","#8F4F47"));
                appList1.add(new App("#721E4B","Rebel","#721E4B"));
                appList1.add(new App("#A9736A","Spirit","#A9736A"));
                appList1.add(new App("#E48777","Sushi Kiss","#E48777"));
                appList1.add(new App("#DD6265","Twig","#DD6265"));
                appListColor = appList1;
                CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
                mList.setAdapter(adaptor2);

            }
        });
        button_Amplified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = findViewById(R.id.list2);
                appList1 = new ArrayList<>();
                appList1.add(new App("#C06B6F","Bric-o-la","#C06B6F"));
                appList1.add(new App("#993F3F","Dubonnet","#993F3F"));
                appList1.add(new App("#BC617D","Craving","#BC617D"));
                appList1.add(new App("#EC735E","Morange","#EC735E"));
                appList1.add(new App("#EA5836","Neon Orange","#EA5836"));
                appList1.add(new App("#D78080","Vegas Volt","#D78080"));
                appList1.add(new App("#A24C4F","Smoked Almond","#A24C4F"));
                appList1.add(new App("#D87A92","Chatter Box","#D87A92"));
                appList1.add(new App("#B8787A","Cosmo","#B8787A"));
                appList1.add(new App("#DC4F73","Impassioned","#DC4F73"));
                appList1.add(new App("#C9548A","Girl About Town","#C9548A"));
                appList1.add(new App("#E887C6","Saint Germain","#E887C6"));
                appList1.add(new App("#FF9755","Nifty Neon","#FF9755"));

                appListColor = appList1;
                CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
                mList.setAdapter(adaptor2);


            }
        });
        button_Lustre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = findViewById(R.id.list2);
                appList1 = new ArrayList<>();
                appList1.add(new App("#EE4C87","Pout Of Control","#EE4C87"));
                appList1.add(new App("#D75360","Pigment Of Your Image","#D75360"));
                appList1.add(new App("#9E2625","Glossed And Found","#9E2625"));
                appList1.add(new App("#CB726C","Thanks","#CB726C"));
                appList1.add(new App("#AD6358","Hug Me","#AD6358"));
                appList1.add(new App("#E17A75","Ellout","#E17A75"));
                appList1.add(new App("#C8232B","Pink Big","#C8232B"));
                appList1.add(new App("#A84539","Business Casual","#A84539"));
                appList1.add(new App("#A5384F","Beam There","#A5384F"));
                appList1.add(new App("#BD6772","Syrup","#BD6772"));
                appList1.add(new App("#902924","PDA","#902924"));
                appList1.add(new App("#702465","Good For My Ego","#702465"));
                appList1.add(new App("#B42225","Lady Bug","#B42225"));
                appList1.add(new App("#C02028","Cockney","#C02028"));
                appList1.add(new App("#D94E51","See Sheer","#D94E51"));
                appList1.add(new App("#BC3428","Local Celeb","#BC3428"));
                appList1.add(new App("#A74F43","Posh Pit","#A74F43"));
                appList1.add(new App("#EA2E22","Tnteaser","#EA2E22"));
                appList1.add(new App("#F05458","Flawless Is More","#F05458"));
                appList1.add(new App("#86241B","Spice It Up!","#86241B"));
                appList1.add(new App("#C72B2C","Flus Tered","#C72B2C"));

                appListColor = appList1;
                CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
                mList.setAdapter(adaptor2);

            }
        });
        button_Chemesheen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mList = findViewById(R.id.list2);
                appList1 = new ArrayList<>();
                appList1.add(new App("#E32929","Sweet Sakra","#E32929"));
                appList1.add(new App("#A51629","Brave Red","#A51629"));
                appList1.add(new App("#973335","Dare You","#973335"));
                appList1.add(new App("#A8676D","Creme In Your Coffee","#A8676D"));
                appList1.add(new App("#EB666D","Crosswires","#EB666D"));
                appList1.add(new App("#D37782","Fanfare","#D37782"));
                appList1.add(new App("#CF6067","Onhold","#CF6067"));
                appList1.add(new App("#C16F8C","Hot Gussid","#C16F8C"));
                appList1.add(new App("#D8A195","Japanese","#D8A195"));
                appList1.add(new App("#BA3A6D","Lickable","#BA3A6D"));
                appList1.add(new App("#DE9787","Shy Girl","#DE9787"));
                appList1.add(new App("#FF9782","Sweet&Sour","#FF9782"));
                appList1.add(new App("#F28571","Koi Coral","#F28571"));
                appList1.add(new App("#E9616F","Little Buddha","#E9616F"));
                appList1.add(new App("#E04171","Pickled Plum","#E04171"));
                appList1.add(new App("#F2668E","Star Magnolia","#F2668E"));
                appListColor = appList1;
                CustomAdaptor adaptor2 = new CustomAdaptor(Process.this,appListColor);
                mList.setAdapter(adaptor2);

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



        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        noDialog = new Dialog(this);
                ////////////////////////Save Image
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (str_iv.equals("null")){
                    Toast.makeText(Process.this,"\n" + "Please select a new image.",Toast.LENGTH_SHORT).show();
                }*/

                    BitmapDrawable drawable_save = (BitmapDrawable)iv.getDrawable();
                    Bitmap bitmap_save = drawable_save.getBitmap();
                    SaveImageGallery(bitmap_save);

            }
        });

        btnshare = findViewById(R.id.buttonshare);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Now share image function will be called
                // here we  will be passing the text to share
                // Getting drawable value from image
                BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                shareImageandText(bitmap);
            }
        });
        }

    public void Processlip(CharSequence hex_color) {
        final Python py = Python.getInstance();
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
                PyObject obj = pyo.callAttr("main",imageString,String.valueOf(hex_color));
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
    private void shareImageandText(Bitmap bitmap) {
        Uri uri = getmageToShare(bitmap);
        Intent intent = new Intent(Intent.ACTION_SEND);

        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        // adding text to share
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");

        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");

        // setting type to image
        intent.setType("image/png");

        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    // Retrieving the url to share
    private Uri getmageToShare(Bitmap bitmap) {
        File imagefolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            File file = new File(imagefolder, "shared_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, "com.anni.shareimage.fileprovider", file);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
     class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
        public String hex_color ="";
        private Context context;
        private List<App> apps;
        public Process process;


        public CustomAdaptor(Context context, List<App> apps) {
            this.context = context;
            this.apps = apps;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mName;
            Button Color;
            TextView textColor;
            public MyViewHolder(@NonNull final View itemView) {
                super(itemView);
                mName = itemView.findViewById(R.id.name);
                textColor = itemView.findViewById(R.id.textColor);
                Color = itemView.findViewById(R.id.btncolor);

            }
        }

        @NonNull
        @Override
        public CustomAdaptor.MyViewHolder
            onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.layout_list_item,parent,false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdaptor.MyViewHolder holder, int position) {

            App app = apps.get(position);
            holder.mName.setText(app.getName());
            holder.textColor.setText(app.getTextColor());
            holder.Color.setBackgroundColor(Color.parseColor(app.color));
            holder.Color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hex_color = app.getTextColor();
                    Processlip(hex_color);
                }
            });

        }
        @Override
        public int getItemCount() {
            return apps.size();
        }

    }
}
