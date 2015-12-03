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
import fr.epsi.epsinotifier.service.EPSIIntentService;
import fr.epsi.epsinotifier.service.RefreshReceiver;


public class MainActivity extends Activity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(MainActivity.this, RefreshReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Button enableNotificationsButton = (Button) findViewById(R.id.enableNotificationsButton);
        enableNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        Button disableNotificationsButton = (Button) findViewById(R.id.disableNotificationsButton);
        disableNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        Button testNotificationsButton = (Button) findViewById(R.id.testNotificationsButton);
        testNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Patientez...", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), EPSIIntentService.class);

                startService(intent);
            }
        });
    }

    private void start() {
        Intent intent = new Intent(MainActivity.this, RefreshReceiver.class);
        sendBroadcast(intent);

        Toast.makeText(this, "Notifications activées", Toast.LENGTH_SHORT).show();
    }

    private void stop() {
        alarmManager.cancel(pendingIntent);

        Toast.makeText(this, "Notifications desactivées", Toast.LENGTH_SHORT).show();
    }
}
