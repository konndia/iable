package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {

    TextView user_name, user_email, user_height, user_weight, user_age;
    String name, email, height, weight, age;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        db = FirebaseDatabase.getInstance("https://iable-72f9a-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                user_name = (TextView)findViewById(R.id.user_name);
                user_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = snapshot.child("email").getValue(String.class);
                user_email = (TextView)findViewById(R.id.user_email);
                user_email.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                height = snapshot.child("height").getValue(String.class);
                user_height = (TextView)findViewById(R.id.user_height);
                user_height.setText(height);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                weight = snapshot.child("weight").getValue(String.class);
                user_weight = (TextView)findViewById(R.id.user_weight);
                user_weight.setText(weight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                age = snapshot.child("age").getValue(String.class);
                user_age = (TextView)findViewById(R.id.user_age);
                user_age.setText(age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ImageButton drawer_menu = findViewById(R.id.drawer_menu);
        drawer_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, DrawerMenuActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_user);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_achievements:
                    startActivity(new Intent(getApplicationContext(), AchievementsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_add:
                    startActivity(new Intent(getApplicationContext(), AddActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_user:
                    return true;
                case R.id.bottom_notifications:
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}