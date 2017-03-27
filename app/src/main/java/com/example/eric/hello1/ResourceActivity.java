package com.example.eric.hello1;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class ResourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resource);
        Button bt=(Button) findViewById(R.id.buttonResource1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fmtString=ResourceActivity.this.getString(R.string.fmtstring);
                String str=String.format(fmtString,"p1","p2");
                Toast.makeText(ResourceActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
        bt=(Button) findViewById(R.id.buttonResource2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res=ResourceActivity.this.getResources();
                int[] a=res.getIntArray(R.array.intArr);

                for(int i=0;i<a.length;i++){
                    Log.d(ResourceActivity.class.toString(),a[i]+"");
                }

                String[] s=res.getStringArray(R.array.strArr);
                for(int i=0;i<s.length;i++){
                    Log.d(ResourceActivity.class.toString(),s[i]+"");
                }

                TypedArray colors = res.obtainTypedArray(R.array.colors);

                for(int i=0;i<colors.length();i++){
                    Log.d(ResourceActivity.class.toString(),colors.getColor(i,0)+"");
                }

            }
        });

        bt=(Button) findViewById(R.id.buttonResource3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList a=parseXml();
                }catch (Exception e){

                }
            }
        });
    }
    ArrayList<String> parseXml() throws Exception{
        ArrayList<String> items=new ArrayList<String>();
        XmlPullParser xpp=getResources().getXml(R.xml.words);

        while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
            if (xpp.getEventType()==XmlPullParser.START_TAG) {
                if (xpp.getName().equals("word")) {
                    Log.d(ResourceActivity.class.toString(),xpp.getAttributeValue(0));
                    items.add(xpp.getAttributeValue(0));
                }
            }

            xpp.next();
        }
        return  items;
    }
}
