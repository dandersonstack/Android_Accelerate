package com.test.danderson.android_accelerate;

/**
 * Created by Danderson on 7/10/15.
 */

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener {

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("Service just started", "Start");
        Toast.makeText(this, "A service is about to start", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    public MyService() {
        super();
    }
    long lastUpdate = 0;
    float last_x, last_y, last_z;
    final int SHAKE_THRESHOLD = 600;
    SensorManager senSensorManager;
    Sensor senAccelerometer;
    int notificationId=001;

    void sendSimpleNotification() {
        Intent pIntent = new Intent(this, Camera_Opener.class);

// Specify data you want this activity to have access to

        //fbIntent.putExtra(“username”, username);

        PendingIntent p = PendingIntent.getActivity(this, 0, pIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Are you excited")
                        .setSmallIcon(R.drawable.generic_confirmation)
                        .setContentText("You should document it")
                        .setContentIntent(p)
                        .addAction(R.drawable.generic_confirmation, "Do you want to take a picture?", p);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
        //startService(pIntent);
        Log.i("Some Tag", "I hate this");
        //Toast.makeText(this, "Check if ran", Toast.LENGTH_SHORT).show();

    }
    protected void onPause() {
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i("Some tag", "The sensor was changed.");
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor mySensor = sensorEvent.sensor;
        if (senAccelerometer.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    sendSimpleNotification();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
