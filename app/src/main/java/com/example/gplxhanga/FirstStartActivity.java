package com.example.gplxhanga;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.adapter.ListquestionAdapter;
import com.example.gplxhanga.api.ApiService;
import com.example.gplxhanga.entities.CauHoi;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstStartActivity extends AppCompatActivity{
    private boolean isToolbarMoved = false;

    private RecyclerView recyclerView;
    private List<ItemQuestion> list;
    private ListquestionAdapter listquestionAdapter;
    private ConstraintLayout btn_toolbar;
    private Toolbar toolbar;
    private ImageButton btnBack;
    private TextView tvquestion, answerA, answerB, answerC, answerD, tvqs, explanation;
    private String answerTrue;
    private int position;
    private ImageButton btnleft, btnright;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);
        init();
        callapi();  // Call API after initializing the adapter
        nextandback();
        checkdapan();
        setPositionToolBarBottom();
    }

    private void init() {
        btnleft = findViewById(R.id.btn_chervon_left_first);
        btnright = findViewById(R.id.btn_chervon_right_first);
        tvqs = findViewById(R.id.socauhoi);
        explanation = findViewById(R.id.explanation);
        btn_toolbar = findViewById(R.id.btn_move_toolbar);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        recyclerView = findViewById(R.id.rv_question_list);
        toolbar = findViewById(R.id.toolbar_bottom);
        setSupportActionBar(toolbar);
        btnBack = findViewById(R.id.btn_back_hlt);
        tvquestion = findViewById(R.id.question);

        // Set up the RecyclerView with GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);  // 7 columns
        recyclerView.setLayoutManager(gridLayoutManager);
        list = new ArrayList<>();

        // Initialize the adapter and pass the list
        listquestionAdapter = new ListquestionAdapter(list, new ListquestionAdapter.itemClick() {
            @Override
            public void onClickItemQuestion(ItemQuestion item, int positions) {
                position = positions;
                tvqs.setText(String.valueOf(position + 1) + "/" + list.size());
                getitem(item, position);
            }
        });

        recyclerView.setAdapter(listquestionAdapter);  // Set the adapter to RecyclerView
    }

    private void callapi() {
        ApiService.apiService.findALlQuestion().enqueue(new Callback<List<ItemQuestion>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<ItemQuestion>> call, Response<List<ItemQuestion>> response) {
                if (response.body() != null) {
                    for (ItemQuestion cauhoi : response.body()) {
                        if (cauhoi.getQuestionType().equals("1")) {
                            list.add(cauhoi);
                        }
                    }
                    listquestionAdapter.notifyDataSetChanged();  // Notify adapter after data is loaded
                } else {
                    Toast.makeText(FirstStartActivity.this, "No data received", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemQuestion>> call, Throwable t) {
                Toast.makeText(FirstStartActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nextandback(){

            btnleft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(position>0){
                       position--;
                       getitem(listquestionAdapter.getItem(position),position);
                       tvqs.setText(String.valueOf(position+1)+"/"+list.size());
                   }
                   else {
                       Toast.makeText(FirstStartActivity.this, "Đã đến câu hỏi đầu tiên", Toast.LENGTH_SHORT).show();
                   }
                }
            });


            btnright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position<list.size()-1){
                        position++;
                        getitem(listquestionAdapter.getItem(position),position);
                        tvqs.setText(String.valueOf(position+1)+"/"+list.size());
                    }
                    else{
                        Toast.makeText(FirstStartActivity.this, "Đã đến câu hỏi cuối", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void getitem(ItemQuestion itemQuestion, int positions) {
        tvquestion.setText(itemQuestion.getQuestionText());
        answerA.setText(itemQuestion.getOption1());
        answerB.setText(itemQuestion.getOption2());
        answerC.setText(itemQuestion.getOption3());
        answerD.setText(itemQuestion.getOption4());
        explanation.setText(itemQuestion.getExplanation());
        answerTrue = itemQuestion.getCorrectAnswer();
    }
    private void checkdapan(){
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explanation.setVisibility(View.VISIBLE);
                if(answerA.getText().equals(answerTrue)){
                    answerA.setTextColor(Color.GREEN);
                }
                else {
                    answerA.setTextColor(Color.RED);
                }
            }
        });
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explanation.setVisibility(View.VISIBLE);
                if(answerB.getText().equals(answerTrue)){
                    answerB.setTextColor(Color.GREEN);
                }
                else {
                    answerB.setTextColor(Color.RED);
                }
            }
        });
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(answerC.getText().equals(answerTrue)){
                    answerC.setTextColor(Color.GREEN);
                }
                else {
                    answerC.setTextColor(Color.RED);
                }
            }
        });
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explanation.setVisibility(View.VISIBLE);
                if(answerD.getText().equals(answerTrue)){
                    answerD.setTextColor(Color.GREEN);
                }
                else {
                    answerD.setTextColor(Color.RED);
                }
            }
        });
    }

    private void setColorText(){
        answerA.setTextColor(Color.WHITE);
        answerB.setTextColor(Color.WHITE);
        answerC.setTextColor(Color.WHITE);
        answerD.setTextColor(Color.WHITE);
    }

    private void setPositionToolBarBottom() {
        btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu RecyclerView chưa được hiển thị, thì hiển thị nó
                if (!isToolbarMoved) {
                    // Animate RecyclerView height from 0dp to wrap_content with maxHeight
                    toggleRecyclerViewHeight();
                } else {
                    // Nếu RecyclerView đã hiển thị, ẩn nó
                    toggleRecyclerViewHeight();
                }
                isToolbarMoved = !isToolbarMoved;
            }
        });
    }
    private void toggleRecyclerViewHeight() {
        // Lấy LayoutParams hiện tại của RecyclerView
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();

        // Kiểm tra chiều cao hiện tại của RecyclerView
        if (layoutParams.height == RelativeLayout.LayoutParams.WRAP_CONTENT) {
            // Nếu chiều cao hiện tại là wrap_content, thay đổi thành 0dp
            layoutParams.height = 0;  // Set height to 0dp (0 pixels)
            recyclerView.setLayoutParams(layoutParams);  // Cập nhật LayoutParams
        } else {
            // Nếu chiều cao hiện tại là 0dp, thay đổi thành wrap_content
            layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            recyclerView.setLayoutParams(layoutParams);  // Cập nhật LayoutParams
        }
    }
}

