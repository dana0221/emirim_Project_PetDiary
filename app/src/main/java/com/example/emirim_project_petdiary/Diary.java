package com.example.emirim_project_petdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class Diary extends AppCompatActivity {
    ImageView imgBack;
    Button btnSave;
    CheckBox checkWalk, checkPlay, checkFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        imgBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);
        checkWalk = findViewById(R.id.check_walk);
        checkPlay = findViewById(R.id.check_play);
        checkFeed = findViewById(R.id.check_feed);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        checkWalk.setOnClickListener(checkListener);
        checkPlay.setOnClickListener(checkListener);
        checkFeed.setOnClickListener(checkListener);
    }

    View.OnClickListener checkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.check_walk:
                    Toast.makeText(getApplicationContext(), "산책하기를 완료했습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.check_play:
                    Toast.makeText(getApplicationContext(), "놀아주기 완료했습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.check_feed:
                    Toast.makeText(getApplicationContext(), "밥주기를 완료했습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}