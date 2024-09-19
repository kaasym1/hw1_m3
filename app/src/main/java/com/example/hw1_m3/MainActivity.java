package com.example.hw1_m3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button openSecondActivityButton;
    private Button openGalleryButton;
    private ImageView imageView;
    Button openEmailButton;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSecondActivityButton = findViewById(R.id.btn_open_second_activity);
        openGalleryButton = findViewById(R.id.btn_open_gallery);
        imageView = findViewById(R.id.iv_image);
        openEmailButton = findViewById(R.id.btn_open_email);

        openSecondActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", "salam");
            startActivity(intent);
        });

        openGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        openEmailButton.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("massage/rfs822");
            startActivity(Intent.createChooser(intent, ""));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
