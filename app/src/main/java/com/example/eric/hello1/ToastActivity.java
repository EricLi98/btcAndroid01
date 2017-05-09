package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toast);
        bt=(Button) findViewById(R.id.buttonToast1);
        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick2(View v) {
               Student student=new Student();
                student.setAge(20);
                student.setHeight(177);
                student.setName("xm");
                StudentService1 studentService1=new StudentService1(ToastActivity.this);
                studentService1.save(student);
            }
            public void onClick(View v) {
                Toast tt=new Toast(ToastActivity.this.getApplicationContext());
                LayoutInflater inflater = getLayoutInflater();
                //ViewGroup vg=(ViewGroup) findViewById(R.id.toast_layout_root);
                View layout = inflater.inflate(R.layout.layout_toast_cust,null   );

                ImageView image = (ImageView) layout.findViewById(R.id.imageToastCust);
                image.setImageResource(R.drawable.a);
                TextView text = (TextView) layout.findViewById(R.id.textViewToastCust);
                text.setText("Hello! This is a custom toast!");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
        bt=(Button)findViewById(R.id.buttonToastQuery);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentService1 studentService1=new StudentService1(ToastActivity.this);
                Student student=studentService1.find("1");
                if(student==null){
                    Toast.makeText(ToastActivity.this,"no data",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ToastActivity.this,student.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt=(Button)findViewById(R.id.buttonToastDel);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentService1 studentService1=new StudentService1(ToastActivity.this);
                studentService1.del("4");

                Toast.makeText(ToastActivity.this,"del",Toast.LENGTH_SHORT).show();

            }
        });

        bt=(Button)findViewById(R.id.buttonToastUpdate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student();
                student.setAge(21);
                student.setId(5);
                student.setHeight(178);
                student.setName("tom");
                StudentService1 studentService1=new StudentService1(ToastActivity.this);
                studentService1.update(student);

                Toast.makeText(ToastActivity.this,"update",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
