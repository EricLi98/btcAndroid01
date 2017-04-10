package com.example.eric.hello1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Eric on 2017/3/29.
 */

public class StudentService1 {
    private StudDbHelpler studDbHelpler;
    StudentService1(Context context){
            studDbHelpler=new StudDbHelpler(context);
    }
    public void save(Student student){
        SQLiteDatabase db=studDbHelpler.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("xm",student.getName());
        newValues.put("height",student.getHeight());
        newValues.put("age",student.getAge());
        db.insert("student", null, newValues);
        db.close();
    }
    public Student find(String id){
        Student st=null;
        SQLiteDatabase db=studDbHelpler.getReadableDatabase();//打开数据库

        Cursor cursor=db.rawQuery("select * from student where id=?",new String[]{id});//执行SQL
        if(cursor.moveToNext()){//是否查询到记录
            int age=cursor.getInt(cursor.getColumnIndex("age"));//以下为得到数据，getColumnIndex返回的是列的索引
            int height=cursor.getInt(cursor.getColumnIndex("height"));
            int sId=cursor.getInt(cursor.getColumnIndex("id"));
            String xm=cursor.getString(cursor.getColumnIndex("xm"));

            st=new Student();
            st.setId(sId);
            st.setHeight(height);
            st.setAge(age);
            st.setName(xm);
        }
        db.close();
        return st;
    }
    public void del(String id){
        SQLiteDatabase db=studDbHelpler.getWritableDatabase();
        db.delete("student","id=?",new String[]{id});
        db.close();
    }
    public void update(Student student){
        SQLiteDatabase db=studDbHelpler.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("xm",student.getName());
        newValues.put("height",student.getHeight());
        newValues.put("age",student.getAge());

        db.update("student",  newValues,"id=?",new String[]{student.getId()+""});

        db.close();
    }
}
