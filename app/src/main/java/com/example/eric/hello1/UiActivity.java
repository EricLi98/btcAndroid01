package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UiActivity extends AppCompatActivity {

    private List<String> lst;
    private List<HashMap<String ,String>> list1;
    private ArrayAdapter<String> adapterLst;
    private Spinner spinner;

    String[] countryNames={"India","China","Australia","Portugle","America","New Zealand"};
    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.new_zealand};

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ui);
        ImageButton bt=(ImageButton)findViewById(R.id.imageButtonUi);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UiActivity.this,"imgbutton",Toast.LENGTH_LONG).show();
                UiActivity.this.lst.add("item4");
                UiActivity.this.adapterLst.notifyDataSetChanged();


            }
        });
        ToggleButton tg=(ToggleButton)findViewById(R.id.toggleButtonUi);
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(UiActivity.this,"turn on",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(UiActivity.this,"turn off",Toast.LENGTH_LONG).show();
                }
            }
        });
        Spinner sp=(Spinner)findViewById(R.id.spinnerUi);
        this.spinner=sp;
        lst=new ArrayList<String>();
        lst.add("item1");
        lst.add("item2");
        lst.add("item3");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(R.layout.dropdownlist);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(UiActivity.this,"you have sel:"+lst.get(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

    });
        Spinner spin = (Spinner) findViewById(R.id.spinnerUiCustAdaptor);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),flags,countryNames);
        spin.setAdapter(customAdapter);


        ListView lv=(ListView)findViewById(R.id.listViewUi);
        adapterLst=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lst);
        lv.setAdapter(adapterLst);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UiActivity.this,"you have sel:"+lst.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////////////
        ListView lv1=(ListView)findViewById(R.id.ListViewUi1);
        list1=new ArrayList<HashMap<String,String>>();

        HashMap hm=new HashMap();
        hm.put("title","title1");
        hm.put("desc","desc1");
        list1.add(hm);
        hm=new HashMap();
        hm.put("title","title2");
        hm.put("desc","desc2");
        list1.add(hm);
        hm=new HashMap();
        hm.put("title","title3");
        hm.put("desc","desc3");
        list1.add(hm);

        SimpleAdapter sa=new SimpleAdapter(this,list1,R.layout.layout_listview_row,new String[]{"title","desc"},new int[]{R.id.textViewUiLv1,R.id.textViewUiLv2});
        lv1.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UiActivity.this,"you have sel:"+list1.get(position).get("title"),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
