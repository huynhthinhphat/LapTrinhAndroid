package com.example.gplxhanga.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gplxhanga.R;
import com.example.gplxhanga.entities.BienBaoGiaoThong;

import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {

    private final List<BienBaoGiaoThong> list;

    public SecondAdapter(List<BienBaoGiaoThong> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second, parent, false);
        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {
        BienBaoGiaoThong bienBaoGiaoThong = list.get(position);
        holder.title.setText(bienBaoGiaoThong.getName());  // Set tên biển báo
        holder.description.setText(bienBaoGiaoThong.getContent());  // Set mô tả

        // Tải ảnh từ URL nếu URL hợp lệ
        Glide.with(holder.itemView.getContext())  // Sử dụng context của từng item
                .load(bienBaoGiaoThong.getImage())
                .into(holder.image);  // Tải ảnh vào holder.image
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView description;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_second_image);
            title = itemView.findViewById(R.id.item_second_text_1);
            description = itemView.findViewById(R.id.item_second_text_2);
        }
    }
}
