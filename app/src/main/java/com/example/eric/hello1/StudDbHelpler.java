package com.example.eric.hello1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 2017/2/18.
 */

public class StudDbHelpler extends SQLiteOpenHelper {
    public static final String SQL_CREATE_STUDINFO="create table student(id integer primary key  autoincrement,height integer,age integer,xm text)";

    StudDbHelpler(Context context){
        super(context,"stud6.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table student");
        onCreate(db);
    }
}
