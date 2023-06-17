package com.example.iable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WaterActivity extends AppCompatActivity {

    ImageButton backToAch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        backToAch = findViewById(R.id.backToAch);
        backToAch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaterActivity.this, AchievementsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}