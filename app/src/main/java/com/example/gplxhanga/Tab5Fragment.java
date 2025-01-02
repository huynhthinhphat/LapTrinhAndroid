package com.example.gplxhanga;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.adapter.SecondAdapter;
import com.example.gplxhanga.entities.BienBaoGiaoThong;

import java.util.List;


public class Tab5Fragment extends Fragment {

    private List<BienBaoGiaoThong> list;

    public Tab5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab5, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            list = (List<BienBaoGiaoThong>) bundle.getSerializable("list_tab5");

            if (list != null) {
                setupRecyclerView(rootView);
            } else {
                Toast.makeText(getContext(), "No data received", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Load data...", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    private void setupRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_second_5);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SecondAdapter adapter = new SecondAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}