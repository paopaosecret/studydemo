package com.xbing.com.viewdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhaobing on 2016/8/29.
 */
public class MySqliteDBHelper extends SQLiteOpenHelper{

    public static final String DBNAME="radiomap";
    public static final int VERSION=1;
    public MySqliteDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id long PRIMARY KEY,name varchar(20),gender varchar(3),mobile varchar(11))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getDatabase(){
        return this.getWritableDatabase();
    }

}
