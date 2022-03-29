package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class Introductory extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView img;
    TextView txtBasic, txtByWhom;
    private static int SPLASH_SCREEN = 5000;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img = findViewById(R.id.imglogo);
        txtBasic = findViewById(R.id.txtbasic);
        txtByWhom = findViewById(R.id.txtbywhom);
        mainLayout = findViewById(R.id.mainlay);

        img.setAnimation(topAnim);
        txtBasic.setAnimation(bottomAnim);

        img.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        txtBasic.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        mainLayout.animate().translationX(-600).setDuration(500).setStartDelay(4500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Introductory.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}