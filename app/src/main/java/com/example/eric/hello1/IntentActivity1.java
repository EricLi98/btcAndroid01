package com.example.eric.hello1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentActivity1 extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_intent1);
        editText=(EditText)findViewById(R.id.editTextIntent1);
        Button bt=(Button)findViewById(R.id.buttonIntentOk);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriString = editText.getText().toString();
                Uri data = Uri.parse(uriString);
                Intent result = new Intent(null, data);
                setResult(RESULT_OK, result);
                finish();

            }
        });
        bt=(Button)findViewById(R.id.buttonIntentCancel);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setResult(RESULT_CANCELED, null);
                finish();

            }
        });
    }
}
