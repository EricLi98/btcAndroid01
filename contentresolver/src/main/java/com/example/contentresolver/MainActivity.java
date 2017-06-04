package com.example.contentresolver;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String AUTHORITY="com.inspur.student1";//"com.inspur.edu";
    public static final String PATH_MULTIPLE = "student";

    public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
    public static final Uri  CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    private EditText xm;
    private EditText age;
    private EditText height;
    private EditText id;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList arrayList;
    private ContentResolver resolver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sqlite);

        resolver=this.getContentResolver();
        xm=(EditText) findViewById(R.id.editTextName);
        age=(EditText) findViewById(R.id.editTextAge);
        height=(EditText) findViewById(R.id.editTextHeight);
        id=(EditText) findViewById(R.id.editTextId);
        listView=(ListView)findViewById(R.id.listViewStud);
        arrayList=new ArrayList();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        Button bt=(Button)findViewById(R.id.buttonDispAll);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonInsert);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonClear);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonDelAll);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonDelId);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonQueryId);
        bt.setOnClickListener(this);
        bt=(Button)findViewById(R.id.buttonUpdateId);
        bt.setOnClickListener(this);

    }

    private void updateId(){
        Student student=new Student();
        student.setName(xm.getText().toString());
        student.setHeight(Integer.parseInt(height.getText().toString()));
        student.setAge(Integer.parseInt(age.getText().toString()));
        student.setId(Integer.parseInt(id.getText().toString()));

        StudService studService=new StudService(resolver);
        studService.update(student);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonDispAll:
                dispAll();
                break;
            case R.id.buttonInsert:
                insert();
                dispAll();
                break;
            case R.id.buttonClear:
                clear();
                break;
            case R.id.buttonDelAll:
                delAll();
                dispAll();
                break;
            case R.id.buttonDelId:
                delId();
                dispAll();
                break;
            case R.id.buttonQueryId:
                queryId();
                break;
            case R.id.buttonUpdateId:
                updateId();
                dispAll();
                break;
        }

    }



    private void queryId(){
        StudService studService=new StudService(resolver);
        ArrayList al=studService.query(Integer.parseInt(id.getText().toString())+"");
        arrayList.clear();

        arrayList.addAll(al);
        arrayAdapter.notifyDataSetChanged();
    }

    private void delId(){
        StudService studService=new StudService(resolver);
        studService.delete(id.getText().toString());
    }
    private void delAll(){
        StudService studService=new StudService(resolver);
        studService.delete(null);

    }
    private void clear(){
        arrayList.clear();
        arrayAdapter.notifyDataSetChanged();
    }


    private void dispAll(){
        StudService studService=new StudService(resolver);
        ArrayList al=studService.query(null);
        arrayList.clear();
        arrayList.addAll(al);
        arrayAdapter.notifyDataSetChanged();
    }
    private void insert(){


        Student student=new Student();
        student.setName(xm.getText().toString());
        student.setHeight(Integer.parseInt(height.getText().toString()));
        student.setAge(Integer.parseInt(age.getText().toString()));
        StudService studService=new StudService(resolver);
        studService.save(student);

    }
}
