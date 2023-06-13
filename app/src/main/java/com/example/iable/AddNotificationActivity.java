package com.example.iable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.iable.Models.Note;
import com.example.iable.Models.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

public class AddNotificationActivity extends AppCompatActivity {

    ImageButton backToNotif;
    EditText titleEditText, contentEditText;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        saveBtn = findViewById(R.id.btnAddNotification);

        saveBtn.setOnClickListener( (v) -> saveNote());

        backToNotif = findViewById(R.id.backToNotif);
        backToNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNotificationActivity.this, NotificationsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(noteTitle == null || noteTitle.isEmpty()){
            titleEditText.setError("Введите название");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);

        saveNoteToFirebase(note);
    }

    void saveNoteToFirebase (Note note) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //note is added
                    Utility.showToast(AddNotificationActivity.this, "Напоминание добавлено");
                    finish();
                } else {
                    Utility.showToast(AddNotificationActivity.this, "Напоминание НЕ добавлено");
                }
            }
        });
    }
}