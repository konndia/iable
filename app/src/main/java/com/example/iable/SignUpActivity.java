package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private TextView sign_in_txt;
    private Button button_register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        button_register = findViewById(R.id.button_register);
        sign_in_txt = findViewById(R.id.sign_in_txt);

        auth = FirebaseAuth.getInstance();

        sign_in_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {

            button_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                    } else {
                        auth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(SignUpActivity.this,
                                                    "Ошибка. Проверьте все поля и попробуйте снова", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }
    }
}