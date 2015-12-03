package fr.epsi.epsinotifier.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.joda.time.LocalDateTime;

public class RefreshReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isSchoolTime()) {
            Intent epsiIntent = new Intent(context, EPSIIntentService.class);
            context.startService(epsiIntent);
        }
    }

    private boolean isSchoolTime() {
        LocalDateTime today = new LocalDateTime();

        return today.getDayOfWeek() <= 5
                && today.getHourOfDay() >= 8
                && today.getHourOfDay() <= 18;
    }
}
