package com.example.eric.hello1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewSimActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String,?>> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lstview);
        listView=(ListView)findViewById(R.id.listviewsimple);

        al=new ArrayList<HashMap<String,?>>();
        HashMap hm=new HashMap();
        hm.put("prod","prod1");
        hm.put("cate","cate1");

        al.add(hm);
        hm=new HashMap();
        hm.put("prod","prod2");
        hm.put("cate","cate2");
        al.add(hm);


        SimpleAdapter sa=new SimpleAdapter(this,al,R.layout.layout_lstview_row1,new String[]{"prod","cate"},new int[]{R.id.textView6,R.id.textView7});
        listView.setAdapter(sa);

    }
}
