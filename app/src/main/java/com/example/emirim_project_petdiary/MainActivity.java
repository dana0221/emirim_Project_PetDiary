package com.example.emirim_project_petdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        Button btnJoin = findViewById(R.id.btn_join);

        btnLogin.setOnClickListener(btnListener);
        btnJoin.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.btn_login:
                    intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                    break;
                case R.id.btn_join:
                    intent = new Intent(getApplicationContext(), join.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}