package com.example.myapplication;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation top_anim,bottom_anim;
    ImageView imageView;
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
//        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageView = findViewById(R.id.logo);
        textView = findViewById(R.id.logo_name);



        imageView.setAnimation(top_anim);
        textView.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,Login_Activity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);
    }
}