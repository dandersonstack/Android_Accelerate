package com.test.danderson.android_accelerate;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
//import android.support.v4.app.NotificationCompat.WearableExtender;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MainActivity extends Activity{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//       private static final String TWITTER_KEY = "sdl6o1Hk8eZsoiBxSFH6FCvkn";
//    private static final String TWITTER_SECRET = "nZV7Aigonb60HA116MtVGNdTM6PAsep4P7lQguUwz3mxTLTFgX";


    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent Tintent = new Intent(this,MyService.class);
        setContentView(R.layout.activity_main);
        Log.i("service", "Service is starting");
        //final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        //senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        startService(Tintent);
    }
}
