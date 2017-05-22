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

    protected void onCreate1(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_frame1);
        setContentView(R.layout.layout_frame2);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.img_name);//设置资源
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置缩放
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));//设置layout_width,layout_height
        //实例化TextView
        TextView textView1 = new TextView(this);
        textView1.setText("test");
        textView1.setTextSize(30);//字号
        textView1.setGravity(Gravity.CENTER);//显示位置
        textView1.setTextColor(Color.parseColor("#f3f3f3"));//颜色
        textView1.setTypeface(null, Typeface.BOLD);//加粗
        FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        textView1.setLayoutParams(lp1);//设置layout_width,layout_height
        //实例化布局
        FrameLayout framelayout = new FrameLayout(this);
        framelayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        //添加子元素
        framelayout.addView(imageView);
        framelayout.addView(textView1);
        //设置布局
        setContentView(framelayout);
    }



}
