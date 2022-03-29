package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView control, monitor, secret_lock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        control = findViewById(R.id.cardcontrol);
        monitor = findViewById(R.id.cardmonitor);
        secret_lock = findViewById(R.id.cardsecretlock);

        control.setOnClickListener(this);
        monitor.setOnClickListener(this);
        secret_lock.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i;
        switch (view.getId())
        {
            case R.id.cardcontrol:
                i = new Intent(this, Control.class);
                startActivity(i);
                break;
            case R.id.cardmonitor:
                i = new Intent(this, MonitorData.class);
                startActivity(i);
                break;
            case R.id.cardsecretlock:
                i = new Intent(this, SecretLock.class);
                startActivity(i);
                break;
        }

    }
}