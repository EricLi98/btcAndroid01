package com.example.eric.hello1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lis1Activity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lis1);
        bt1=(Button) findViewById(R.id.buttonLisA);
        bt2=(Button) findViewById(R.id.buttonLisB);
        tv1=(TextView)findViewById(R.id.textViewLis1A);
        bt2.setOnClickListener(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText("button click");
            }
        });
    }
    public void onBtn3(View v) {
        tv1.setText("button3 click");
    }
    @Override
    public void onClick(View v) {
        tv1.setText("button2 click");
    }
}
