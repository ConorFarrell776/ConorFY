package com.example.finalyear;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {
    FirebaseAuth mAuth;;
    Button logout,design;
    ListView listView;
    List<Details> detailsList;
    FirebaseUser mCurrentUser;
    DatabaseReference mDatabase;
    Details details = new Details();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        logout = findViewById(R.id.btnlogout);
        design = findViewById(R.id.btndesign);
        listView = findViewById(R.id.list);
        detailsList = new ArrayList<>();


        mDatabase.child("users").child(mCurrentUser.getUid()).child("details")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        detailsList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Details details = snapshot.getValue(Details.class);
                            detailsList.add(details);
                        }
                        DetailsAdapter adapter = new DetailsAdapter(MainActivity.this, detailsList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, New.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            } });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Details clickedDetails = detailsList.get(position);
                Intent displayIntent = new Intent(MainActivity.this, Display.class);
                displayIntent.putExtra("details", (Serializable) clickedDetails);
                startActivity(displayIntent);


            }
        });

    }



    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        { startActivity(new Intent(MainActivity.this, Login.class));
        } }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, Login.class));
    }



}