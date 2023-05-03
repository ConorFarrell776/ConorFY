package com.example.finalyear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDisplay extends AppCompatActivity {

    TextView wallsize,w,h,large,med1,med2,med3,small,
            largeh,med1h,med2h,med3h,smallh,largew,med1w,med2w,med3w,smallw,del,price,stat;
    Button adm,hme,sen,dele,nsen;
    DatabaseReference mDatabase;
    FirebaseUser mCurrentUser;
    FirebaseDatabase database;
    DatabaseReference usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_display);
        w = findViewById(R.id.W);
        h = findViewById(R.id.H);
        large = findViewById(R.id.Large);
        largeh = findViewById(R.id.LRGH);
        largew = findViewById(R.id.LRGW);
        sen = findViewById(R.id.Sent);
        dele = findViewById(R.id.Delivered);
        nsen = findViewById(R.id.NotSent);
        med1 = findViewById(R.id.Med1);
        med2 = findViewById(R.id.Med2);
        med3 = findViewById(R.id.Med3);
        price = findViewById(R.id.Tprice);
        stat = findViewById(R.id.Status);
        med1h = findViewById(R.id.Med1H);
        med2h = findViewById(R.id.Med2H);
        med3h = findViewById(R.id.Med3H);
        med1w = findViewById(R.id.Med1W);
        med2w = findViewById(R.id.Med2W);
        med3w = findViewById(R.id.Med3W);
        small = findViewById(R.id.Small);
        smallh = findViewById(R.id.SmallH);
        smallw = findViewById(R.id.SmallW);
        adm = findViewById(R.id.adminMenu);
        hme = findViewById(R.id.Home);
        del = findViewById(R.id.Delivery);
        wallsize = (TextView) findViewById(R.id.WallSize);
        Intent i = getIntent();
        String orderId = i.getStringExtra("orderID");
        String email = i.getStringExtra("Email");
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user.getEmail().equals(email)) {
                        String userId = user.getId();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase.child("users").child(userId).child("details").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                                    Details details = detailsSnapshot.getValue(Details.class);
                                    if (details.getOrderID() != null && details.getOrderID().equals(orderId)) {
                                        String str = details.getName();
                                        String value = details.getWidth();
                                        String siz = details.getAmount();
                                        String hj = details.getDelivery();
                                        String ds = details.getStatus();
                                        String jd = details.getPrice();
                                        wallsize.setText(str);
                                        price.setText("Total is €" + jd);
                                        stat.setText("Status of order: "+ds);
                                        del.setText("Delivery Status:"+ hj);
                                        int length = Integer.parseInt(value);
                                        int lrgW,med1W,med2W,med3W,smallW,lrgH,med1H,med2H,med3H,smallH;
                                        String lw,m1w,m2w,m3w,sw,lh,m1h,m2h,m3h,sh;
                                        sen.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                detailsSnapshot.getRef().child("delivery").setValue("Sent");
                                            }        });
                                        dele.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                detailsSnapshot.getRef().child("delivery").setValue("Delivered to customer");

                                            }        });
                                        nsen.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                detailsSnapshot.getRef().child("delivery").setValue("Not sent yet");

                                            }        });


                                        if(siz.equals("Three")){
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
                                        }
                                        else if(siz.equals("Four")){
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
                                        }
                                        else if(siz.equals("Five")){
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

                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });








        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDisplay.this, adminMenu.class));

            }        });

        hme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDisplay.this, MainActivity.class));

            }        });

    }
}
