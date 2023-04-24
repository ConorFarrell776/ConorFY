package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class New extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String text;
    EditText  name, width;
    Button add;
    FirebaseDatabase rootNode;
    DatabaseReference photowallUserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        name = findViewById(R.id.name);
        width = findViewById(R.id.width);
        add = findViewById(R.id.add);
        Spinner frameAmount = findViewById(R.id.amount);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frameAmount.setAdapter(adapter);
        frameAmount.setOnItemSelectedListener(this);
        photowallUserDB = FirebaseDatabase.getInstance().getReference().child("PhotoWall");


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insertPhotoWall();
                String Title=name.getText().toString();
                Intent intent = new Intent(getApplicationContext(), WallSize.class);
                intent.putExtra("message_key", Title);
                String value=width.getText().toString();
                Intent i = new Intent(getApplicationContext(), WallSize.class);
                intent.putExtra("value", value);
                startActivity(intent);
                //startActivity(new Intent(New.this , com.example.conorfarrell_finalyear.WallSize.class));
            }

        });

    }

    private void insertPhotoWall() {
        String sname = name.getText().toString();
        String swidth = width.getText().toString();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String useruid=user.getUid();
        Details details = new Details(text,sname, swidth, useruid);

        photowallUserDB.push().setValue(details);

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
