package com.example.eric.hello1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BinderService extends Service {
    private final Random mGenerator = new Random();
    private final IBinder mBinder = new LocalBinder();
    public BinderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    public class LocalBinder extends Binder {
        BinderService getMyService() {
            // Return this instance of LocalService so clients can call public methods
            return BinderService.this;
        }
    }
}
