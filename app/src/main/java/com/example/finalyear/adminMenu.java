package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class adminMenu extends AppCompatActivity {
    EditText id,email;
    Button srch,hme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_menu);
        email = findViewById(R.id.Email);
        id = findViewById(R.id.Name);
        srch = findViewById(R.id.Search);
        hme = findViewById(R.id.Home);

        srch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String order = id.getText().toString().trim();
                String emails = email.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), AdminDisplay.class);
                intent.putExtra("orderID",order );
                intent.putExtra("Email",emails);
                startActivity(intent);
            }

        });

        hme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminMenu.this, MainActivity.class));

            }

        });
    }

}