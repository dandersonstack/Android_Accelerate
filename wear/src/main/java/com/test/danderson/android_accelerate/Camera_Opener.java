package com.test.danderson.android_accelerate;

/**
 * Created by Danderson on 7/10/15.
 */

import android.app.IntentService;
import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.service.carrier.CarrierMessagingService;
import android.support.v4.app.NotificationCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.Node;
import android.content.BroadcastReceiver;


import java.util.Set;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Camera_Opener extends Service {

    private final static String CAPABILITY_NAME = "camera";
    private final String RECEIVER_SERVICE_PATH = "/send_to_mobile";
    private TextView mTextView;
    private GoogleApiClient mGoogleApiClient;

    public Camera_Opener() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onHandleIntent(Intent intent) {
        Log.i("Some Tag", "Please run, Please");
        Log.i("Tag it","The Intent was reached.");
        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        // Do something
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        // Do something
                    }
                })

                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CapabilityApi.GetCapabilityResult capResult =
                        Wearable.CapabilityApi.getCapability(
                                mGoogleApiClient, CAMERA_SERVICE,
                                CapabilityApi.FILTER_REACHABLE)
                                .await();

                Set<com.google.android.gms.wearable.Node> capableNodes = capResult.getCapability().getNodes();
                Node closest = null;
                if (!capableNodes.isEmpty())

                {
                    for (Node node : capableNodes) {
                        if (node.isNearby()) {
                            closest = node;
                            break;
                        }
                        closest = node;
                    }
                    ;
                }
                Wearable.MessageApi.sendMessage(
                        mGoogleApiClient, closest.getId(),
                        RECEIVER_SERVICE_PATH, new byte[3]
                );
            }
        }).start();
    }
}
