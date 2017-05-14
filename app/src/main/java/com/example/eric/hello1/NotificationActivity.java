package com.example.eric.hello1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class NotificationActivity extends AppCompatActivity {
    static int pendingCnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button bt=(Button)findViewById(R.id.buttonNotification);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationActivity.this.showAppNotification();
            }
        });

        bt=(Button)findViewById(R.id.buttonNotification1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationActivity.this.showNotifcation1();
            }
        });
        bt=(Button)findViewById(R.id.buttonNotifycation3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.cancel(R.id.buttonNotification1);
            }
        });
        bt=(Button)findViewById(R.id.buttonNotification4);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager =    (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder nb1=new Notification.Builder(NotificationActivity.this);
                nb1.setSmallIcon(R.drawable.stat_sample);  // the status ico
                        nb1.setTicker("tickerText");  // the status text
                        nb1.setWhen(System.currentTimeMillis()) ; // the time stamp
                        nb1.setContentTitle("title") ; // the label of the entry
                        nb1.setContentText("message");  // the contents of the entry
                Notification nf=nb1.build();
                notificationManager.notify(R.id.buttonNotification4,nf);
                //notificationManager.cancel();

            }
        });
    }
    void showNotifcation1(){
        //得到NotificationManager
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //创建Notification.Builder
        Notification.Builder notifBuilder = new Notification.Builder(this);
        //设置属性
        notifBuilder.setContentTitle("notify tile");
        notifBuilder.setSmallIcon(android.R.drawable.ic_input_add);//(R.drawable.stat_sample);

        notifBuilder.setWhen(System.currentTimeMillis());
        notifBuilder.setContentText("notify content");
        notifBuilder.setDefaults(Notification.DEFAULT_ALL);
        //构建Notification
        Notification nf=notifBuilder.build();
        //显示Notification
        nm.notify(R.id.buttonNotification1,nf);

    }

    void showAppNotification() {
        // look up the notification manager service
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // The details of our fake message
        String from = "Joe";
        String message;
        switch ((new Random().nextInt()) % 3) {
            case 0: message = "r u hungry?  i am starved"; break;
            case 1: message = "im nearby u"; break;
            default: message = "kthx. meet u for dinner. cul8r"; break;
        }

        // The PendingIntent to launch our activity if the user selects this
        // notification.  Note the use of FLAG_CANCEL_CURRENT so that, if there
        // is already an active matching pending intent, cancel it and replace
        // it with the new array of Intents.
        PendingIntent contentIntent = PendingIntent.getActivity(NotificationActivity.this, 0,
                makeMessageIntentStack(NotificationActivity.this, from, message), PendingIntent.FLAG_CANCEL_CURRENT);

        // The ticker text, this uses a formatted string so our message could be localized
        String tickerText =  message;

        // Set the info for the views that show in the notification panel.
        Notification.Builder notifBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.stat_sample)  // the status icon
                .setTicker(tickerText)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(from)  // the label of the entry
                .setContentText(message)  // the contents of the entry
                .setContentIntent(contentIntent);  // The intent to send when the entry is clicked

        // We'll have this notification do the default sound, vibration, and led.
        // Note that if you want any of these behaviors, you should always have
        // a preference for the user to turn them off.
        notifBuilder.setDefaults(Notification.DEFAULT_ALL);

        // Note that we use R.layout.incoming_message_panel as the ID for
        // the notification.  It could be any integer you want, but we use
        // the convention of using a resource id for a string related to
        // the notification.  It will always be a unique number within your
        // application.
        pendingCnt++;
        nm.notify(R.layout.activity_notification, notifBuilder.build());
    }

    static Intent makeMessageIntentStack(Context context, CharSequence from,
                                           CharSequence msg) {
        // A typical convention for notifications is to launch the user deeply
        // into an application representing the data in the notification; to
        // accomplish this, we can build an array of intents to insert the back
        // stack stack history above the item being displayed.
        Intent intents = new Intent(context, IncomingMessageView.class);
        intents.putExtra(IncomingMessageView.KEY_FROM, from);
        intents.putExtra(IncomingMessageView.KEY_MESSAGE, msg);

        return intents;
    }
}
