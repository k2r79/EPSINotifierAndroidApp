package fr.epsi.epsinotifier.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RefreshReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent epsiIntent = new Intent(context, EPSIIntentService.class);
        context.startService(epsiIntent);
    }
}
