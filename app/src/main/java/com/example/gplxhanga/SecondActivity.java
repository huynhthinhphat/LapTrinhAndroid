package com.example.gplxhanga;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.gplxhanga.adapter.ViewPagerAdapter;
import com.example.gplxhanga.api.ApiService;
import com.example.gplxhanga.entities.BienBaoGiaoThong;
import com.example.gplxhanga.entities.Item;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageButton btnBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        setUpBtn();
        findAllTrafficSigns_CallApi();
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarsecond);
        setSupportActionBar(toolbar);

        btnBack = findViewById(R.id.btn_back_bbgt);

        mTabLayout = findViewById(R.id.tab_layout_second);
        mViewPager = findViewById(R.id.view_pager_second);

        /*ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);*/

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void findAllTrafficSigns_CallApi(){
        ApiService.apiService.findALlTrafficSigns().enqueue(new Callback<List<BienBaoGiaoThong>>() {
            @Override
            public void onResponse(Call<List<BienBaoGiaoThong>> call, Response<List<BienBaoGiaoThong>> response) {

                List<BienBaoGiaoThong> listTab1 = new ArrayList<>();
                List<BienBaoGiaoThong> listTab2 = new ArrayList<>();
                List<BienBaoGiaoThong> listTab3 = new ArrayList<>();
                List<BienBaoGiaoThong> listTab4 = new ArrayList<>();
                List<BienBaoGiaoThong> listTab5 = new ArrayList<>();

                for (BienBaoGiaoThong item : response.body()) {
                    // Thêm điều kiện cho từng loại biển báo
                    if (item.getSignType().trim().equals("Biển báo cấm")) {
                        listTab1.add(item);
                    } else if (item.getSignType().trim().equals("Biển báo nguy hiểm")) {
                        listTab2.add(item);
                    } else if (item.getSignType().trim().equals("Biển báo chỉ dẫn")) {
                        listTab3.add(item);
                    } else if (item.getSignType().trim().equals("Biển báo hiệu lệnh")) {
                        listTab4.add(item);
                    } else {
                        listTab5.add(item);
                    }
                }

                // Tạo Bundle cho từng Fragment
                Bundle bundleTab1 = new Bundle();
                bundleTab1.putSerializable("list_tab1", (Serializable) listTab1);
                Bundle bundleTab2 = new Bundle();
                bundleTab2.putSerializable("list_tab2", (Serializable) listTab2);
                Bundle bundleTab3 = new Bundle();
                bundleTab3.putSerializable("list_tab3", (Serializable) listTab3);
                Bundle bundleTab4 = new Bundle();
                bundleTab4.putSerializable("list_tab4", (Serializable) listTab4);
                Bundle bundleTab5 = new Bundle();
                bundleTab5.putSerializable("list_tab5", (Serializable) listTab5);

                // Khởi tạo các Fragment
                Tab1Fragment fragment1 = new Tab1Fragment();
                fragment1.setArguments(bundleTab1);
                Tab2Fragment fragment2 = new Tab2Fragment();
                fragment2.setArguments(bundleTab2);
                Tab3Fragment fragment3 = new Tab3Fragment();
                fragment3.setArguments(bundleTab3);
                Tab4Fragment fragment4 = new Tab4Fragment();
                fragment4.setArguments(bundleTab4);
                Tab5Fragment fragment5 = new Tab5Fragment();
                fragment5.setArguments(bundleTab5);

                // Tạo mảng chứa các Bundle
                Bundle[] bundles = new Bundle[5];
                bundles[0] = bundleTab1;
                bundles[1] = bundleTab2;
                bundles[2] = bundleTab3;
                bundles[3] = bundleTab4;
                bundles[4] = bundleTab5;

                // Tạo ViewPagerAdapter với dữ liệu Bundle
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundles);

                // Cập nhật ViewPager và TabLayout
                mViewPager.setAdapter(viewPagerAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
            }

            @Override
            public void onFailure(Call<List<BienBaoGiaoThong>> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Call thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
