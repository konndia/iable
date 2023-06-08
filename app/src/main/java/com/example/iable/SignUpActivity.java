package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.iable.Models.User;


public class SignUpActivity extends AppCompatActivity {

    EditText email_registration, password_registration, name, height, weight, age;
    TextView sign_in_txt;
    Button button_register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email_registration = findViewById(R.id.email_registration);
        password_registration = findViewById(R.id.password_registration);
        name = findViewById(R.id.name);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);
        sign_in_txt = findViewById(R.id.sign_in_txt);
        button_register = findViewById(R.id.button_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://iable-72f9a-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users");

        sign_in_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser created_user = auth.getCurrentUser();
        if (created_user != null) {
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            button_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // проверка на пустоту полей
                    if (email_registration.getText().toString().isEmpty() || password_registration.getText().toString().isEmpty()
                            || name.getText().toString().isEmpty() || height.getText().toString().isEmpty() || weight.getText().toString().isEmpty()
                            || age.getText().toString().isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                    } else {
                        // регистрация пользователя
                        auth.createUserWithEmailAndPassword(email_registration.getText().toString(), password_registration.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        User user = new User();
                                        user.setEmail(email_registration.getText().toString());
                                        user.setPass(password_registration.getText().toString());
                                        user.setName(name.getText().toString());
                                        user.setHeight(height.getText().toString());
                                        user.setWeight(weight.getText().toString());
                                        user.setAge(age.getText().toString());

                                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(SignUpActivity.this, "Ошибка аутентификации", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                });
                    }
                }
            });
        }
    }
}