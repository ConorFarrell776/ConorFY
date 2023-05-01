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

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class New extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String text;
    EditText  name, width;
    Button add;
    DatabaseReference mDatabase;
    FirebaseUser mCurrentUser;
    String randomString;





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
                Intent intent = new Intent(getApplicationContext(), WallSize.class);
                intent.putExtra("orderID", randomString);
                startActivity(intent);
            }

        });

    }

    private void insertPhotoWall(String amount) {
        String sname = name.getText().toString();
        String swidth = width.getText().toString();
        String detailsKey = mDatabase.child("users").child(mCurrentUser.getUid()).child("details").push().getKey();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        int size = Integer.parseInt(swidth);
        int largev= size * 3;
        int medv = size * 2;
        int smallv = size;
        if(amount.equals("Three")){
            int mat = (largev + medv+ smallv) ;
            int three = 15;
            int price = mat + three;
            String yes =String.valueOf(price);
            randomString = sb.toString();
            Details newDetails = new Details(amount, sname, swidth, "Incomplete",randomString,yes);
            Map<String, Object> detailsValues = newDetails.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/users/" + mCurrentUser.getUid() + "/details/" + detailsKey, detailsValues);
            mDatabase.updateChildren(childUpdates);


        }
        else if(amount.equals("Four")){
            int mat = (largev + medv + medv+ smallv);
            int four = 20;
            int price = mat + four;
            String yes =String.valueOf(price);
            randomString = sb.toString();
            Details newDetails = new Details(amount, sname, swidth, "Incomplete",randomString,yes);
            Map<String, Object> detailsValues = newDetails.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/users/" + mCurrentUser.getUid() + "/details/" + detailsKey, detailsValues);
            mDatabase.updateChildren(childUpdates);


        }
        else if(amount.equals("Five")){
            int mat = (largev + medv + medv+ medv+ smallv);
            int five = 25;
            int price = mat + five;
            String yes =String.valueOf(price);
            randomString = sb.toString();
            Details newDetails = new Details(amount, sname, swidth, "Incomplete",randomString,yes);
            Map<String, Object> detailsValues = newDetails.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/users/" + mCurrentUser.getUid() + "/details/" + detailsKey, detailsValues);
            mDatabase.updateChildren(childUpdates);

        }





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
