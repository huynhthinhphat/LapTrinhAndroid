package com.example.gplxhanga;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.gplxhanga.dao.Database;
import com.example.gplxhanga.entities.CauHoi;
import com.example.gplxhanga.entities.HistoryLearn;
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
    private TextView tvquestion, tvqs, explanation;
    private RadioButton answerA, answerB, answerC, answerD;
    private RadioGroup groupAnswer;
    private String answerTrue;
    private int position;
    private ImageButton btnleft, btnright;
    private ItemQuestion itemQS;
    private Database dtb;
    private ImageButton btnDelete;

    private String typequestion, nametool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        Intent intentsecond = getIntent();
        typequestion = intentsecond.getStringExtra("typequestion");
        nametool = intentsecond.getStringExtra("toolbar");

        init();
        callapi(typequestion);
        nextandback();
        changebtn();
        setUpBtn();
        setPositionToolBarBottom();
    }

    private void init() {
        dtb = new Database(FirstStartActivity.this);
        groupAnswer = findViewById(R.id.groupAnswer);
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


        TextView nameToolbar = findViewById(R.id.toolbar_first_name);
        nameToolbar.setText(""+nametool);

        btnDelete = findViewById(R.id.btn_delete_hlt);
        btnDelete.setVisibility(View.GONE);

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
                groupAnswer.clearCheck();
                getitem(item, position);
            }
        },FirstStartActivity.this);

        recyclerView.setAdapter(listquestionAdapter);  // Set the adapter to RecyclerView
    }
    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void callapi(String typequestion) {
        ApiService.apiService.findALlQuestion(typequestion).enqueue(new Callback<List<ItemQuestion>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<ItemQuestion>> call, Response<List<ItemQuestion>> response) {
                if (response.body() != null) {
                    for (ItemQuestion cauhoi : response.body()) {
                        list.add(cauhoi);
                    }
                    listquestionAdapter.notifyDataSetChanged();  // Notify adapter after data is loaded

                    // Gọi getitem() với câu hỏi đầu tiên (vị trí 0) sau khi dữ liệu đã được tải
                    if (!list.isEmpty()) {
                        getitem(list.get(0), 0);  // Hiển thị câu hỏi đầu tiên
                        tvqs.setText("1/" + list.size());  // Hiển thị số câu hỏi hiện tại
                    }
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
                       groupAnswer.clearCheck();
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
                        groupAnswer.clearCheck();
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
        itemQS = itemQuestion;
        boolean foundAnswer = false;
        List<HistoryLearn> listhtr = dtb.getLearn("1");
        tvquestion.setText(itemQuestion.getQuestionText());
        answerA.setText(itemQuestion.getOption1());
        answerB.setText(itemQuestion.getOption2());
        answerC.setText(itemQuestion.getOption3());
        explanation.setText(itemQuestion.getExplanation());
        answerTrue = itemQuestion.getCorrectAnswer();

        // Kiểm tra số lượng đáp án và ẩn các RadioButton thừa
        if (itemQuestion.getOption4() == null) {
            answerD.setVisibility(View.GONE);  // Ẩn RadioButton D nếu không có đáp án thứ 4
        } else {
            answerD.setVisibility(View.VISIBLE);  // Hiển thị RadioButton D nếu có đáp án thứ 4
            answerD.setText(itemQuestion.getOption4());
        }

        // Kiểm tra xem câu hỏi này đã được học chưa và chọn đáp án đã chọn (nếu có)
        for (int i = 0; i < listhtr.size(); i++) {
            HistoryLearn htrlearn = listhtr.get(i);
            String answerhtr = htrlearn.getAnswer_Select();
            if (itemQuestion.getId() == htrlearn.getId_Question()) {
                foundAnswer = true;
                if (answerA.getText().toString().trim().equals(answerhtr.trim())) {
                    answerA.setChecked(true);
                    checkandsetcolor(answerTrue, answerA);
                } else if (answerB.getText().toString().trim().equals(answerhtr.trim())) {
                    answerB.setChecked(true);
                    checkandsetcolor(answerTrue, answerB);
                } else if (answerC.getText().toString().trim().equals(answerhtr.trim())) {
                    answerC.setChecked(true);
                    checkandsetcolor(answerTrue, answerC);
                } else if (answerD.getText().toString().trim().equals(answerhtr.trim())) {
                    answerD.setChecked(true);
                    checkandsetcolor(answerTrue, answerD);
                }
            }
        }

        if (!foundAnswer) {

            setColorText(-1);
        }
    }

    private void changebtn(){
        groupAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioselect = groupAnswer.getCheckedRadioButtonId();
                if (radioselect != -1) {
                    RadioButton btnselect = findViewById(radioselect);
                    checkandsetcolor(answerTrue,btnselect);
                    setColorText(radioselect);
                    dtb.addLearn(itemQS, (String) btnselect.getText());
                }else {
                    explanation.setVisibility(View.GONE);
                }
                listquestionAdapter.notifyDataSetChanged();
            }
        });
    }
    private void checkandsetcolor(String aswtrue, RadioButton rdobtn){
        if (aswtrue.equals(rdobtn.getText())) {
            rdobtn.setTextColor(Color.GREEN);
            explanation.setVisibility(View.VISIBLE);
        } else {
            rdobtn.setTextColor(Color.RED);
            explanation.setVisibility(View.GONE);
        }
    }

    private void setColorText(int idchecked){
        if(answerA.getId() != idchecked){
            answerA.setTextColor(Color.WHITE);
        }
        if (answerB.getId() != idchecked) {
            answerB.setTextColor(Color.WHITE);
        }
        if (answerC.getId() != idchecked) {
            answerC.setTextColor(Color.WHITE);
        }
        if (answerD.getId() != idchecked) {
            answerD.setTextColor(Color.WHITE);
        }
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

