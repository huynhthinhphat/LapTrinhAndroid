package com.example.gplxhanga;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gplxhanga.utils.Database;
import com.example.gplxhanga.entities.Item;
import com.example.gplxhanga.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private ListView listView;
    private List<Item> itemList;
    private ItemAdapter adapter;
    private ImageButton btnBack;
    private ImageButton btnDeleteAll;
    private Database dtb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
        clickmenu();
        setUpBtn();
        registerForContextMenu(listView);

        setRecyclerView();
    }

    private void init(){
        listView = findViewById(R.id.listViewFirst);
        itemList = new ArrayList<>();
        adapter = new ItemAdapter(FirstActivity.this, R.layout.item_first, itemList);
        btnBack = findViewById(R.id.btn_back_hlt);
        btnDeleteAll = findViewById(R.id.btn_delete_hlt);
        dtb = new Database(FirstActivity.this);
        adapter.notifyDataSetChanged();
    }

    private void setRecyclerView(){
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new Item(R.drawable.note_alt, "KHÁI NIỆM VÀ QUY TẮC", "Gồm 83 câu hỏi", " (18 Câu điểm liệt)", dtb.getLearn(1).size(), 83));
        itemList.add(new Item(R.drawable.people_talk, "VĂN HÓA VÀ ĐẠO ĐỨC LÁI", "Gồm 5 câu hỏi", null, dtb.getLearn(2).size() ,5));
        itemList.add(new Item(R.drawable.person_biking_solid, "KỸ THUẬT LÁI XE", "Gồm 12 câu hỏi", "(2 Câu điểm liệt)", dtb.getLearn(3).size(),12));
        itemList.add(new Item(R.drawable.signs_post, "BIỂN BÁO ĐƯỜNG BỘ", "Gồm 65 câu hỏi", null, dtb.getLearn(4).size(), 65));
        itemList.add(new Item(R.drawable.car_burst_solid, "SA HÌNH", "Gồm 35 câu hỏi", null, dtb.getLearn(5).size(), 35));
        itemList.add(new Item(R.drawable.triangle_exclamation_solid, "TỔNG HỢP CÂU ĐIỂM LIỆT", "Gồm 20 câu hỏi", "20 Câu điểm liệt", dtb.getLearn(6).size(),20));


        listView.setAdapter(adapter);
    }

    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }
    private void clickmenu(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent_1 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_1.putExtra("typequestion",1);
                        intent_1.putExtra("toolbar", "Khái niệm và quy tắc");
                        startActivity(intent_1);
                        break;
                    case 1:
                        Intent intent_2 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_2.putExtra("typequestion",2);
                        intent_2.putExtra("toolbar", "Văn hóa và đạo đức lái");
                        startActivity(intent_2);
                        break;
                    case 2:
                        Intent intent_3 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_3.putExtra("typequestion",3);
                        intent_3.putExtra("toolbar", "Kỹ thuật lái xe");
                        startActivity(intent_3);
                        break;
                    case 3:
                        Intent intent_4 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_4.putExtra("typequestion",4);
                        intent_4.putExtra("toolbar", "Biển báo đường bộ");
                        startActivity(intent_4);
                        break;
                    case 4:
                        Intent intent_5 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_5.putExtra("typequestion",5);
                        intent_5.putExtra("toolbar", "Sa hình");
                        startActivity(intent_5);
                        break;
                    case 5:
                        Intent intent_6 = new Intent(FirstActivity.this, FirstStartActivity.class);
                        intent_6.putExtra("typequestion",6);
                        intent_6.putExtra("toolbar", "Tổng hợp câu điểm liệt");
                        startActivity(intent_6);
                        break;
                }
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database dtb = new Database(FirstActivity.this);
                        if(dtb.deleteAllLearn()){
                            Toast.makeText(FirstActivity.this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                            recreate();
                        }else{
                            Toast.makeText(FirstActivity.this,"Lỗi xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Database dtb = new Database(FirstActivity.this);
        AdapterView.AdapterContextMenuInfo vitri = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.delete) {
            dtb.deleteLearnByType(vitri.position+1);
            recreate();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
