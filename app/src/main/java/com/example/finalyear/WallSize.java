package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WallSize extends AppCompatActivity {
    TextView wallsize,w,h,large,med1,med2,med3,small,
            largeh,med1h,med2h,med3h,smallh,largew,med1w,med2w,med3w,smallw;
    Button pri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_size);
        w = findViewById(R.id.W);
        h = findViewById(R.id.H);
        large = findViewById(R.id.Large);
        largeh = findViewById(R.id.LRGH);
        largew = findViewById(R.id.LRGW);
        med1 = findViewById(R.id.Med1);
        med2 = findViewById(R.id.Med2);
        med3 = findViewById(R.id.Med3);
        med1h = findViewById(R.id.Med1H);
        med2h = findViewById(R.id.Med2H);
        med3h = findViewById(R.id.Med3H);
        med1w = findViewById(R.id.Med1W);
        med2w = findViewById(R.id.Med2W);
        med3w = findViewById(R.id.Med3W);
        small = findViewById(R.id.Small);
        smallh = findViewById(R.id.SmallH);
        smallw = findViewById(R.id.SmallW);
        pri = findViewById(R.id.price);
        wallsize = (TextView) findViewById(R.id.WallSize);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        Intent i = getIntent();
        String value = intent.getStringExtra("value");
        wallsize.setText(str);
        int length = Integer.parseInt(value);
        int lrgW = length* 30;
        int med1W = length * 20;
        int med2W = length * 20;
        int med3W = length * 20;
        int smallW = length * 10;
        int lrgH = length* 30 +45;
        int med1H = length * 20 + 30;
        int med2H = length * 20 + 30;
        int med3H = length * 20 +30;
        int smallH = length * 10 +15;
        String lw=String.valueOf(lrgW);
        String m1w=String.valueOf(med1W);
        String m2w=String.valueOf(med2W);
        String m3w=String.valueOf(med3W);
        String sw=String.valueOf(smallW);
        String lh=String.valueOf(lrgH);
        String m1h=String.valueOf(med1H);
        String m2h=String.valueOf(med2H);
        String m3h=String.valueOf(med3H);
        String sh=String.valueOf(smallH);
        largew.setText(lw);
        med1w.setText(m1w);
        med2w.setText(m2w);
        med3w.setText(m3w);
        smallw.setText(sw);
        largeh.setText(lh);
        med1h.setText(m1h);
        med2h.setText(m2h);
        med3h.setText(m3h);
        smallh.setText(sh);
        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WallSize.this, Price.class));
            }        });

    }
}