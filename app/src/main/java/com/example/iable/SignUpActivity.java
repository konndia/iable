package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.iable.Models.User;


public class SignUpActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private TextView sign_in_txt;
    private Button button_register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        button_register = findViewById(R.id.button_register);
        sign_in_txt = findViewById(R.id.sign_in_txt);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

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
                        ShowUserInformationWindow();
//                        auth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()) {
//                                            ShowUserInformationWindow();
////                                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
////                                            startActivity(intent);
////                                            finish();
//                                        } else {
//                                            Toast.makeText(SignUpActivity.this,
//                                                    "Ошибка. Проверьте все поля и попробуйте снова", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
                        }
                }
            });
        }
    }

    private void ShowUserInformationWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Введите свои данные");
        dialog.setMessage("Введите данные для продолжения");

        LayoutInflater inflater = LayoutInflater.from(this);
        View user_information = inflater.inflate(R.layout.user_information_window, null);
        dialog.setView(user_information);

        final MaterialEditText name = user_information.findViewById(R.id.nameField);
        final MaterialEditText height = user_information.findViewById(R.id.heightField);
        final MaterialEditText weight = user_information.findViewById(R.id.weightField);
        final MaterialEditText age = user_information.findViewById(R.id.ageField);

//        auth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            User user = new User();
//                            user.setEmail(email_register.getText().toString());
//                            user.setPass(password_register.getText().toString());
//                            user.setName(name.getText().toString());
//                            user.setHeight(height.getText().toString());
//                            user.setWeight(weight.getText().toString());
//                            user.setAge(age.getText().toString());
//
//                            users.child(user.getEmail())
//                                    .setValue(user);
//
//
//                        } else {
//                            Toast.makeText(SignUpActivity.this,
//                                    "Ошибка. Проверьте все поля и попробуйте снова", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

//        User user = new User();
//        user.setEmail(email_register.getText().toString());
//        user.setPass(password_register.getText().toString());
//        user.setName(name.getText().toString());
//        user.setHeight(height.getText().toString());
//        user.setWeight(weight.getText().toString());
//        user.setAge(age.getText().toString());
//
//        users.child(user.getEmail())
//                .setValue(user);
//
        dialog.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(height.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(weight.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(age.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                }

                else {
                    auth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User user = new User();
                                        user.setEmail(email_register.getText().toString());
                                        user.setPass(password_register.getText().toString());
                                        user.setName(name.getText().toString());
                                        user.setHeight(height.getText().toString());
                                        user.setWeight(weight.getText().toString());
                                        user.setAge(age.getText().toString());

                                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user);

                                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(SignUpActivity.this,
                                                "Ошибка. Проверьте все поля и попробуйте снова.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }



            }
        });

        dialog.show();
    }
}