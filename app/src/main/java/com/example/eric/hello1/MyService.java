package com.example.eric.hello1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer myPlayer;
    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
        myPlayer.stop();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myPlayer = MediaPlayer.create(this, R.raw.wav);
        myPlayer.setLooping(true); // Set looping
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"onStartCommand",Toast.LENGTH_SHORT).show();

        double randomDouble = Math.random();
        String msg = "随机数："+ String.valueOf(randomDouble);
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        myPlayer.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
