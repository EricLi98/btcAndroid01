package com.example.eric.hello1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchActivity extends AppCompatActivity implements View.OnTouchListener{
    TextView tv ;
    LinearLayout ll1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_touch);
        tv=(TextView) findViewById(R.id.textViewTouch);
        ll1=(LinearLayout)findViewById(R.id.ll1);
        ll1.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        tv.setText("x:"+x+",y:"+y);
        return  true;
    }
}
