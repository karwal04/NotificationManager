package com.example.karwal.notificationmanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

/**
 * Created by karwal on 30-04-2017.
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context,"times up","5 sec completed","alert");
    }

    private void createNotification(Context context, String s, String s1, String alert) {

        PendingIntent notification=PendingIntent.getActivity(context,0, new Intent(context,MoreInfo.class),0);

        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(context)
                .setContentTitle(s)
                .setContentText(s1)
                .setTicker(alert)
                .setSmallIcon(R.drawable.ic_shopping_basket_black_24dp);

        mbuilder.setContentIntent(notification);
        mbuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mbuilder.setVibrate(new long[] {0, 100, 1000, 300, 200, 100, 500, 200, 100});
        mbuilder.setLights(Color.RED, 3000, 3000);
        mbuilder.setAutoCancel(true);

        NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1,mbuilder.build());

    }
}
