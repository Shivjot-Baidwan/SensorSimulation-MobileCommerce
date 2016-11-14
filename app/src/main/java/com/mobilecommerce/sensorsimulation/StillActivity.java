/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
 */
package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import static android.R.attr.typeface;

public class StillActivity extends AppCompatActivity  {

    private static int position = 0;

    private ImageButton playButton, pauseButton, stopButton, nextSongButton, previousSongButton;
    private Integer[] songs = new Integer[6];
    private GoogleMap googleMap;
    private TextView stillScreenMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still);
        //MainActivity mainActivity = new MainActivity();

        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        stillScreenMessage = (TextView) findViewById(R.id.stillScreenTextView);
        //mainActivity.stillScreenMessage = (TextView) findViewById(R.id.stillScreenTextView);
        stillScreenMessage.setTypeface(typeface2);

        //createAndAddAFragment();

        playButton = (ImageButton) findViewById(R.id.playButtonRunning);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        stopButton = (ImageButton) findViewById(R.id.stopButton);
        nextSongButton = (ImageButton) findViewById(R.id.nextSongButton);
        previousSongButton = (ImageButton) findViewById(R.id.previousSongButton);

        load_Songs();
    }
    private void createAndAddAFragment() {

        //grabs our fragment manager and fragment transaction so that we can add the edit or view fragment dynamically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //FIRST FRAGMENT STARTS **********
        MapFragmentTest mapFragmentTest = new MapFragmentTest();
        setTitle(R.string.still_screen_title);
        fragmentTransaction.add
                (R.id.mapFrag, mapFragmentTest, "MAP_SCREEN_FRAGMENT");
        //FIRST FRAGMENT ENDS **********

        //SECOND FRAGMENT STARTS **********
        // MainActivity.FragmentToLoad fragmentToLoad = (MainActivity.FragmentToLoad)
        //        intent.getSerializableExtra(MainActivity.stillFragmentToLoad);
        //GoogleMapFragment googleMapFragment = new GoogleMapFragment();
        //setTitle(R.string.map_fragment_title);
        //fragmentTransaction.add
        //      (R.id.activity_still, googleMapFragment, "MAP_SCREEN_FRAGMENT");
        //SECOND FRAGMENT ENDS **********


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
