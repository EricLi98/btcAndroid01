package com.example.eric.hello1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class KeyeventActivity extends AppCompatActivity {
    EditText et1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_keyevent);
        super.onCreate(savedInstanceState);
        et1=(EditText) findViewById(R.id.editTextKeyevent);
        tv1=(TextView)findViewById(R.id.textViewKey) ;
        et1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tv1.setText("you input:"+et1.getText().toString());
                return false;
            }
        });
    }
}
