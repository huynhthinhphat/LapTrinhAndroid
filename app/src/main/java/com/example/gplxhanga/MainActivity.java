package com.example.gplxhanga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.api.ApiService;
import com.example.gplxhanga.entities.CauHoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnLyThuyet;
    private Button btnMeoThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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
    }

    private void btnLyThuyetr_CallApi(){
        ApiService.apiService.findALlQuestion().enqueue(new Callback<List<CauHoi>>() {
            @Override
            public void onResponse(@NonNull Call<List<CauHoi>> call, @NonNull Response<List<CauHoi>> response) {
                Toast.makeText(MainActivity.this, "Call thành công", Toast.LENGTH_SHORT).show();
                assert response.body() != null;
                for(CauHoi cauhoi: response.body()){
                    Log.d("MainActivity", String.valueOf(cauhoi.getMaCauHoi()) + " " + cauhoi.getCauHoi() + " " + cauhoi.getLoaiCauHoi());
                }
            }

            @Override
            public void onFailure(Call<List<CauHoi>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sửa lại địa chỉ ip tao ghi trong ApiService đi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}