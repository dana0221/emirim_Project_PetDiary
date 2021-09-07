package com.example.emirim_project_petdiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Diary extends AppCompatActivity {
    ImageView imgBack, img1, img2, img3;
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
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

//        img1.setOnClickListener(imgListener);
//        img2.setOnClickListener(imgListener);
//        img3.setOnClickListener(imgListener);

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
                Toast.makeText(getApplicationContext(), "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
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

//    View.OnClickListener imgListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, REQUEST_CODE);
//        }
//    };
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null) {
//            //response에 getData , return data 부분 추가해주어야 한다
//
//            selectedImage = data.getData();
//            Uri photoUri = data.getData();
//            Bitmap bitmap = null;
//            //bitmap 이용
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photoUri);
//                bitmap = rotateImage(bitmap, 90);
//                //사진이 돌아가 있는 경우 rotateImage 함수 이용해서 사진 회전 가능
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            //이미지뷰에 이미지 불러오기
//            img1.setImageBitmap(bitmap);
//
//            Cursor cursor = getContentResolver().query(Uri.parse(selectedImage.toString()), null, null, null, null);
//            assert cursor != null;
//            cursor.moveToFirst();
//            String mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
//            Log.d("경로 확인 >> ", "$selectedImg  /  $absolutePath");
//
//        }else{
//            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public static Bitmap rotateImage(Bitmap source, float angle) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(angle);
//        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
//    }
}