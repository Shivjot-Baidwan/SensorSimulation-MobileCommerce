/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static MediaPlayer soundPlayer;

    public static GoogleApiClient mApiClient;
    public static final String inVehicleFragmentToLoad = "com.mobilecommerce.sensorsimulation.DrivingScreenFragment";
    public static final String runningFragmentToLoad = "com.mobilecommerce.sensorsimulation.RunningScreenFragment";
    public static final String stillFragmentToLoad = "com.mobilecommerce.sensorsimulation.StillScreenFragment";
    public static final String walkingFragmentToLoad = "com.mobilecommerce.sensorsimulation.WalkingScreenFragment";
    public static final String mapFragmentToLoad = "com.mobilecommerce.sensorsimulation.OurMapFragment";

    private ImageView animationImages;
    public TextView welcomeScreenMessage1,welcomeScreenMessage2,drivingScreenMessage;

    public enum FragmentToLoad{APP_IN_VEHICLE, APP_RUNNING, APP_STILL, APP_WALKING, APP_MAP}
    public Typeface typeface;

    public static long lastTimeDatabase=0;
    public static String lastActivityTypeDatabase;

    private static Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_welcome_screen);

        context = getApplicationContext();

        MyDatabaseHandler myDatabaseHandler = new MyDatabaseHandler(this, null, null, 1);
        myDatabaseHandler.createDatabasetable();

        typeface = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");

        welcomeScreenMessage1 = (TextView) findViewById(R.id.welcomeMessage1);
        welcomeScreenMessage2 = (TextView) findViewById(R.id.welcomeMessage2);

        welcomeScreenMessage1.setTypeface(typeface);
        welcomeScreenMessage2.setTypeface(typeface);


        getSupportActionBar().setTitle(Html.fromHtml("<font color = '#0000b7'>SENSE ME</font"));

        final Calendar t = Calendar.getInstance();

        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        dateTextView.setText(android.text.format.DateFormat.getDateFormat(this).format(t.getTime()));

        animationImages = (ImageView) findViewById(R.id.animationView);

        animationImages.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) animationImages.getBackground()).start();
            }
        });

        //After Implementing the interfaces for GoogleApiClient, initializing the GoogleApiClient.
        //Also connecting to Google Play Services.

        mApiClient =  new GoogleApiClient.Builder(this)

                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)

                .build();

        mApiClient.connect();
    }

    //implementing the required interfaces for GoogleApiClient
    @Override
    public void onConnected(@Nullable Bundle bundle){
        Log.d("Success: ", "Connected to GoogleAPIClient");
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
    Log.d("Failure: ", "Failed to connect to GoogleAPIClient");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);
        return true;
    }

    public void showActivityToast(String activityType, String lastActivityTypeDatabase, long durationOfLastActivity){
        String textToBeShownInToast="";
        if(lastActivityTypeDatabase.equals("STILL")){
            textToBeShownInToast = "remained still";
        }else if (lastActivityTypeDatabase.equals("RUNNING")){
            textToBeShownInToast = "ran";
        }else if (lastActivityTypeDatabase.equals("IN_VEHICLE")){
            textToBeShownInToast = "were in vehicle";
        }else if (lastActivityTypeDatabase.equals("WALKING")){
            textToBeShownInToast = "were walking";
        }

        if(activityType.equals(lastActivityTypeDatabase)){
            // DO NOTHING
        }else{
            int seconds = ((int)durationOfLastActivity)%60;
            int totalMinutes = ((int)durationOfLastActivity)/60;
            int minutes = totalMinutes%60;
            int hours = totalMinutes/60;

            CharSequence text = "Hello Buddy. You "+textToBeShownInToast+" for "+hours+" hours, "+ minutes +" minutes, "+seconds+" seconds ";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}
