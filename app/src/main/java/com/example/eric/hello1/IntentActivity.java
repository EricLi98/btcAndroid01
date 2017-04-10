package com.example.eric.hello1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_intent);
        Button bt=(Button)findViewById(R.id.buttonIntent1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntentActivity.this,IntentActivity1.class);
                IntentActivity.this.startActivityForResult(intent,1);
            }
        });

        bt=(Button)findViewById(R.id.buttonIntent3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntentActivity.this,CheckboxActivity.class);
                startActivity(intent);
            }
        });

        bt=(Button)findViewById(R.id.buttonIntent4);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("schemodemo://com.inspur/path"));
                startActivity(intent);
            }
        });

        bt=(Button)findViewById(R.id.buttonIntent5);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.inspur.receiver");
                intent.putExtra("message","broadcast message");
                sendBroadcast(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,data.getData().toString(),Toast.LENGTH_SHORT).show();
            }
            if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"canceled",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
