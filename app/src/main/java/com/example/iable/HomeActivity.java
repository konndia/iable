package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.iable.Models.User;
import com.example.iable.PedometerActivity;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.Utilities;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    public TextView greeting;
    public TextView greetingPhrases;
    public TextView waterTextView;
    public String name;
    public String dayTime;
    int steps;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout steps_counter;
    CheckBox water1, water2, water3, water4, water5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // вода
        waterTextView = findViewById(R.id.waterTextView);
        water1 = findViewById(R.id.water1);
        water2 = findViewById(R.id.water2);
        water3 = findViewById(R.id.water3);
        water4 = findViewById(R.id.water4);
        water5 = findViewById(R.id.water5);

        water1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    waterTextView.setText("400 ml");
                else
                    waterTextView.setText("0 ml");
            }
        });

        water2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    waterTextView.setText("800 ml");
                else
                    waterTextView.setText("400 ml");
            }
        });

        water3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    waterTextView.setText("1200 ml");
                else
                    waterTextView.setText("800 ml");
            }
        });

        water4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    waterTextView.setText("1600 ml");
                else
                    waterTextView.setText("1200 ml");
            }
        });

        water5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    waterTextView.setText("2000 ml");
                else
                    waterTextView.setText("1600 ml");
            }
        });

        db = FirebaseDatabase.getInstance("https://iable-72f9a-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        // вывод приветствия в соответствии со временем
        String currentTime = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
        int time = Integer.parseInt(currentTime.trim());
        if (time >= 4 && time < 12)
            dayTime = "Доброе утро";
        if (time >= 12 && time < 18)
            dayTime = "Добрый день";
        if (time >= 18 && time < 23)
            dayTime = "Добрый вечер";
        if (time >= 23 || time < 4 )
            dayTime = "Доброй ночи";


        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                greeting = (TextView)findViewById(R.id.greeting);
                greeting.setText(dayTime + ", " + name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //вывод рандомных фраз
        Random random = new Random();
        String[] phrases = {
                "Вы сегодня потрясны!",
                "Улыбнитесь!:)",
                "Не забывайте пить воду!:)",
                "Вы делаете этот мир лучше!",
                "Хорошего дня!"
        };
        int index = random.nextInt(phrases.length);
        String randomPhrase = phrases[index];

        greetingPhrases = (TextView)findViewById(R.id.greetingPhrases);
        greetingPhrases.setText(randomPhrase);

        steps_counter = findViewById(R.id.steps_counter);
        steps_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PedometerActivity.class);
                startActivity(intent);
            }
        });

        // переход на бургер меню
        ImageButton drawer_menu = findViewById(R.id.drawer_menu);
        drawer_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DrawerMenuActivity.class);
                startActivity(intent);
            }
        });

        //нижнее меню
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_achievements);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
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
                    startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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
