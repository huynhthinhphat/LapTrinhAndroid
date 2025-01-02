package com.example.gplxhanga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnLyThuyet;
    private Button btnMeoThi;
    private Button btnBienBao;
    private Button btnThiSatHach;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setUpBtn();

    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMeoThi = findViewById(R.id.btn_meo_thi);
        btnLyThuyet = findViewById(R.id.btn_ly_thuyet);
        btnBienBao = findViewById(R.id.btn_bien_bao);
        btnThiSatHach = findViewById(R.id.btn_thi);
        img = findViewById(R.id.img);
        Glide.with(this)
                .load("data:image/png;base64,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")
                .into(img);

    }

    private void setUpBtn(){
        btnLyThuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        btnMeoThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });

        btnBienBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnThiSatHach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirtActivity.class);
                startActivity(intent);
            }
        });
    }
}