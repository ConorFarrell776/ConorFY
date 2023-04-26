package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Price extends AppCompatActivity {
    TextView wallsize,materials,labour,large,med1,med2,med3,small,
            largem,med1m,med2m,med3m,smallm,largel,med1l,med2l,med3l,smalll,total;
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
        total = findViewById(R.id.Total);
        pay = findViewById(R.id.Pay);
        Intent i = getIntent();
        String str = i.getStringExtra("title");
        String siz = i.getStringExtra("Size");
        String value = i.getStringExtra("value");
        wallsize.setText(str);
        int size = Integer.parseInt(value);
        int largev= size * 3;
        int medv = size * 2;
        int smallv = size;
        String lm=String.valueOf(largev);
        String mm=String.valueOf(medv);
        String sm=String.valueOf(smallv);
        if(siz.equals("Three")){
            largem.setText( lm);
            med1m.setText(mm);
            med2m.setText("0");
            med3m.setText("0");
            smallm.setText(sm);
            largel.setText("5");
            med1l.setText("5");
            med2l.setText("0");
            med3l.setText("0");
            smalll.setText("5");
            int mat = (largev + medv+ smallv) ;
            int three = 15;
            int totalv = mat + three;
            String totalp=String.valueOf(totalv);
            total.setText("Total is €" + totalp);

        }
        else if(siz.equals("Four")){
            largem.setText( lm);
            med1m.setText(mm);
            med2m.setText(mm);
            med3m.setText("0");
            smallm.setText(sm);
            largel.setText("5");
            med1l.setText("5");
            med2l.setText("5");
            med3l.setText("0");
            smalll.setText("5");
            int mat = (largev + medv + medv+ smallv);
            int four = 20;
            int totalv = mat + four;
            String totalp=String.valueOf(totalv);
            total.setText("Total is €" + totalp);

        }
        else if(siz.equals("Five")){
            largem.setText( lm);
            med1m.setText(mm);
            med2m.setText(mm);
            med3m.setText(mm);
            smallm.setText(sm);
            largel.setText("5");
            med1l.setText("5");
            med2l.setText("5");
            med3l.setText("5");
            smalll.setText("5");
            int mat = (largev + medv + medv+ medv+ smallv);
            int five = 25;
            int totalv = mat + five;
            String totalp=String.valueOf(totalv);
            total.setText("Total is €" + totalp);

        }

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Price.this, Pay.class));
            }
        });

    }
}