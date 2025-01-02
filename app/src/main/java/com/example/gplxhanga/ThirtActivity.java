package com.example.gplxhanga;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gplxhanga.adapter.ItemTestAdapter;
import com.example.gplxhanga.dao.Database;
import com.example.gplxhanga.entities.ItemTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThirtActivity extends AppCompatActivity {

    private ListView listitem;
    private List<ItemTopic> list;
    private ItemTestAdapter adapter;
    private Toolbar toolbar;
    private TextView nameToolbar;
    private ImageButton btnDelete;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirt);
        init();
        setListView();
        setUpBtn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }
    private void init(){
        toolbar = findViewById(R.id.toolbarfirst);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        listitem = findViewById(R.id.listitems);
        adapter = new ItemTestAdapter(ThirtActivity.this, R.layout.activity_thirt_item, list);

        nameToolbar = findViewById(R.id.toolbar_first_name);
        nameToolbar.setText("Thi sát hạch");

        btnDelete = findViewById(R.id.btn_delete_hlt);
        btnBack = findViewById(R.id.btn_back_hlt);
    }

    public void setListView() {
        Database dtb = new Database(ThirtActivity.this);
        for (int i = 1; i <= 8; i++) {
            List<HashMap<String, Integer>> list_temp = dtb.getTopicTB(i);
            int isPass = 0;
            String line_3 = "Thời gian 19 phút";

            if (list_temp != null && !list_temp.isEmpty()) {
                HashMap<String, Integer> map = list_temp.get(0);
                if (map != null) {
                    if(map.get("isPass") != null && map.get("score") != null && map.get("score") != null){
                        isPass = map.get("isPass");
                        line_3 = "Đúng: " + String.valueOf(map.get("score")) +"/25\nSố câu điểm liệt sai: " + String.valueOf(map.get("cauLiet"));
                    }
                }
            }

            list.add(new ItemTopic("Đề số " + i, "25 câu hỏi", line_3, isPass));
        }

        // Cập nhật giao diện
        adapter.notifyDataSetChanged();
        listitem.setAdapter(adapter);
    }

    private void setUpBtn(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void dialogDelete(){
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn chắc chắn muốn xóa?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database dtb = new Database(ThirtActivity.this);
                        if(dtb.deleteAllExam()) {
                            Toast.makeText(ThirtActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ThirtActivity.this, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                        }
                        recreate();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}