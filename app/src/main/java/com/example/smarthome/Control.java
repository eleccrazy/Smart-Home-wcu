package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Control extends AppCompatActivity {

    private SwitchCompat lightOne, lightTwo, tv, ac;
    DatabaseReference refLightOne, refLightTwo, refAC, refTV;
    private String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lightOne =  findViewById(R.id.lightOne);
        lightTwo =  findViewById(R.id.lightTwo);
        ac =  findViewById(R.id.lightThree);
        tv =  findViewById(R.id.lightFour);


        refLightOne = FirebaseDatabase.getInstance().getReference().child("Control").child("Light One");
        refLightTwo = FirebaseDatabase.getInstance().getReference().child("Control").child("Light Two");
        refAC = FirebaseDatabase.getInstance().getReference().child("Control").child("AC");
        refTV = FirebaseDatabase.getInstance().getReference().child("Control").child("TV");

        lightOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightOne.isChecked()){
                    refLightOne.setValue("L1_ON");
                }
                else
                {
                    refLightOne.setValue("L1_OFF");
                }
            }
        });
        lightTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightTwo.isChecked()){

                    refLightTwo.setValue("L2_ON");
                }
                else
                {
                    refLightTwo.setValue("L2_OFF");
                }
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ac.isChecked()){

                    refAC.setValue("AC_ON");
                }
                else
                {
                    refAC.setValue("AC_OFF");
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.isChecked()){
                    refTV.setValue("TV_ON");
                }
                else
                {
                    refTV.setValue("TV_OFF");
                }
            }
        });

        statCheckLightOne();
        statCheckLightTwo();
        statCheckAC();
        statCheckTV();
    }

    public void statCheckLightOne(){
        refLightOne.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stat_light_one = snapshot.getValue(String.class);
                if(stat_light_one.equals("L1_ON"))
                    lightOne.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void statCheckLightTwo(){
        refLightTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stat_light_two = snapshot.getValue(String.class);
                if(stat_light_two.equals("L2_ON"))
                    lightTwo.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    public void statCheckAC(){
        refAC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stat_ac = snapshot.getValue(String.class);
                if(stat_ac.equals("AC_ON"))
                    ac.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    public void statCheckTV(){
        refTV.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stat_tv = snapshot.getValue(String.class);
                if(stat_tv.equals("TV_ON"))
                    tv.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}

