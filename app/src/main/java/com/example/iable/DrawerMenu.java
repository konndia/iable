package com.example.iable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DrawerMenu extends AppCompatActivity {

    ImageButton buttonBackToMain;
    TextView logout_txt;
    FirebaseAuth auth;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);

        buttonBackToMain = findViewById(R.id.backToMain);
        logout_txt = findViewById(R.id.logout_txt);
        auth = FirebaseAuth.getInstance();

        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(DrawerMenu.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawerMenu.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}