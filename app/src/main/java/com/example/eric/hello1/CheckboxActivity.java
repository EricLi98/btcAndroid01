package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckboxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private ArrayList myBoxes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkbox);
        LinearLayout ll=(LinearLayout) this.findViewById(R.id.llCheckbox);
        int c=ll.getChildCount();
        myBoxes=new ArrayList();
        for(int i=0;i<c;i++){
            View v=ll.getChildAt(i);

            if(v instanceof CheckBox){
                myBoxes.add(v);
                ((CheckBox) v).setOnCheckedChangeListener(this);


            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String rst="you select:"+this.getString(R.string.app_name);
        for(int i=0;i<this.myBoxes.size();i++){
            CheckBox cb=(CheckBox) this.myBoxes.get(i);
            if(cb.isChecked()){
                rst+=cb.getText()+";";
            }

        }
        Toast.makeText(this,rst,Toast.LENGTH_SHORT).show();
    }
}
