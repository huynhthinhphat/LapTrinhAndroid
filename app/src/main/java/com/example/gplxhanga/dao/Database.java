package com.example.gplxhanga.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String dtbname = "GPLXA";
    private static final int version = 1;
    private  static final String historyLearn = "HistoryLearn";
    private  static final String historyTest = "HistoryTest";

    public Database(@Nullable Context context ) {
        super(context, dtbname, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createLearn = "CREATE TABLE "+ historyLearn +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Id_Question INTEGER,"+
                "Id_Answer_Select TEXT,"+
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



}