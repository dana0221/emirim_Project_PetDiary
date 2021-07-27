package com.example.emirim_project_petdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText editId, editPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = findViewById(R.id.edit_id);
        editPw = findViewById(R.id.edit_pw);
        Button btnDoan = findViewById(R.id.btn_done);
        btnDoan.setOnClickListener(btnListener);
    }
}