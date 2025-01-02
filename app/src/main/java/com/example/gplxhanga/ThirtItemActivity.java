package com.example.gplxhanga;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gplxhanga.api.ApiService;
import com.example.gplxhanga.dao.Database;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
    private List<ItemQuestion> list_question = new ArrayList<>();
    private TextView tvQuestion;
    private RadioGroup radioGroup;
    private RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private ImageButton btnPrev, btnNext;
    private Button btnSubmit;
    private int ques_number = 0;
    private List<HashMap<String, String>> list_answer = new ArrayList<>();
    private TextView questionCurrent;
    private int score = 0;
    private int cauLiet = 0;
    private int isPass = 3;

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
    }

    private void getItent(){
        Intent intent = getIntent();
        topic = intent.getIntExtra("topic", -1);
    }

    public void init(){
        toolbar = findViewById(R.id.toolbarexam);
        setSupportActionBar(toolbar);

        toolbarbottom = findViewById(R.id.toolbarfirstitem);
        setSupportActionBar(toolbarbottom);

        time = findViewById(R.id.toolbar_time);
        btnBack = findViewById(R.id.btn_back_exam);

        tvQuestion = findViewById(R.id.question);
        radioGroup = findViewById(R.id.groupAnswer);
        radioButtonA = findViewById(R.id.answerA);
        radioButtonB = findViewById(R.id.answerB);
        radioButtonC = findViewById(R.id.answerC);
        radioButtonD = findViewById(R.id.answerD);

        btnPrev = findViewById(R.id.btn_chervon_left_first);
        btnNext = findViewById(R.id.btn_chervon_right_first);
        btnSubmit = findViewById(R.id.btn_submit);

        questionCurrent = findViewById(R.id.socauhoi);
    }

    private void startCountdown() {
        // Thời gian bắt đầu (19 phút, chuyển sang milliseconds)
        long startTime = 19 * 60 * 1000;  // 19 phút = 1140 giây = 1140000 milliseconds

        // Tạo một CountDownTimer
        new CountDownTimer(startTime, 1000) { // Mỗi 1 giây sẽ gọi onTick()

            @Override
            public void onTick(long millisUntilFinished) {
                // Cập nhật thời gian còn lại vào TextView
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeft = String.format("%02d:%02d", minutes, seconds);
                time.setText(timeLeft);
            }

            @Override
            public void onFinish() {
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
        }.start(); // Bắt đầu đếm ngược
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
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
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

                        Intent intent = new Intent(ThirtItemActivity.this, ThirtActivity.class);
                        startActivity(intent);
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
}
