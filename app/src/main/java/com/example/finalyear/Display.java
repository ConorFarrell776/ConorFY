package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class Display extends AppCompatActivity implements Serializable {
    TextView wallsize,w,h,large,med1,med2,med3,small,
            largeh,med1h,med2h,med3h,smallh,largew,med1w,med2w,med3w,smallw,status,price;
    Button pri,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display);
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
        home = findViewById(R.id.Home);
        status = findViewById(R.id.Status);
        price = findViewById(R.id.Tprice);
        wallsize = (TextView) findViewById(R.id.WallSize);
        Intent intent = getIntent();
        Details clickedDetails = (Details) intent.getSerializableExtra("details");
        wallsize.setText(clickedDetails.getName());
        String size = clickedDetails.getAmount();
        String ord =clickedDetails.getOrderID();
        String yes = clickedDetails.getStatus();
        status.setText("Current Status: " + clickedDetails.getStatus());
        if(yes.equals("Incomplete")){
            pri.setVisibility(View.VISIBLE);
        }
        int length = Integer.parseInt(clickedDetails.getWidth());
        int lrgW,med1W,med2W,med3W,smallW,lrgH,med1H,med2H,med3H,smallH;
        String lw,m1w,m2w,m3w,sw,lh,m1h,m2h,m3h,sh;
        int largev= length * 3;
        int medv = length * 2;
        int smallv = length;
        int totalv;
        String totalp;
        if(size.equals("Three")){
            lrgW = length* 45;
            med1W = length * 35;
            smallW = length * 20;
            lrgH = length* 45 +45;
            med1H = length * 35 + 30;
            smallH = length * 20 +15;
            lw=String.valueOf(lrgW);
            m1w=String.valueOf(med1W);
            sw=String.valueOf(smallW);
            lh=String.valueOf(lrgH);
            m1h=String.valueOf(med1H);
            sh=String.valueOf(smallH);
            largew.setText(lw);
            med1w.setText(m1w);
            med2w.setText("0");
            med3w.setText("0");
            smallw.setText(sw);
            largeh.setText(lh);
            med1h.setText(m1h);
            med2h.setText("0");
            med3h.setText("0");
            smallh.setText(sh);
            int mat = (largev + medv+ smallv) ;
            int three = 15;
            totalv = mat + three;
            totalp=String.valueOf(totalv);
            price.setText("Total is €" + totalp);
        }
        else if(size.equals("Four")){
            lrgW = length* 40;
            med1W = length * 25;
            med2W = length * 25;
            smallW = length * 10;
            lrgH = length* 40 +45;
            med1H = length * 25 + 30;
            med2H = length * 25 + 30;
            smallH = length * 10 +15;
            lw=String.valueOf(lrgW);
            m1w=String.valueOf(med1W);
            m2w=String.valueOf(med2W);
            sw=String.valueOf(smallW);
            lh=String.valueOf(lrgH);
            m1h=String.valueOf(med1H);
            m2h=String.valueOf(med2H);
            sh=String.valueOf(smallH);
            largew.setText(lw);
            med1w.setText(m1w);
            med2w.setText(m2w);
            med3w.setText("0");
            smallw.setText(sw);
            largeh.setText(lh);
            med1h.setText(m1h);
            med2h.setText(m2h);
            med3h.setText("0");
            smallh.setText(sh);
            int mat = (largev + medv + medv+ smallv);
            int four = 20;
            totalv = mat + four;
            totalp=String.valueOf(totalv);
            price.setText("Total is €" + totalp);
        }
        else if(size.equals("Five")){
            lrgW = length* 30;
            med1W = length * 20;
            med2W = length * 20;
            med3W = length * 20;
            smallW = length * 10;
            lrgH = length* 30 +45;
            med1H = length * 20 + 30;
            med2H = length * 20 + 30;
            med3H = length * 20 +30;
            smallH = length * 10 +15;
            lw=String.valueOf(lrgW);
            m1w=String.valueOf(med1W);
            m2w=String.valueOf(med2W);
            m3w=String.valueOf(med3W);
            sw=String.valueOf(smallW);
            lh=String.valueOf(lrgH);
            m1h=String.valueOf(med1H);
            m2h=String.valueOf(med2H);
            m3h=String.valueOf(med3H);
            sh=String.valueOf(smallH);
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
            int mat = (largev + medv + medv+ medv+ smallv);
            int five = 25;
            totalv = mat + five;
            totalp=String.valueOf(totalv);
            price.setText("Total is €" + totalp);

        }
        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Price.class);
                intent.putExtra("ord", ord);
                startActivity(intent);            }        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display.this, MainActivity.class));
            }        });
    }
}