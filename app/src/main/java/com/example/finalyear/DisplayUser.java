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

public class DisplayUser extends AppCompatActivity {

    TextView email,eircode,adminstat;
    Button amen;
    FirebaseDatabase database;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display_user);
        email = findViewById(R.id.UserEmail);
        eircode = findViewById(R.id.UserEircode);
        adminstat = findViewById(R.id.UserAdmin);
        amen = findViewById(R.id.admM);
        Intent i = getIntent();
        String emailt = i.getStringExtra("Email");
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user.getEmail().equals(emailt)) {
                        String eir = user.getEircode();
                        String admin = user.getAdmin();
                        email.setText("User Email: " + emailt);
                        eircode.setText("User Eircode: " + eir);
                        adminstat.setText("User Admin Status: " + admin);


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        amen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayUser.this, adminMenu.class));

            }        });

    }
}