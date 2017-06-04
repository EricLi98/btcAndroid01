package com.example.contentresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Eric on 2017/2/18.
 */

public class StudService {
    private ContentResolver resolver;
    StudService(ContentResolver resolver){
        this.resolver=resolver;
    }
    public  void save(Student stud) {
        ContentValues values = new ContentValues();

        values.put("xm", stud.getName());
        values.put("age", stud.getAge());
        values.put("height", stud.getHeight());

        Uri newUri = resolver.insert(MainActivity.CONTENT_URI, values);
    }
    public void update(Student stud) {
        ContentValues cv = new ContentValues();
        cv.put("age", stud.getAge());
        cv.put("xm", stud.getName());
        cv.put("height", stud.getHeight());


        Uri uri = Uri.parse(MainActivity.CONTENT_URI_STRING + "/" + stud.getId().toString());
        int result = resolver.update(uri, cv, null, null);

    }
    public ArrayList query(String id) {
        ArrayList al=new ArrayList();
        String[] selColumns={"id","age","height","xm"};

        Uri uri = Uri.parse(MainActivity.CONTENT_URI_STRING );
        if(id!=null)
            uri = Uri.parse(MainActivity.CONTENT_URI_STRING + "/" +id);


        Cursor cursor = resolver.query(uri,
               selColumns,
                null, null, null);

        while (cursor.moveToNext()){
            al.add(convert2Stud(cursor));
        }
        cursor.close();

        return al;
    }
    /*
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
*/
    public void delete(String id) {


        Uri uri = Uri.parse(MainActivity.CONTENT_URI_STRING );
        if(id!=null)
            uri = Uri.parse(MainActivity.CONTENT_URI_STRING + "/" +id);
        resolver.delete(uri,null,null);
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
