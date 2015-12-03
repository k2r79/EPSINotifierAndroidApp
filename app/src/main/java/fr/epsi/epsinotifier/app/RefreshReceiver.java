package fr.epsi.epsinotifier.app;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class RefreshReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent epsiIntent = new Intent(context, EPSIIntentService.class);
        context.startService(epsiIntent);

        int notificationId = 1;

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Android Wear Notification");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
