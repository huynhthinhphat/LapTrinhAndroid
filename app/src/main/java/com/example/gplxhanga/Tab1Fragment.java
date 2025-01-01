package com.example.gplxhanga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gplxhanga.adapter.SecondAdapter;
import com.example.gplxhanga.entities.BienBaoGiaoThong;

import java.util.List;

public class Tab1Fragment extends Fragment {

    private List<BienBaoGiaoThong> list;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);

        // Nhận Bundle và dữ liệu từ Activity
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Kiểm tra nếu dữ liệu tồn tại trong Bundle
            list = (List<BienBaoGiaoThong>) bundle.getSerializable("list_tab1");

            if (list != null) {
                setupRecyclerView(rootView);
            } else {
                // Dữ liệu không có trong Bundle
                Toast.makeText(getContext(), "No data received", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Load data...", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    private void setupRecyclerView(View rootView) {
        // Tìm RecyclerView trong layout
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_second_1);

        // Thiết lập LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tạo Adapter và truyền dữ liệu
        SecondAdapter adapter = new SecondAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}