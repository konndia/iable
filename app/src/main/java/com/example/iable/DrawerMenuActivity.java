package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DrawerMenuActivity extends AppCompatActivity {

    ImageButton buttonBackToMain;
    public String name;
    TextView logout_txt, username;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);

        buttonBackToMain = findViewById(R.id.backToMain);
        logout_txt = findViewById(R.id.logout_txt);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://iable-72f9a-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                username = (TextView)findViewById(R.id.username);
                username.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(DrawerMenuActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}