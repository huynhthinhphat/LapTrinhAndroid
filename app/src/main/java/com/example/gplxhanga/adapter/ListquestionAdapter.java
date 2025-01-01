package com.example.gplxhanga.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gplxhanga.R;
import com.example.gplxhanga.entities.Item;
import com.example.gplxhanga.entities.ItemQuestion;
import java.util.List;
public class ListquestionAdapter extends RecyclerView.Adapter<ListquestionAdapter.QuestionViewHolder> {

    private List<ItemQuestion> list;
    private itemClick clickitem;

    public interface itemClick{
        void onClickItemQuestion(ItemQuestion item, int positions);
    }

    public ListquestionAdapter(List<ItemQuestion> list, itemClick clickitem) {
        this.list = list;
        this.clickitem = clickitem;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_first_start_item,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        ItemQuestion item = list.get(position);

        int i = position;
        if(item == null){
            return;
        }
            holder.tv.setText(String.valueOf(position+1));
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickitem.onClickItemQuestion(item,i );
                }
            });

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public ItemQuestion getItem(int position) {
        return list.get(position);
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item_question);
        }
    }
}
