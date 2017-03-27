package com.example.eric.hello1;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText tel;
    private Button bt1;
    private Button btInnerClass;
    private TextView tv1;
    private ImageView iv1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv1=(ImageView)findViewById(R.id.imageView1);
        iv1.setImageResource(R.drawable.a);
        tv1.setBackgroundColor(getResources().getColor(android.R.color.black));
        iv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {


                    myWallpaperManager.setResource( R.drawable.a);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
    protected void dispXml(View v){
        String str=tel.getText().toString();
        tv1.setText("用户输入的是："+str);


    }
    protected void onCreate1(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tel=(EditText) this.findViewById(R.id.editTextTel);
        bt1=(Button)this.findViewById(R.id.buttonDisp);
        tv1=(TextView)this.findViewById(R.id.textViewDisp);
        btInnerClass=(Button)this.findViewById(R.id.buttonDispInnerClass);
        DispListener dl=new DispListener();
        btInnerClass.setOnClickListener(dl);


        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str=tel.getText().toString();
                tv1.setText("用户输入的是："+str);

                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setResource(R.drawable.a);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }
    public class DispListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String str=tel.getText().toString();
            tv1.setText("用户输入的是："+str);
        }
    }
}
