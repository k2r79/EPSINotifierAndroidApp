package fr.epsi.epsinotifier.app;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import fr.epsi.epsinotifier.service.RefreshReceiver;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

        Button testNotificationsButton = (Button) findViewById(R.id.testNotificationsButton);
        testNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationId = 1;

                DateTimeFormatter outputDateFormatter = DateTimeFormat.forPattern("HH:mm");

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setVibrate(new long[]{ 250, 800 })
                        .setSmallIcon(R.drawable.logo_epsi)
                        .setContentTitle("EPSI")
                        .setContentText("Notification de test");

                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(notificationId, notificationBuilder.build());
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
