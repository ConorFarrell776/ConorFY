package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Price extends AppCompatActivity {
    TextView wallsize,materials,labour,large,med1,med2,med3,small,
            largem,med1m,med2m,med3m,smallm,largel,med1l,med2l,med3l,smalll;
    Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        wallsize = (TextView) findViewById(R.id.WallSize);
        materials = findViewById(R.id.Materials);
        labour = findViewById(R.id.Labour);
        large = findViewById(R.id.Large);
        largem = findViewById(R.id.LRGM);
        largel = findViewById(R.id.LRGL);
        med1 = findViewById(R.id.Med1);
        med2 = findViewById(R.id.Med2);
        med3 = findViewById(R.id.Med3);
        med1m = findViewById(R.id.Med1M);
        med2m = findViewById(R.id.Med2M);
        med3m = findViewById(R.id.Med3M);
        med1l = findViewById(R.id.Med1L);
        med2l = findViewById(R.id.Med2L);
        med3l = findViewById(R.id.Med3L);
        small = findViewById(R.id.Small);
        smallm = findViewById(R.id.SmallM);
        smalll = findViewById(R.id.SmallL);
        pay = findViewById(R.id.Pay);
        largem.setText("20");
        med1m.setText("15");
        med2m.setText("15");
        med3m.setText("15");
        smallm.setText("10");
        largel.setText("5");
        med1l.setText("5");
        med2l.setText("5");
        med3l.setText("5");
        smalll.setText("5");
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Price.this, Pay.class));
            }
        });

    }
}