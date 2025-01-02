package com.example.gplxhanga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.R;
import com.example.gplxhanga.entities.Item;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Item> list;

    public ItemAdapter(Context context, int layout, List<Item> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        ImageView icon = view.findViewById(R.id.item_icon);
        TextView title = view.findViewById(R.id.item_title);
        TextView description = view.findViewById(R.id.item_description_1);
        TextView paralysis_points = view.findViewById(R.id.item_description_2);
        TextView quantity_question = view.findViewById(R.id.item_quantity);
        ProgressBar progress = view.findViewById(R.id.item_progress);

        Item item = list.get(i);

        icon.setImageResource(item.getIcon());
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        paralysis_points.setText(item.getParalysis_points());
        quantity_question.setText(item.getQuestion_excute()+"/"+item.getQuantity_question());
        progress.setMax(item.getQuantity_question());
        progress.setProgress(item.getQuestion_excute());
        return view;
    }
}