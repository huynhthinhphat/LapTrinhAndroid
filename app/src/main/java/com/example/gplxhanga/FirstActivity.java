package com.example.gplxhanga;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gplxhanga.entities.Item;
import com.example.gplxhanga.adapter.ItemAdapter;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private ListView listView;
    private List<Item> itemList;
    private ItemAdapter adapter;
    private ImageButton btnBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
        setRecyclerView();
        clickmenu();
        setUpBtn();
    }

    private void init(){
        listView = findViewById(R.id.listViewFirst);
        itemList = new ArrayList<>();
        adapter = new ItemAdapter(FirstActivity.this, R.layout.item_first, itemList);
        btnBack = findViewById(R.id.btn_back_hlt);
    }

    private void setRecyclerView(){
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new Item(R.drawable.note_alt, "KHÁI NIỆM VÀ QUY TẮC", "Gồm 83 câu hỏi", " (18 Câu điểm liệt)", 0 , 83));
        itemList.add(new Item(R.drawable.people_talk, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, 5 ,5));
        itemList.add(new Item(R.drawable.person_biking_solid, "KỸ THUẬT LÁI XE", "Gồm 12 câu hỏi", "(2 Câu điểm liệt)", 7,12));
        itemList.add(new Item(R.drawable.signs_post, "BIỂN BÁO ĐƯỜNG BỘ", "Gồm 65 câu hỏi", null, 6, 65));
        itemList.add(new Item(R.drawable.car_burst_solid, "SA HÌNH", "Gồm 35 câu hỏi", null, 22, 35));
        itemList.add(new Item(R.drawable.triangle_exclamation_solid, "TỔNG HỢP CÂU ĐIỂM LIỆT", "Gồm 20 câu hỏi", "20 Câu điểm liệt", 17,20));


        listView.setAdapter(adapter);
    }

    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void clickmenu(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(FirstActivity.this, FirstStartActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                }
            }
        });
    }


}
