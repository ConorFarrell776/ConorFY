package com.example.finalyear;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Price extends AppCompatActivity {
    TextView wallsize,materials,labour,large,med1,med2,med3,small,
            largem,med1m,med2m,med3m,smallm,largel,med1l,med2l,med3l,smalll,total;
    Button pay,home;
    String publicKey= "pk_test_51N163kKFo6L84JioH2YHzTJXlIlslqr2JDVaPia7dzyZoYgzva7pWQHaL4uaJPS4lVNRwQdZLwajCJcgB4Kj6zEw00vo1zw6ps";
    String secretKey="sk_test_51N163kKFo6L84JioRIGu17Im0AJwwRSCAYEADHOFkawIgSl014CSyZYZ6e5KGIJZhOBe8xRb7zjfoqBJexLPs6p900PgiCKLgF";
    String customerID;
    String EphericalKey;
    String totalp;
    String ClientSecret;
    PaymentSheet paymentSheet;
    FirebaseUser mCurrentUser;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
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
        home = findViewById(R.id.Home);
        TotalValue();
        PaymentConfiguration.init(this, publicKey);
        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            onPaymentResult(paymentSheetResult);
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Price.this, MainActivity.class));

            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFlow();
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            customerID=object.getString("id");
                            getEphericalKey(customerID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header = new HashMap<>();
                header.put("Authorization","Bearer " + secretKey);
                return header;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Price.this);
        requestQueue.add(stringRequest);



    }


    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
            if (paymentSheetResult instanceof PaymentSheetResult.Completed){
                Toast.makeText(this,"Payment success", Toast.LENGTH_SHORT).show();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                Intent i = getIntent();
                String order = i.getStringExtra("orderID");
                Intent d = getIntent();
                String ord = d.getStringExtra("ord");
                mDatabase.child("users").child(mCurrentUser.getUid()).child("details").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                            Details details = detailsSnapshot.getValue(Details.class);


                            if (details.getOrderID().equals(order) || details.getOrderID().equals(ord)) {
                                details.setStatus("Complete");
                                detailsSnapshot.getRef().child("status").setValue("Complete");



                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


            }
        }

        private void getEphericalKey(String customerID) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                EphericalKey=object.getString("id");
                                getClientSecret(customerID,EphericalKey);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Authorization","Bearer " + secretKey);
                    header.put("Stripe-Version","2022-11-15" );
                    return header;
                }

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String > params = new HashMap<>();
                    params.put("customer",customerID);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Price.this);
            requestQueue.add(stringRequest);
        }

        private void getClientSecret(String customerID, String ephericalKey) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                ClientSecret=object.getString("client_secret");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("Authorization","Bearer " + secretKey);
                    return header;
                }

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    String zero = "00";
                    Intent i = getIntent();
                    String order = i.getStringExtra("orderID");
                    Intent d = getIntent();
                    String ord = d.getStringExtra("ord");
                    final int[] yes = {0};
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                    final CountDownLatch latch = new CountDownLatch(1);
                    mDatabase.child("users").child(mCurrentUser.getUid()).child("details").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                                Details details = detailsSnapshot.getValue(Details.class);


                                if (details.getOrderID().equals(order)|| details.getOrderID().equals(ord)) {
                                    String str = details.getPrice();

                                    yes[0] = Integer.parseInt(str);


                                }
                            }
                            latch.countDown();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                    try {
                        latch.await(); // wait for the latch to count down
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int okay = yes[0];
                    Map<String, String > params = new HashMap<>();
                    params.put("customer",customerID);
                    params.put("amount", okay +zero);
                    params.put("currency","eur");
                    params.put("automatic_payment_methods[enabled]","true");
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Price.this);
            requestQueue.add(stringRequest);
        }

        private void PaymentFlow() {
            paymentSheet.presentWithPaymentIntent(ClientSecret,new PaymentSheet.Configuration("Abc",new PaymentSheet.CustomerConfiguration(
                    customerID,EphericalKey
            )));

        }

        private void TotalValue(){
            Intent i = getIntent();
            String order = i.getStringExtra("orderID");
            Intent yes = getIntent();
            String ord = yes.getStringExtra("ord");
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            mDatabase.child("users").child(mCurrentUser.getUid()).child("details").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                        Details details = detailsSnapshot.getValue(Details.class);

                        if (details.getOrderID().equals(order) || details.getOrderID().equals(ord) ) {
                            String str = details.getName();
                            String value = details.getWidth();
                            String siz = details.getAmount();
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
                                int totalv =0;
                                int mat = (largev + medv+ smallv) ;
                                int three = 15;
                                totalv = mat + three;
                                totalp=String.valueOf(totalv);
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
                                int totalv =0;
                                totalv = mat + four;
                                totalp=String.valueOf(totalv);
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
                                int totalv =0;
                                totalv = mat + five;
                                String totalp=String.valueOf(totalv);
                                total.setText("Total is €" + totalp);
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

