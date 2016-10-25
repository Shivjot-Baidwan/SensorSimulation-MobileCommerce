package com.mobilecommerce.sensorsimulation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);
        return true;

    }

}
