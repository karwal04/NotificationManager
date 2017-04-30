package com.example.karwal.notificationmanager;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Button shownotif,stopnotif,alertnotifi;

    static NotificationManager mNotificationManager;

    static boolean isNotificationActive= false;
    static int notifID=33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shownotif=(Button)findViewById(R.id.Btn1);
        stopnotif=(Button)findViewById(R.id.Btn2);
        alertnotifi=(Button)findViewById(R.id.Btn3);


    }

    public void showNotif(View view) {

        NotificationCompat.Builder notification=new NotificationCompat.Builder(this)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert new Message received")
                .setSmallIcon(R.drawable.ic_shopping_basket_black_24dp);

        Intent intent=new Intent(this,MoreInfo.class);

        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);

        taskStackBuilder.addParentStack(MoreInfo.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent=taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setContentIntent(pendingIntent);
        mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifID,notification.build());
        isNotificationActive=true;

    }

    public void stopNotif(View view) {

        if(isNotificationActive){
                mNotificationManager.cancel(notifID);

        }

    }

    public void AlarmManager(View view){

        Log.v("dddooogggggg","executed") ;
        Long alertime=new GregorianCalendar().getTimeInMillis()+5*1000;


        Calendar myAlarmDate = Calendar.getInstance();
        myAlarmDate.setTimeInMillis(System.currentTimeMillis());
        myAlarmDate.set(2017, 03, 30, 17, 06, 0);

        Intent alertIntent=new Intent(this,AlertReceiver.class);

        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,myAlarmDate.getTimeInMillis(),PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();

    }
}
