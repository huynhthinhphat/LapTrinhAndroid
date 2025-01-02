package com.example.gplxhanga.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gplxhanga.entities.HistoryLearn;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String dtbname = "GPLXA";
    private static final int version = 20;
    private  static final String historyLearn = "HistoryLearn";
    private  static final String historyTest = "HistoryTest";
    private static final String questionIncorrect = "QuestionInCorrect";
    private static final String topictb = "TopcScore";

    public Database(@Nullable Context context ) {
        super(context, dtbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createLearn = "CREATE TABLE IF NOT EXISTS " + historyLearn +
                "(Id_Question INTEGER PRIMARY KEY," +
                "Answer_Select TEXT," +  // Cột Answer_Select cần có
                "Typequestion TEXT)";

        String createTest = "CREATE TABLE IF NOT EXISTS "+ historyTest +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Topic INTEGER,"+
                "Question TEXT,"+
                "Answer TEXT,"+
                "TimeHistory INTEGER)";

        String createTopic = "CREATE TABLE IF NOT EXISTS "+ topictb +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Topic INTEGER,"+
                "CauLiet INTEGER,"+
                "isPass INTEGER,"+
                "Score INTEGER)";

        String createQuestionIncorrect = "CREATE TABLE IF NOT EXISTS "+ questionIncorrect +
                "(Id_Question INTEGER PRIMARY KEY," +
                "Answer_Select TEXT," +
                "Typequestion TEXT)";

        sqLiteDatabase.execSQL(createLearn);
        sqLiteDatabase.execSQL(createTest);
        sqLiteDatabase.execSQL(createTopic);
        sqLiteDatabase.execSQL(createQuestionIncorrect);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + historyLearn);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + historyTest);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + questionIncorrect);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + topictb);
            onCreate(sqLiteDatabase);
        }
    }

    public void addTopicExam(List<HashMap<String, String>> list, int topic){
        SQLiteDatabase db = getWritableDatabase();

        for(HashMap<String, String> map: list){
            ContentValues value = new ContentValues();
            value.put("Topic", topic);
            value.put("Question", map.get("question"));
            value.put("Answer", map.get("answer"));

            long newRowId = db.insert(historyTest, null, value);

            if (newRowId == -1) {
                Log.e("DB", "Lỗi thêm csdl");
            } else {
                Log.d("DB", "Thêm thành công id: " + newRowId);
            }
        }
        db.close();
    }

    public void addTopicTB(int score, int topic, int cauLiet, int isPass){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("Topic", topic);
        value.put("Score", score);
        value.put("CauLiet", cauLiet);
        value.put("isPass", isPass);

        Log.d("msg", topic +" " + score +" " + cauLiet +" " + isPass);
        long newRowId = db.insert(topictb, null, value);

        if (newRowId == -1) {
            Log.e("DB", "Lỗi thêm csdl");
        } else {
            Log.d("DB", "Thêm thành công id: " + newRowId);
        }
        db.close();
    }

    public List<HashMap<String, Integer>> getTopicTB(int topic){
        SQLiteDatabase db = getWritableDatabase();
        List<HashMap<String, Integer>> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+topictb+ " WHERE Topic = ?", new String[]{String.valueOf(topic)});
        while (cursor.moveToNext()){
            HashMap<String, Integer> map = new HashMap<>();
            map.put("cauLiet", cursor.getInt(2));
            map.put("isPass", cursor.getInt(3));
            map.put("score", cursor.getInt(4));
            list.add(map);
        }

        db.close();
        return list;
    }

    public void addLearn(ItemQuestion item, String selected){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id_Question", item.getId());
        values.put("Answer_Select", selected);
        values.put("Typequestion", item.getQuestionType());

        db.replace(historyLearn, null, values);
        db.close();
    }

    public List<HistoryLearn> getLearn(String typequestion){
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryLearn> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+historyLearn+ " WHERE Typequestion = ?", new String[]{typequestion});
        while (cursor.moveToNext()){
            int idQuestion = cursor.getInt(0);
            String answerSelect = cursor.getString(1);
            String typeQuestion = cursor.getString(2);

            HistoryLearn htr = new HistoryLearn(idQuestion, answerSelect, typeQuestion);
            list.add(htr);
        }
        cursor.close();
        db.close();
        return list;
    }

    public Boolean deleteAllLearn(){
        SQLiteDatabase db = getReadableDatabase();
        int count = db.delete(historyLearn, null, null);
        db.close();
        return count >= 0;
    }

    public Boolean deleteAllExam(){
        SQLiteDatabase db = getReadableDatabase();
        int count = db.delete(topictb, null, null);
        db.close();
        return count >= 0;
    }
}
