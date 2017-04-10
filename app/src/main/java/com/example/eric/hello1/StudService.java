package com.example.eric.hello1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Eric on 2017/2/18.
 */

public class StudService {
    private StudDbHelpler studDbHelpler;
    StudService(Context context){
        studDbHelpler=new StudDbHelpler(context);
    }
    public  void save(Student stud) {
        SQLiteDatabase db=this.studDbHelpler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("age", stud.getAge());
        cv.put("xm", stud.getName());
        cv.put("height", stud.getHeight());

        db.insert("student", null, cv);
        db.close();
    }
    public void update(Student stud) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db=this.studDbHelpler.getWritableDatabase();
        cv.put("age", stud.getAge());
        cv.put("xm", stud.getName());
        cv.put("height", stud.getHeight());

        String where="id=?";
        String[] args={stud.getId()+""};

        db.update("student", cv,where,args);
    }
    public Student find(Integer id){
        Student student=null;
        SQLiteDatabase db=this.studDbHelpler.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student where id=?",new String[]{id.toString()});
        if(cursor.moveToNext()){
            int age=cursor.getInt(cursor.getColumnIndex("age"));
            int height=cursor.getInt(cursor.getColumnIndex("height"));
            String xm=cursor.getString(cursor.getColumnIndex("xm"));
            student=new Student();
            student.setAge(age);
            student.setId(id);
            student.setHeight(height);
            student.setName(xm);
        }
        cursor.close();
        db.close();
        return student;
    }
    public  ArrayList query(String id) {
        ArrayList al=new ArrayList();
        SQLiteDatabase db=this.studDbHelpler.getReadableDatabase();
        String[] selColumns={"id","age","height","xm"};
        String where="1=1 ";
        ArrayList<String> args=new ArrayList();
        if(id!=null){
            where+=" and id=?";
            args.add(id);
        }
        String sortOrder="height DESC";
        String[] c=args.toArray(new String[] {});
        Cursor cursor=db.query("student",selColumns,where,c,null,null,sortOrder);
        while (cursor.moveToNext()){
            al.add(convert2Stud(cursor));
        }
        cursor.close();
        db.close();
        return al;
    }
    public void delete(String id) {
        ArrayList al=new ArrayList();
        SQLiteDatabase db=this.studDbHelpler.getWritableDatabase();
        String where="1=1 ";
        ArrayList<String> args=new ArrayList();
        if(id!=null){
            where+=" and id=?";
            args.add(id);
        }

        String[] c=args.toArray(new String[] {});
        db.delete("student",where,c);
        db.close();
    }
    private static Student convert2Stud(Cursor cursor){
        Student student=new Student();
        student.setId(cursor.getInt(cursor.getColumnIndex("id")));
        student.setName(cursor.getString(cursor.getColumnIndex("xm")));
        student.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
        student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
        return student;
    }
}
