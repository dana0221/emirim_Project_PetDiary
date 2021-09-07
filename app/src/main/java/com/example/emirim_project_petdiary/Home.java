package com.example.emirim_project_petdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imgNote = findViewById(R.id.btn_diary);
        ImageView imgAdd = findViewById(R.id.btn_add);

        imgNote.setOnClickListener(imgListener);
        imgAdd.setOnClickListener(imgListener);
    }

    View.OnClickListener imgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.btn_diary:
                    intent = new Intent(getApplicationContext(), Diary.class);
                    startActivity(intent);
                    break;
                case R.id.btn_add:
                    intent = new Intent(getApplicationContext(), Add.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}