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



//package com.example.gplxhanga.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.gplxhanga.R;
//import com.example.gplxhanga.entities.Item;
//
//import java.util.List;
//
//public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
//    private final List<Item> itemList;
//    public ItemAdapter(List<Item> itemList) {
//        this.itemList = itemList;
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first, parent, false);
//        return new ItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        Item item = itemList.get(position);
//        holder.icon.setImageResource(item.getIcon());
//        holder.title.setText(item.getTitle());
//        holder.description.setText(item.getDescription());
//        holder.paralysis_points.setText(item.getParalysis_points());
//        holder.quantity_question.setText(item.getQuantity_question());
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemList.size();
//    }
//    class ItemViewHolder extends RecyclerView.ViewHolder{
//        private ImageView icon;
//        private TextView title;
//        private TextView description;
//        private TextView paralysis_points;
//        private TextView quantity_question;
//
//    public ItemViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        icon = itemView.findViewById(R.id.item_icon);
//        title = itemView.findViewById(R.id.item_title);
//        description = itemView.findViewById(R.id.item_description_1);
//        paralysis_points = itemView.findViewById(R.id.item_description_2);
//        quantity_question = itemView.findViewById(R.id.item_quantity);
//    }
//}
//}
