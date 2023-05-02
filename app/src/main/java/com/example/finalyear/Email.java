package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Email extends AppCompatActivity {
    EditText add;
    Button email,menu,home;
    TextView det;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_email);
        det = findViewById(R.id.EmailDetails);
        add = findViewById(R.id.Address);

        email = findViewById(R.id.Email);
        menu = findViewById(R.id.AdminMen);
        home = findViewById(R.id.Home);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!add.getText().toString().isEmpty() ) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{add.getText().toString()});
                    intent.setType("message/rfc822");
                    if(intent.resolveActivity(getPackageManager()) !=null){
                        startActivity(intent);
                    }

                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Email.this, adminMenu.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Email.this, MainActivity.class));
            }
        });
    }
}