package com.example.gplxhanga.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gplxhanga.R;
import com.example.gplxhanga.ThirtActivity;
import com.example.gplxhanga.ThirtItemActivity;
import com.example.gplxhanga.entities.Item;
import com.example.gplxhanga.entities.ItemTopic;

import java.util.List;

public class ItemTestAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemTopic> list;

    public ItemTestAdapter(Context context, int layout, List<ItemTopic> list) {
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

        TextView questionnumber = view.findViewById(R.id.questionnumber);
        TextView khongdat = view.findViewById(R.id.khongdat);
        TextView topic = view.findViewById(R.id.topic);
        TextView item_score = view.findViewById(R.id.item_score);
        Button btn_start = view.findViewById(R.id.btn_start);

        ItemTopic item = list.get(i);

        questionnumber.setText(String.valueOf(item.getNumberQuestion()));
        topic.setText(String.valueOf(item.getNumberTopic()));
        item_score.setText(String.valueOf(item.getTime()));

        if(item.isIspass() == 0){
            khongdat.setVisibility(View.GONE);
        }else {
            if(item.isIspass() == 1){
                khongdat.setVisibility(View.VISIBLE);
            }else{
                khongdat.setVisibility(View.VISIBLE);
                khongdat.setBackgroundColor(Color.GREEN);
                khongdat.setText("Đạt");
            }
            btn_start.setVisibility(View.GONE);
        }

        setUpBtn(btn_start, i + 1);

        return view;
    }

    private void setUpBtn(Button button, int position){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(context, ThirtItemActivity.class);
                itent.putExtra("topic", position);

                context.startActivity(itent);
            }
        });
    }
}
