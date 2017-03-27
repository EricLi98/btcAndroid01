package com.example.eric.hello1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FocusActivity extends AppCompatActivity {
    EditText etEmail;
    TextView tvFocus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_focus);
        etEmail=(EditText) findViewById(R.id.editTextEmail);
        tvFocus=(TextView)findViewById(R.id.textViewFocus);
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public  void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){
                    String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
                    if(!etEmail.getText().toString().matches(regex)){
                        tvFocus.setText("您输入的邮箱不合法");
                    }else {
                        tvFocus.setText("");
                    }
                }
            }
        });
    }
}
