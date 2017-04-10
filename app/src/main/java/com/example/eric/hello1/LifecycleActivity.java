package com.example.eric.hello1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LifecycleActivity extends AppCompatActivity {
    private String TAG=LifecycleActivity.class.toString();
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"--onStart--");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG,"--onStop--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"--onRestart--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG,"--destory--");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"--onRestoreInstanceState--");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG,"--onPause--");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);

        Log.d(TAG,"--onSaveInstanceState--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"--onResume--");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG,"--onCreate--");

        Button bt=(Button)findViewById(R.id.buttonLifecycle);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                Intent intent=new Intent(LifecycleActivity.this, IntentActivity.class);
                startActivity(intent);

            }
        });
    }
}
