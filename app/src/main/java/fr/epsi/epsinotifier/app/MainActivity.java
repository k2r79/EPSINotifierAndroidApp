package fr.epsi.epsinotifier.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import fr.epsi.epsinotifier.service.RefreshReceiver;

import java.util.Calendar;


public class MainActivity extends Activity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(MainActivity.this, RefreshReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        Button enableNotificationsButton = (Button) findViewById(R.id.enableNotificationsButton);
        enableNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private void start() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.MINUTE, 55);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);

        Toast.makeText(this, "Notifications activées", Toast.LENGTH_SHORT).show();
    }
}
