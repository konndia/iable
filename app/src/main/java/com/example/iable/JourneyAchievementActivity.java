package com.example.iable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class JourneyAchievementActivity extends AppCompatActivity {

    ImageButton backToAch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_achievement);

        backToAch = findViewById(R.id.backToAch);
        backToAch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JourneyAchievementActivity.this, AchievementsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}