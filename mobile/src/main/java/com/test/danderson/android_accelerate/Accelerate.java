package com.test.danderson.android_accelerate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import io.fabric.sdk.android.Fabric;
//import com.twitter.sdk.android.tweetcomposer.TweetComposer;


public class Accelerate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerate);
        //TwitterAuthConfig authConfig = new TwitterAuthConfig("consumerKey", "consumerSecret");
        //Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());

        createNotificationListener1();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createNotificationListener1(){
        Intent practice = new Intent(this,TakeThePicture.class);
        startActivity(practice);

        //Intent listener = new Intent(this,OpenTheCamera.class);
        //startService(listener);
    }
}
