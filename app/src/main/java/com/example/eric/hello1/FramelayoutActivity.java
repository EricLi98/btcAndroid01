package com.example.eric.hello1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FramelayoutActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_frame1);
        setContentView(R.layout.layout_frame2);
    }

    protected void onCreate2(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.img_name);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        TextView textView1 = new TextView(this);
        textView1.setText("test");
        textView1.setTextSize(30);
        textView1.setGravity(Gravity.CENTER);
        textView1.setTextColor(Color.parseColor("#f3f3f3"));
        textView1.setTypeface(null, Typeface.BOLD);
//        textView1.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
        FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        //lp1.setMargins(0,20,0,0);
        textView1.setLayoutParams(lp1);
//Initializing frame layout
        FrameLayout framelayout = new FrameLayout(this);
        framelayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
//Adding views to FrameLayout
        framelayout.addView(imageView);
        framelayout.addView(textView1);
        setContentView(framelayout);
    }

}
