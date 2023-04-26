package com.example.finalyear;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    FirebaseAuth mAuth;;
    Button logout,design;
    ListView listView;
    ArrayList<Details> arraylist = new ArrayList<>();
    ArrayAdapter<Details> arrayAdapter;
    FirebaseDatabase rootNode;
    DatabaseReference photowallUserDB;
    Details details = new Details();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnlogout);
        design = findViewById(R.id.btndesign);
        photowallUserDB = FirebaseDatabase.getInstance().getReference().child("PhotoWall");
        listView = findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arraylist);
        listView.setAdapter(arrayAdapter);
        photowallUserDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                details = snapshot.getValue(Details.class);
                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(details.getId().equals(currentuser)){
                    arraylist.add(details);
                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                Details details = arrayAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, Display.class);
                intent.putExtra("name",details.getName());
                intent.putExtra("amount",details.getAmount());
                intent.putExtra("width",details.getWidth());
                startActivity(intent);

               // startActivity(new Intent(MainActivity.this, Display.class));


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