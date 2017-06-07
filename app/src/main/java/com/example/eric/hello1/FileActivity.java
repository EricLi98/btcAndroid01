package com.example.eric.hello1;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    private CheckBox appendBox;
    private String FILE_NAME = "fileDemo.txt";
    private EditText entryText;
    private TextView labelView;
    private TextView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_file);
        appendBox = (CheckBox) findViewById(R.id.checkBoxFile);
        Button bt = (Button) findViewById(R.id.buttonFileWrite);
        entryText = (EditText) findViewById(R.id.editTextFile);
        labelView = (TextView) findViewById(R.id.textViewFile);
        displayView = (TextView) findViewById(R.id.textViewFile1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                try {
                    if (appendBox.isChecked()) {
                        fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                    } else {
                        fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    }
                    String text = entryText.getText().toString();
                    fos.write(text.getBytes());
                    labelView.setText("文件写入成功，写入长度：" + text.length());
                    entryText.setText("");
                } catch (FileNotFoundException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        bt = (Button) findViewById(R.id.buttonFileRead);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView.setText("");
                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILE_NAME);
                    if (fis.available() == 0) {
                        return;
                    }
                    byte[] readBytes = new byte[fis.available()];
                    while (fis.read(readBytes) != -1) {
                    }
                    String text = new String(readBytes);
                    displayView.setText(text);
                    labelView.setText("文件读取成功，文件长度：" + text.length());
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        /*
        bt = (Button) findViewById(R.id.buttonFileWriteSd);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "SdcardFile-" + System.currentTimeMillis() + ".txt";
                File dir = new File(Environment.getExternalStorageDirectory(),fileName);
                if (dir.exists() && dir.canWrite()) {
                    File newFile = new File(dir.getAbsolutePath() + "/" + fileName);
                    FileOutputStream fos = null;
                    try {
                        newFile.createNewFile();
                        if (newFile.exists() && newFile.canWrite()) {
                            fos = new FileOutputStream(newFile);
                            fos.write(entryText.getText().toString().getBytes());
                            labelView.setText(fileName + "文件写入SD卡");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fos != null) {
                            try {
                                fos.flush();
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.amenu, menu);
        return true;
    }
    public void writeSd(View v){
        String fileName = "SdcardFile-" + System.currentTimeMillis() + ".txt";
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        FileOutputStream fos = null;
        try {
            file.createNewFile();

            if (file.exists() && file.canWrite()) {
                fos = new FileOutputStream(file);
                fos.write(entryText.getText().toString().getBytes());
                labelView.setText(fileName + "文件写入SD卡");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "click menu", Toast.LENGTH_SHORT).show();
                return true;

        }
        return  false;
    }

}
