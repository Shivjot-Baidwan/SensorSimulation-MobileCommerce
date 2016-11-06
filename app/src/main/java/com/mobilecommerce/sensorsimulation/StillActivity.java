/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
 */
package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class StillActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still);

        createAndAddAFragment();

        Button stopMusic = (Button) findViewById(R.id.musicStopButton);

        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("abc", "abc");
                try {
                    //if (mediaPlayer != null) {
                        mediaPlayer.reset();
                        mediaPlayer.prepare();
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    //}
                } catch (Exception exception) {
                    exception.toString();
                }
            }
        });
    }

    private void createAndAddAFragment() {

        Intent intent = getIntent();// this is used to grab intent and fragment so that it launches
        MainActivity.FragmentToLoad fragmentToLoad = (MainActivity.FragmentToLoad)
                intent.getSerializableExtra(MainActivity.stillFragmentToLoad);

        //grabs our fragment manager and fragment transaction so that we can add the edit or view fragment dynamically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        StillScreenFragment stillScreenFragment = new StillScreenFragment();
        setTitle(R.string.still_screen_title);
        fragmentTransaction.add
                (R.id.activity_still, stillScreenFragment, "STILL_SCREEN_FRAGMENT");

        fragmentTransaction.commit();// using commit here to make sure that everything we did above actually happens.
    }

    public void playMusic(View view) {
        mediaPlayer = MediaPlayer.create(this, R.raw.how_deep_is_your_love);
        mediaPlayer.start();
    }

    /*
        public void stopMusic(View view){
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                    mediaPlayer.prepare();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }catch(Exception exception){
                exception.toString();
            }
    //        mediaPlayer.stop();
        }
    */
    public void playNextSong(View view) {
        try {
            Log.d("abc", "abc");
            //if (mediaPlayer != null) {
                mediaPlayer.reset();
                mediaPlayer.prepare();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                mediaPlayer = MediaPlayer.create(this, R.raw.this_is_what_you_came_for);
                mediaPlayer.start();
           // }
            //mediaPlayer.setNextMediaPlayer(MediaPlayer.create(this, R.raw.this_is_what_you_came_for));
        } catch (Exception exception) {
            exception.toString();
        }

    }

}