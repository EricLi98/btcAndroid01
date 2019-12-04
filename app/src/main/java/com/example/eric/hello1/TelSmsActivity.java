package com.example.eric.hello1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelSmsActivity extends AppCompatActivity {
    private String callText;
    private String smsContent;
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MainActivity.class.toString(),"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MainActivity.class.toString(),"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.class.toString(),"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MainActivity.class.toString(),"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.class.toString(),"onResume");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_telsms);
        Button bt=(Button)this.findViewById(R.id.setOri);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelSmsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            }
        });
        bt=(Button)this.findViewById(R.id.setOriPor);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelSmsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }
        });
        bt=(Button)this.findViewById(R.id.call);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView telText=(TextView) TelSmsActivity.this.findViewById(R.id.telText);
                TelSmsActivity.this.callPhone(telText.getText().toString());
            }
        });
        bt=(Button)this.findViewById(R.id.call6);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView telText=(TextView) TelSmsActivity.this.findViewById(R.id.telText);
                TelSmsActivity.this.callText=telText.getText().toString();
                if (ActivityCompat.checkSelfPermission(TelSmsActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TelSmsActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1000);
                }else {
                    TelSmsActivity.this.callPhone(telText.getText().toString());
                }
            }
        });
        bt=(Button)this.findViewById(R.id.smsSend);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView telText=(TextView) TelSmsActivity.this.findViewById(R.id.telText);
                TextView smsText=(TextView) TelSmsActivity.this.findViewById(R.id.smsText);
                callText=telText.getText().toString();
                smsContent=smsText.getText().toString();
                //TelSmsActivity.this.sendSms(telText.getText().toString(),smsText.getText().toString());
                if (ActivityCompat.checkSelfPermission(TelSmsActivity.this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TelSmsActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            1001);
                }else {
                    TelSmsActivity.this.sendSms(callText,smsContent);
                }

            }
        });

        bt=(Button)this.findViewById(R.id.telSmsCamera);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(TelSmsActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TelSmsActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            1003);
                }else {
                    showCamera();
                }
            }
        });

    }
    private void showCamera(){
        Intent intent=new Intent(this,CameraActivity.class);
        startActivity(intent);
    }
    private void callPhone(String tel){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel));
        startActivity(intent);
    }
    private void sendSmsBak(String tel,String content){
        Intent intent=new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:"+tel));
        //intent.putExtra("address",tel);
        intent.putExtra("sms_body",content);
        intent.setType("vnd.android-dir/mms-sms");

        startActivity(intent);
    }
    public void sendSms(String edt_phoneNo,String sms)
    {
        try {

            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(edt_phoneNo, null, sms, null, null);


            //startActivity(smsSIntent);
        } catch (Exception ex) {
            Toast.makeText(TelSmsActivity.this, "Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                this.callPhone(this.callText);
            }
        }
        if(requestCode==1001){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                this.sendSmsBak(this.callText,this.smsContent);
            }
        }
        if(requestCode==1003){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                showCamera();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(MainActivity.class.toString(),"onConfigurationChanged"+newConfig.orientation);
        if(newConfig.orientation==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE||newConfig.orientation==ActivityInfo.SCREEN_ORIENTATION_USER){
            Toast.makeText(this,"SCREEN_ORIENTATION_LANDSCAPE",Toast.LENGTH_LONG).show();
        }
        if(newConfig.orientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            Toast.makeText(this,"SCREEN_ORIENTATION_PORTRAIT",Toast.LENGTH_SHORT).show();
        }
        super.onConfigurationChanged(newConfig);

    }
}
