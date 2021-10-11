package com.example.convertrgbtogray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

import java.util.ArrayList;
import java.util.List;

public class Camera_Realtime extends AppCompatActivity {
    private String TAG = CameraActivity.class.getSimpleName();
    CameraView cv;
    Button button_Matte,button_Satin,button_Amplified,button_Lustre,button_Chemesheen;
    List<App> appList1,appListColor;
    RecyclerView mList1,mList;
    ImageButton ibToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_realtime);
        cv = findViewById(R.id.camera_view_Realtime);
        ibToggle = findViewById(R.id.toggleCamera_Realtime);
        ibToggle.setOnClickListener(this::onClick);
        cv.setLifecycleOwner(this);
        cv.addCameraListener(cl);

        RelativeLayout Re_camera_realtime = findViewById(R.id.Reletive_Camera_Realtime);
        View v = LayoutInflater.from(Re_camera_realtime.getContext())
                .inflate(R.layout.activity_color_recycleview, Re_camera_realtime, false);
        Re_camera_realtime.addView(v);
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

        LinearLayoutManager manager2 = new LinearLayoutManager(Camera_Realtime.this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList.setLayoutManager(manager2);

        Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
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
                Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
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
                Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
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
                Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
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
                Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
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
                Camera_Realtime.CustomAdaptor1 adaptor2 = new Camera_Realtime.CustomAdaptor1(Camera_Realtime.this,appListColor);
                mList.setAdapter(adaptor2);

            }
        });
    }

    private CameraListener cl = new CameraListener() {
        @Override
        public void onPictureTaken(@NonNull PictureResult result) {
            super.onPictureTaken(result);

            Process.pictureResult = result;
            startActivity(new Intent(Camera_Realtime.this, Process.class));
        }

        @Override
        public void onCameraError(@NonNull CameraException exception) {
            super.onCameraError(exception);
            Toast.makeText(Camera_Realtime.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCameraError: ", exception);
        }
    };
    public void onClick(View v) {
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
    class CustomAdaptor1 extends RecyclerView.Adapter<CustomAdaptor1.MyViewHolder> {
        public String hex_color ="";
        private Context context;
        private List<App> apps;


        public CustomAdaptor1(Context context, List<App> apps) {
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
        public CustomAdaptor1.MyViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.layout_list_item,parent,false);
            return new Camera_Realtime.CustomAdaptor1.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Camera_Realtime.CustomAdaptor1.MyViewHolder holder, int position) {

            App app = apps.get(position);
            holder.mName.setText(app.getName());
            holder.textColor.setText(app.getTextColor());
            holder.Color.setBackgroundColor(Color.parseColor(app.color));
            holder.Color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hex_color = app.getTextColor();
                    ///Processlip(text123);
                }
            });

        }
        @Override
        public int getItemCount() {
            return apps.size();
        }

    }

}