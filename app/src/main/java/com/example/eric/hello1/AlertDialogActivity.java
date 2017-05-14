package com.example.eric.hello1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements AlertDialog.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alertdialog);
        Button button=(Button)findViewById(R.id.buttonAlertDialog1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab=new AlertDialog.Builder(AlertDialogActivity.this);
                ab.setTitle("title");
                ab.setIcon(R.drawable.stat_sample);
                ab.setMessage("message");
                ab.setPositiveButton("确定",AlertDialogActivity.this);
                ab.setNegativeButton("取消",AlertDialogActivity.this);

                ab.show();
            }
        });

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==AlertDialog.BUTTON_POSITIVE){
            Toast.makeText(this,"click positive",Toast.LENGTH_SHORT).show();
        }else if(which==AlertDialog.BUTTON_NEGATIVE){
            Toast.makeText(this,"click negtive",Toast.LENGTH_SHORT).show();
        }
    }
}
