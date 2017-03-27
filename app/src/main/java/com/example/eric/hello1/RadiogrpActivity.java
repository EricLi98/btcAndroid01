package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadiogrpActivity extends AppCompatActivity {
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_radiogrp);
        rg=(RadioGroup) findViewById(R.id.radiogrp1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                if(rb.getTag()!=null&&rb.getTag().equals("1")) {
                    Toast.makeText(RadiogrpActivity.this, "YOU ARE RIGHT", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RadiogrpActivity.this, "YOU ARE WRONG", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
