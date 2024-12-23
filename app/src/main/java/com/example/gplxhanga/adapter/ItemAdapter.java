package com.example.gplxhanga.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.R;
import com.example.gplxhanga.entities.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final List<Item> itemList;
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.icon.setImageResource(item.getIcon());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.paralysis_points.setText(item.getParalysis_points());
        holder.quantity_question.setText(item.getQuantity_question());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView title;
        private TextView description;
        private TextView paralysis_points;
        private TextView quantity_question;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.item_icon);
        title = itemView.findViewById(R.id.item_title);
        description = itemView.findViewById(R.id.item_description_1);
        paralysis_points = itemView.findViewById(R.id.item_description_2);
        quantity_question = itemView.findViewById(R.id.item_quantity);
    }
}
}
