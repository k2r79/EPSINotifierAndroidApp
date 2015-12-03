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
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
        Toast.makeText(this, "Notifications activées", Toast.LENGTH_SHORT).show();
    }
}
