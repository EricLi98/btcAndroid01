package com.example.eric.hello1;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ListViewPracActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<HashMap<String,?>> list1;
    private ArrayList catList;
    private Spinner spinner;
    private EditText editText;
    private ListView listView;
    private SimpleAdapter sa;
    private ImageButton imageButton;
    private String selCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_prac);
        Resources res=this.getResources();

        String[] prod=res.getStringArray(R.array.prodArr);
        String[] category=res.getStringArray(R.array.catArr);
        TypedArray imgs = res.obtainTypedArray(R.array.prodImg);


        list1=new ArrayList<HashMap<String,?>>();

        HashSet set=new HashSet<String>();
        for(int i=0;i<prod.length;i++){
            HashMap hm=new HashMap();
            hm.put("prod",prod[i]);
            hm.put("cat",category[i]);
            hm.put("img",imgs.getResourceId(i,0));
            list1.add(hm);
            if(!set.contains(category[i])){
                set.add(category[i]);
            }
        }

        this.catList=new ArrayList(Arrays.asList(set.toArray()));

        spinner=(Spinner) findViewById(R.id.spinnerListViewPrac);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,this.catList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.listViewPrac);
        sa=new SimpleAdapter(this,list1,R.layout.layout_listview_proc_row,new String[]{"prod","cat","img"},new int[]{R.id.textViewListviewProdRow1,R.id.textViewListviewProdRow2,R.id.imageViewListViewRowProc});
        listView.setAdapter(sa);

        editText=(EditText)findViewById(R.id.editTextListPrac);

        imageButton=(ImageButton)findViewById(R.id.imageButtonListViewPrac);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewPracActivity.this.filterList(ListViewPracActivity.this.selCategory,ListViewPracActivity.this.editText.getText().toString());
                ListViewPracActivity.this.sa.notifyDataSetChanged();
            }
        });

    }
    void filterList(String pCate,String pProd){
        Resources res=this.getResources();
        String[] prod=res.getStringArray(R.array.prodArr);
        String[] category=res.getStringArray(R.array.catArr);
        TypedArray imgs = res.obtainTypedArray(R.array.prodImg);
        list1.clear();

        for(int i=0;i<prod.length;i++){
            HashMap hm=new HashMap();
            if(pCate!=null&&pCate.length()>0&&!category[i].equals(pCate)){
                continue;
            }
            if(pProd!=null&&pProd.length()>0&&prod[i].indexOf(pProd)<0){
                continue;
            }
            hm.put("prod",prod[i]);
            hm.put("cat",category[i]);
            hm.put("img",imgs.getResourceId(i,0));
            list1.add(hm);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selCategory=(String)this.catList.get(position);
        this.filterList(selCategory,this.editText.getText().toString());
        this.sa.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
