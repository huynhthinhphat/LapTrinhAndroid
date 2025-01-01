package com.example.gplxhanga.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gplxhanga.entities.HistoryLearn;
import com.example.gplxhanga.entities.ItemQuestion;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String dtbname = "GPLXA";
    private static final int version = 2;
    private  static final String historyLearn = "HistoryLearn";
    private  static final String historyTest = "HistoryTest";

    public Database(@Nullable Context context ) {
        super(context, dtbname, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createLearn = "CREATE TABLE " + historyLearn +
                "(Id_Question INTEGER PRIMARY KEY," +
                "Answer_Select TEXT," +  // Cột Answer_Select cần có
                "Typequestion TEXT)";

        String createTest = "CREATE TABLE "+ historyTest +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Topic INTEGER,"+
                "Score INTEGER,"+
                "TimeHistory INTEGER)";
        sqLiteDatabase.execSQL(createLearn);
        sqLiteDatabase.execSQL(createTest);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + historyLearn);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + historyTest);
        onCreate(sqLiteDatabase);

    }

    public void addLearn(ItemQuestion item, String selected){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id_Question", item.getId());
        values.put("Answer_Select", selected);
        values.put("Typequestion", item.getQuestionType());

        db.replace(historyLearn,null,values);
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

            // Thêm đối tượng HistoryLearn vào danh sách
            HistoryLearn htr = new HistoryLearn(idQuestion, answerSelect, typeQuestion);
            list.add(htr);
        }
        cursor.close();
        db.close();
        return list;
    }
   /* public List<HistoryLearn> getLearn(String typequestion){
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryLearn> list = new ArrayList<>();

        // Truy vấn lấy tất cả các cột (Id_Question, Answer_Select, Typequestion)
        Cursor cursor = db.rawQuery("SELECT Id_Question, Answer_Select, Typequestion FROM " + historyLearn + " WHERE Typequestion = ?", new String[]{typequestion});

        if (cursor != null && cursor.moveToFirst()) {
            int idQuestionColIndex = cursor.getColumnIndex("Id_Question");
            int answerSelectColIndex = cursor.getColumnIndex("Answer_Select");
            int typeQuestionColIndex = cursor.getColumnIndex("Typequestion");
            Log.e("columnne", String.valueOf(idQuestionColIndex));
            Log.e("columnne", String.valueOf(answerSelectColIndex));
            Log.e("columnne", String.valueOf(typeQuestionColIndex));

            if (idQuestionColIndex == -1 || answerSelectColIndex == -1 || typeQuestionColIndex == -1) {
                Log.e("Database", "One or more columns not found.");
            } else {
                do {
                    int idQuestion = cursor.getInt(idQuestionColIndex);
                    String answerSelect = cursor.getString(answerSelectColIndex);
                    String typeQuestion = cursor.getString(typeQuestionColIndex);

                    HistoryLearn htr = new HistoryLearn(idQuestion, answerSelect, typeQuestion);
                    list.add(htr);
                } while (cursor.moveToNext());
            }
        } else {
            Log.e("Database", "Cursor is empty or not properly initialized.");
        }

        cursor.close();
        db.close();

        return list;
    }*/



}