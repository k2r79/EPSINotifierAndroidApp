package fr.epsi.epsinotifier.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.joda.time.LocalDateTime;

import java.util.Calendar;

public class RefreshReceiver extends BroadcastReceiver {

    private Context context;
    private Intent epsiIntent;
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        epsiIntent = new Intent(context, EPSIIntentService.class);

        scheduleAlarms();

        if (isSchoolTime()) {
            context.startService(epsiIntent);
        }
    }

    private void scheduleAlarms() {
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, epsiIntent, 0);
        alarmManager.cancel(pendingIntent);

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.MINUTE, 50);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
    }

    private boolean isSchoolTime() {
        LocalDateTime today = new LocalDateTime();

        return today.getDayOfWeek() <= 5
                && today.getHourOfDay() >= 8
                && today.getHourOfDay() <= 18;
    }
}
