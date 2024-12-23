package com.example.gplxhanga;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.entities.Item;
import com.example.gplxhanga.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Item> itemList;
    private ItemAdapter adapter;
    private ImageButton btnBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
        setRecyclerView();
        setUpBtn();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerViewFirst);
        itemList = new ArrayList<>();
        adapter = new ItemAdapter(itemList);

        btnBack = findViewById(R.id.btn_back_hlt);
    }

    private void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new Item(R.drawable.note_alt, "KHÁI NIỆM VÀ QUY TẮC", "Gồm 83 câu hỏi", " (18 Câu điểm liệt)", 0 + "/83"));
        itemList.add(new Item(R.drawable.people_talk, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 83 + "/83"));
        itemList.add(new Item(R.drawable.person_biking_solid, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 83 + "/83"));
        itemList.add(new Item(R.drawable.signs_post, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 83 + "/83"));
        itemList.add(new Item(R.drawable.car_burst_solid, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 83 + "/83"));
        itemList.add(new Item(R.drawable.triangle_exclamation_solid, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 83 + "/83"));


        recyclerView.setAdapter(adapter);
    }

    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
