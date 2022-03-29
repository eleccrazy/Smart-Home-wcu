package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MonitorData extends AppCompatActivity {

    private TextView temperature, humidity;

    private DatabaseReference myRefTemp, myRefHum;
    String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_data);

        temperature = findViewById(R.id.txttempval);
        humidity = findViewById(R.id.txthumval);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myRefTemp = FirebaseDatabase.getInstance().getReference().child("Monitor").child("Temp");
        myRefHum = FirebaseDatabase.getInstance().getReference().child("Monitor").child("Hum");

        myRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                temperature.setText(value + " \u2103");
                Log.d(TAG, "Value is: " + value);

                int temp = Integer.parseInt(value);
                if(temp <= 25)
                {
                    temperature.setTextColor(Color.parseColor("#00ff00"));
                }
                else if(temp > 25 && temp< 34){
                    temperature.setTextColor(Color.parseColor("#ffff00"));
                }
                else {
                    temperature.setTextColor(Color.parseColor("#ff0000"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                humidity.setText(value + " %");
                Log.d(TAG, "Value is: " + value);

                int hum;
                hum = Integer.parseInt(value);
                if(hum <= 25)
                {
                    humidity.setTextColor(Color.parseColor("#00ff00"));
                }
                else if(hum > 25 && hum< 34){
                    humidity.setTextColor(Color.parseColor("#ffff00"));
                }
                else {
                    humidity.setTextColor(Color.parseColor("#ff0000"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}