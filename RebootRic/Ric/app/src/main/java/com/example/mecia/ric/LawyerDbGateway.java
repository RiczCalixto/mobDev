package com.example.mecia.ric;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LawyerDbGateway {

    private static LawyerDbGateway gw;
    private SQLiteDatabase db;

    private LawyerDbGateway(Context ctx){
        LawyerDbHelper helper = new LawyerDbHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public static LawyerDbGateway getInstance(Context ctx) {
        if (gw == null)
            gw = new LawyerDbGateway(ctx);
            return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}


