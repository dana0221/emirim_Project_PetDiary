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

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            if(editId.getText().toString().replace(" ","").equals("")){
                Toast.makeText(v.getContext(), "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            }else if(editPw.getText().toString().replace(" ","").equals("")){
                Toast.makeText(v.getContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "로그인 완료", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        }
    };
}