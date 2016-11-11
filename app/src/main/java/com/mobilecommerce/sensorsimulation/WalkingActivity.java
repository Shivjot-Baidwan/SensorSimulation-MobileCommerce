package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class WalkingActivity extends AppCompatActivity {

    private static int position = 0;

    private ImageButton playButton, pauseButton, stopButton, nextSongButton, previousSongButton;
    private MediaPlayer soundPlayer;
    private Integer[] songs = new Integer[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        createAndAddAFragment();
        load_Songs();
        playButton = (ImageButton) findViewById(R.id.playButtonWalking);
        pauseButton = (ImageButton) findViewById(R.id.pauseButtonWalking);
        stopButton = (ImageButton) findViewById(R.id.stopButtonWalking);
        nextSongButton = (ImageButton) findViewById(R.id.nextSongButtonWalking);
        previousSongButton = (ImageButton) findViewById(R.id.previousSongButtonWalking);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void createAndAddAFragment() {

        Intent intent = getIntent();// this is used to grab intent and fragment so that it launches
        MainActivity.FragmentToLoad fragmentToLoad = (MainActivity.FragmentToLoad)
                intent.getSerializableExtra(MainActivity.walkingFragmentToLoad);

        //grabs our fragment manager and fragment transaction so that we can add the edit or view fragment dynamically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WalkingScreenFragment walkingScreenFragment = new WalkingScreenFragment();
        setTitle(R.string.walking_screen_title);
        fragmentTransaction.add
                (R.id.activity_walking, walkingScreenFragment, "WALKING_SCREEN_FRAGMENT");

        fragmentTransaction.commit();// using commit here to make sure that everything we did above actually happens.
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


    public void load_Songs() {
        songs[0] = R.raw.calvin_harris;
        songs[1] = R.raw.charda_seeyal;
        songs[2] = R.raw.creez;
        songs[3] = R.raw.sendmylove;
        songs[4] = R.raw.how_deep_is_your_love;
        songs[5] = R.raw.this_is_what_you_came_for;

    }
}
