package com.example.mecia.ric;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LawyerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE Clientes (ID INTEGER PRIMARY KEY AUTOINCREMENT, Numero TEXT NOT NULL, Fase TEXT NOT NULL, Obs TEXT NOT NULL);";

    public LawyerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
