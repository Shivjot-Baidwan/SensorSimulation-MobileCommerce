/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/
package com.mobilecommerce.sensorsimulation;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static com.mobilecommerce.sensorsimulation.MainActivity.lastActivityTypeDatabase;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastTimeDatabase;

public class RunningActivity extends AppCompatActivity {

    private Integer[] songs = new Integer[6];
    private static int position = 0;
    private TextView runningScreenMessage;

    private String activityTypeToBeEnteredIntoDatabaseRunning = "RUNNING";
    private long timeToBeEnteredIntoDatabaseRunning=0;
    private long timeDifferenceRunning=0;
    MyDatabaseHandler myDatabaseHandlerRunning = new MyDatabaseHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        // HANDLING DATABASE
        handlingDatabase();

        // SETTING FONT
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        runningScreenMessage = (TextView) findViewById(R.id.runningScreenTextView);
        runningScreenMessage.setTypeface(typeface4);

        // LOADING SONGS IN AN ARRAY
        load_Songs();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void handlingDatabase(){
        timeToBeEnteredIntoDatabaseRunning = ((System.currentTimeMillis()) / 1000);

        List<UserMovementDatabase> userMovementDatabaseList = myDatabaseHandlerRunning.viewAllRecords();

        // GETTING THE START TIME AND ACTIVITY TYPE FROM DATABASE
        Log.d("SIZE OF THE VIEW ALL: ", String.valueOf(userMovementDatabaseList.size()));
        for (UserMovementDatabase userMovementDatabase : userMovementDatabaseList) {
            lastTimeDatabase = userMovementDatabase.getStartTime();
            lastActivityTypeDatabase = userMovementDatabase.getActivityType();
            Log.d("RECORD TIME ", String.valueOf(userMovementDatabase.getStartTime()));
            Log.d("RECORD ACTIVITY TYPE ", userMovementDatabase.getActivityType());
        }

        if(userMovementDatabaseList.size()!=0) {
            // CALCULATING THE CURRENT TIME AND DURATION OF LAST ACTIVITY
            timeDifferenceRunning = timeToBeEnteredIntoDatabaseRunning - lastTimeDatabase;

            // SENDING THE DATA TO BE DISPLAYED IN THE TOAST
            MainActivity mainActivity = new MainActivity();
            mainActivity.showActivityToast("RUNNING", lastActivityTypeDatabase, timeDifferenceRunning);
        }
        // SAVING CURRENT TIME IN FORM OF SECONDS IN DATABASE
        myDatabaseHandlerRunning.addUserMovement(new UserMovementDatabase(timeToBeEnteredIntoDatabaseRunning, activityTypeToBeEnteredIntoDatabaseRunning), this);
    }

    public void load_Songs() {
        songs[0] = R.raw.calvin_harris;
        songs[1] = R.raw.charda_seeyal;
        songs[2] = R.raw.creez;
        songs[3] = R.raw.sendmylove;
        songs[4] = R.raw.how_deep_is_your_love;
        songs[5] = R.raw.this_is_what_you_came_for;

    }
    public void playMusic(View view) {
        MainActivity.soundPlayer = MediaPlayer.create(this, songs[position]);
        MainActivity.soundPlayer.start();
    }

    public void pauseMusic(View view){
        if (MainActivity.soundPlayer.isPlaying()) {
            MainActivity.soundPlayer.pause();
        } else
            MainActivity.soundPlayer.start();
    }

    public void stopMusic(View view) {
        MainActivity.soundPlayer.stop();
        position = 0;
        MainActivity.soundPlayer = MediaPlayer.create(this, songs[position]);

    }
    public void previousMusic(View view) {
        if (MainActivity.soundPlayer.isPlaying()) {
            MainActivity.soundPlayer.stop();
        }
        if (position == 0) {
            position = 0;
        } else {
            position--;
        }
        MainActivity.soundPlayer = MediaPlayer.create(this, songs[position]);
        MainActivity.soundPlayer.start();

    }
    public void nextMusic(View view) {
        if (MainActivity.soundPlayer.isPlaying()) {
            MainActivity.soundPlayer.stop();
        }
        if (position == 5) {
            position = 0;
        } else {
            position++;
        }
        MainActivity.soundPlayer = MediaPlayer.create(this, songs[position]);
        MainActivity.soundPlayer.start();

    }
}
