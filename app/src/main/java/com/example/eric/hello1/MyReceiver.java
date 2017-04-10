package com.example.eric.hello1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message=(String)intent.getExtras().get("message");
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
