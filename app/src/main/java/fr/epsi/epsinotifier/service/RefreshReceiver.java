package fr.epsi.epsinotifier.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class RefreshReceiver extends BroadcastReceiver {

    private Context context;
    private Intent epsiIntent;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        epsiIntent = new Intent(context, EPSIIntentService.class);
        pendingIntent = PendingIntent.getService(context, 0, epsiIntent, 0);

        if (intent.getBooleanExtra("stop", false)) {
            Log.i("EPSI Planning", "Suppression de l'alarme EPSI planning...");
            alarmManager.cancel(pendingIntent);
        } else {
            Log.i("EPSI Planning", "Ajout de l'alarme EPSI planning...");
            scheduleAlarms();

            context.startService(epsiIntent);
        }
    }

    private void scheduleAlarms() {
        alarmManager.cancel(pendingIntent);

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.MINUTE, 50);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
    }
}
