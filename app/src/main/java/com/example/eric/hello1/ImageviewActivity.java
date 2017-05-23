package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageviewActivity extends AppCompatActivity {
    private ImageView iv;
    private static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imageview);

        iv=(ImageView)findViewById(R.id.imageview_img);
        //iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    public void click1(View v){
        if(i==0) {
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Toast.makeText(this,"CENTER_INSIDE",Toast.LENGTH_SHORT).show();
        }
        if(i==1) {
            iv.setScaleType(ImageView.ScaleType.CENTER);
            Toast.makeText(this,"CENTER",Toast.LENGTH_SHORT).show();
        }
        if(i==2) {
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Toast.makeText(this,"FIT_CENTER",Toast.LENGTH_SHORT).show();
        }
        if(i==3) {
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Toast.makeText(this,"FIT_XY",Toast.LENGTH_SHORT).show();
        }

        if(i==4) {
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Toast.makeText(this,"CENTER_CROP",Toast.LENGTH_SHORT).show();
        }
        i++;
        if(i==5){
            i=0;
        }
    }
}
