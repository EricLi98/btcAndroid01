package com.example.eric.hello1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText etRst;
    Button buttonCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        et1=(EditText) findViewById(R.id.editTextOper1);
        et2=(EditText)findViewById(R.id.editTextOper2);
        etRst=(EditText)findViewById(R.id.editTextRst);
        buttonCal=(Button) findViewById(R.id.buttonAdd);

        buttonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int op1=Integer.parseInt(et1.getText().toString());
                int op2=Integer.parseInt(et2.getText().toString());
                int rst=op1*op2;
                etRst.setText(""+rst);
            }
        });
    }
}
