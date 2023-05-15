package com.example.iable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button buttonRegistration;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegistration = findViewById(R.id.button_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Users");

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bottom_menu_view.class);
                startActivity(intent);
//                showMainWindow();
            }
        });
    }
}