/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public GoogleApiClient mApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_welcome_screen);

        TextView welcomeScreenMessage1 = (TextView) findViewById(R.id.welcomeMessage1);
        TextView welcomeScreenMessage2 = (TextView) findViewById(R.id.welcomeMessage2);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        welcomeScreenMessage1.setTypeface(typeface);
        welcomeScreenMessage2.setTypeface(typeface);

        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#0000b7'>SENSE ME</font"));




        Calendar c = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String currentDateTimeString = simpleDateFormat.format(c.getTime());

        Toast.makeText(this,currentDateTimeString,Toast.LENGTH_SHORT).show();

        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        dateTextView.setText(currentDateTimeString);
        //dateTextView.setTypeface(typeface);

        UserMovementDatabase userMovementDatabase = new UserMovementDatabase("time","WALKING");

        MyDatabaseHandler myDatabaseHandler = new MyDatabaseHandler(this, null, null, 1);
        myDatabaseHandler.addUserMovement(userMovementDatabase, this);

        myDatabaseHandler.viewAllRecords();
        myDatabaseHandler.printUserMovementRecords();

        //After Implementing the interfaces for GoogleApiClient, initializing the GoogleApiClient.
        //Also connecting to Google Play Services.

        mApiClient =  new GoogleApiClient.Builder(this)

                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)

                .build();

        mApiClient.connect();



    }

    //implementing the required interfaces for GoogleApiClient
    @Override
    public void onConnected(@Nullable Bundle bundle){
        Log.d("FAILURE", "failure");
        Intent intent = new Intent(this, ActivityRecognizedService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 3000, pendingIntent);

    }

    //implementing the required interfaces for GoogleApiClient
    @Override
    public void onConnectionSuspended(int i) {

    }

    //implementing the required interfaces for GoogleApiClient
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Log.d("FAILURE", "failure");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);
        return true;

    }

}
