package com.example.gplxhanga;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gplxhanga.adapter.ListquestionAdapter;
import com.example.gplxhanga.api.ApiService;
import com.example.gplxhanga.utils.Database;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirtItemActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Toolbar toolbarbottom;
    private TextView time;
    private ImageButton btnBack;
    private boolean shouldProceedBack = false;
    private int topic;
    private final List<ItemQuestion> list_question = new ArrayList<>();
    private TextView tvQuestion;
    private RadioGroup radioGroup;
    private RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private ImageButton btnPrev, btnNext;
    private ImageView imageQuestion;
    private Button btnSubmit;
    private int ques_number = 0;
    private final List<HashMap<String, String>> list_answer = new ArrayList<>();
    private TextView questionCurrent;
    private int score = 0;
    private int cauLiet = 0;
    private int isPass = 3;
    private RecyclerView recyclerView;
    private ListquestionAdapter listquestionAdapter;
    private ConstraintLayout btn_toolbar;
    private boolean isToolbarMoved = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        getItent();

        init();
        startCountdown();
        setUpBtn();
        getQuestion();
        saveAnswerTemp();
        setPositionToolBarBottom();
    }

    private void getItent(){
        Intent intent = getIntent();
        topic = intent.getIntExtra("topic", -1);
    }

    public void init(){
        toolbar = findViewById(R.id.toolbarexam);
        setSupportActionBar(toolbar);
        btn_toolbar = findViewById(R.id.btn_move_toolbar);

        toolbarbottom = findViewById(R.id.toolbarfirstitem);
        setSupportActionBar(toolbarbottom);

        time = findViewById(R.id.toolbar_time);
        btnBack = findViewById(R.id.btn_back_exam);

        tvQuestion = findViewById(R.id.question);
        imageQuestion = findViewById(R.id.image);
        radioGroup = findViewById(R.id.groupAnswer);
        radioButtonA = findViewById(R.id.answerA);
        radioButtonB = findViewById(R.id.answerB);
        radioButtonC = findViewById(R.id.answerC);
        radioButtonD = findViewById(R.id.answerD);

        btnPrev = findViewById(R.id.btn_chervon_left_first);
        btnNext = findViewById(R.id.btn_chervon_right_first);
        btnSubmit = findViewById(R.id.btn_submit);

        questionCurrent = findViewById(R.id.socauhoi);

        recyclerView = findViewById(R.id.recycler_view_number_question);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);  // 7 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        listquestionAdapter = new ListquestionAdapter(list_question, new ListquestionAdapter.itemClick() {
            @Override
            public void onClickItemQuestion(ItemQuestion item, int positions) {
                ques_number = positions;
                setQuestion(ques_number);
                questionCurrent.setText(String.valueOf(ques_number+1)+"/25");
            }
        },ThirtItemActivity.this, "test" , list_answer);

        recyclerView.setAdapter(listquestionAdapter);
    }

    private void startCountdown() {
        long startTime = 19 * 60 * 1000;

        new CountDownTimer(startTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeft = String.format("%02d:%02d", minutes, seconds);
                time.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                dialogHetGio();
            }
        }.start(); // Bắt đầu đếm ngược
    }

    private void dialogHetGio(){
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Hết giờ! Hãy nộp bài")
                .setCancelable(false)
                .setPositiveButton("Nộp bài", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submitExam();
                        showResult();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void setUpBtn(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ques_number > 0){
                    questionCurrent.setText(ques_number + "/25");
                    ques_number--;
                    setQuestion(ques_number);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ques_number < list_question.size() - 1){
                    ques_number++;
                    int temp = ques_number + 1;
                    questionCurrent.setText(temp + "/25");
                    setQuestion(ques_number);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_answer.size() < 25){
                    showDialogNoEnough();
                }else{
                    showDialogEnough();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (shouldProceedBack) {
            super.onBackPressed();
        } else {
            showConfirmationDialog();
        }
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Nếu thoát bạn thì bài thi sẽ bị hủy?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldProceedBack = true;
                        onBackPressed();
                        list_answer.clear();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void showDialogNoEnough() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn chưa làm xong. Bạn vẫn muốn nộp?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submitExam();
                        showResult();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void showDialogEnough() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn muốn nộp bài?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submitExam();
                        showResult();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void showResult(){
        String msg = "";

        if(score < 20 || isPass == 1){
            msg = "Rất tiếc! Haha! Bạn đã rớt :D";
        }

        if(isPass == 2){
            msg = "Đỉnh dị seo!!!";
        }

        new AlertDialog.Builder(this)
                .setTitle(msg)
                .setMessage("Đúng: " + score + "/25" + "\n" + "Sai: " + cauLiet + " câu liệt")
                .setCancelable(false)
                .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database dtb = new Database(ThirtItemActivity.this);
                        dtb.addTopicExam(list_answer, topic);
                        dtb.addTopicTB(score, topic, cauLiet, isPass);
                        finish();
                    }
                })
                .show();
    }
    private void submitExam(){
        for (HashMap<String, String> map : list_answer){
            for(ItemQuestion question : list_question){
                if(question.getQuestionText().equals(map.get("question"))){
                    if(question.getCorrectAnswer().equals(map.get("answer"))){
                        score++;
                    }else{
                        if(question.getQuestionType() == 6){
                            cauLiet++;
                            isPass = 1;
                        }
                    }
                }
            }
        }

        if(score < 20){
            isPass = 1;
        }
    }

    private void getQuestion(){
        ApiService.apiService.findALlQuestionByTopic(topic).enqueue(new Callback<List<ItemQuestion>>() {
            @Override
            public void onResponse(Call<List<ItemQuestion>> call, Response<List<ItemQuestion>> response) {
                list_question.addAll(response.body());

                if(!list_question.isEmpty()){
                    setQuestion(0);
                    questionCurrent.setText(1 + "/25");
                }
            }

            @Override
            public void onFailure(Call<List<ItemQuestion>> call, Throwable t) {

            }
        });
    }

    private void setQuestion(int position) {
        radioGroup.clearCheck();

        Glide.with(this)
                .load(list_question.get(position).getImageUrl())
                .into(imageQuestion);

        String question = list_question.get(position).getQuestionText();
        tvQuestion.setText(question);

        radioButtonA.setText(list_question.get(position).getOption1());
        radioButtonB.setText(list_question.get(position).getOption2());
        radioButtonC.setText(list_question.get(position).getOption3());

        if (list_question.get(position).getOption4() != null) {
            radioButtonD.setVisibility(View.VISIBLE);
            radioButtonD.setText(list_question.get(position).getOption4());
        } else {
            radioButtonD.setVisibility(View.GONE);
        }

        for(HashMap<String, String> map : list_answer){
            if(question.equals(map.get("question"))){
                for(int i  = 0; i < radioGroup.getChildCount(); i++){
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.getText().toString().equals(map.get("answer"))) {
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    private void saveAnswerTemp(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId != -1){
                    RadioButton radioButton = findViewById(checkedId);
                    String answer = radioButton.getText().toString();
                    saveDataTemp(tvQuestion.getText().toString(), answer);
                }
                listquestionAdapter.notifyDataSetChanged();
            }
        });
    }

    private void saveDataTemp(String question, String answer){
        boolean isQuestionAnswered = false;
        for (HashMap<String, String> map : list_answer) {
            String savedQuestion = map.get("question");
            if (question.equals(savedQuestion)) {
                map.put("answer", answer);
                isQuestionAnswered = true;
                break;
            }
        }

        if (!isQuestionAnswered) {
            HashMap<String, String> data = new HashMap<>();
            data.put("question", question);
            data.put("answer", answer);
            list_answer.add(data);
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
