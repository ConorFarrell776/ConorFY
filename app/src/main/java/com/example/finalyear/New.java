package com.example.finalyear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

import java.util.HashMap;
import java.util.Map;

public class New extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String text;
    EditText  name, width;
    Button add;
    DatabaseReference mDatabase;
    FirebaseUser mCurrentUser;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new);
        name = findViewById(R.id.name);
        width = findViewById(R.id.width);
        add = findViewById(R.id.add);
        Spinner frameAmount = findViewById(R.id.amount);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frameAmount.setAdapter(adapter);
        frameAmount.setOnItemSelectedListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();



        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String amount = frameAmount.getSelectedItem().toString();
                insertPhotoWall(amount);
                String Title=name.getText().toString();
                Intent intent = new Intent(getApplicationContext(), WallSize.class);
                intent.putExtra("message_key", Title);
                String value=width.getText().toString();
                intent.putExtra("value", value);
                String size = frameAmount.getSelectedItem().toString();
                intent.putExtra("Size",size);
                startActivity(intent);
            }

        });

    }

    private void insertPhotoWall(String amount) {
        String sname = name.getText().toString();
        String swidth = width.getText().toString();
        String detailsKey = mDatabase.child("users").child(mCurrentUser.getUid()).child("details").push().getKey();
        Details newDetails = new Details(sname, amount, swidth, "Incomplete");
        Map<String, Object> detailsValues = newDetails.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + mCurrentUser.getUid() + "/details/" + detailsKey, detailsValues);
        mDatabase.updateChildren(childUpdates);



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
