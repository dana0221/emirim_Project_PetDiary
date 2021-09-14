package com.example.emirim_project_petdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary extends AppCompatActivity {
    final private static String TAG = "태그명";
    ImageView imgBack, imgv, img2, img3;
    Button btnSave;
    CheckBox checkWalk, checkPlay, checkFeed;
    final static int TAKE_PICTURE = 1;
    String mCurrentPhotoPaht;
    final static int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
//        tedPermission();

        imgBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);
        checkWalk = findViewById(R.id.check_walk);
        checkPlay = findViewById(R.id.check_play);
        checkFeed = findViewById(R.id.check_feed);
        imgv = findViewById(R.id.img1);
        Button btnPhoto = findViewById(R.id.btn_photo);

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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "권한 설정 완료");
            }else {
                Log.d(TAG, "권한 설정 요철");
            }

            ActivityCompat.requestPermissions(Diary.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_photo:
                        Intent cameralIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameralIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "Permission : " + permissions[0] + "was" + grantResults[0]);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try { switch (requestCode) {
            case REQUEST_TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    File file = new File(mCurrentPhotoPaht);
                    Bitmap bitmap;
                    if (Build.VERSION.SDK_INT >= 29) {
                        ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), Uri.fromFile(file));
                        try {
                            bitmap = ImageDecoder.decodeBitmap(source);
                            if (bitmap != null) {
                                imgv.setImageBitmap(bitmap);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                            if (bitmap != null) {
                                imgv.setImageBitmap(bitmap);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } break;
            }
        }

        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, ".jpg", storageDir
        );

        mCurrentPhotoPaht = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent(){
        Intent tackPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(tackPictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }catch (IOException ex){}

            if(photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.emirim_test_photo.fileprovider", photoFile);
                tackPictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(tackPictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
}