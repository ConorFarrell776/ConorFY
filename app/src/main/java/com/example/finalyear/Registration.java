package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText email, password,eir;
    Button register;
    FirebaseAuth mAuth;
    TextView login;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        register  = findViewById(R.id.register);
        login = findViewById(R.id.text_login);
        eir = findViewById(R.id.register_eircode);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });

    }
    private void Register()
    {
        String emails = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String eirc = eir.getText().toString().trim();
        if(emails.isEmpty())
        {
            email.setError("Email can not be empty");
        }
        if(pass.isEmpty())
        {
            password.setError("Password can not be empty");
        }
        if(eirc.isEmpty())
        {
            eir.setError("Eircode can not be empty");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(emails, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        User newUser = new User(emails, uid,"false",eirc);
                        usersRef.child(uid).setValue(newUser);
                        Toast.makeText(Registration.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registration.this, MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Registration.this, "Registration Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}