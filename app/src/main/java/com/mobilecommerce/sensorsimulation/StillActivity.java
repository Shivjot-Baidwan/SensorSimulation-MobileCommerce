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
import android.view.View;
import android.widget.ImageButton;

public class StillActivity extends AppCompatActivity {

    //MediaPlayer mediaPlayer;
    private static int position = 0;

    private ImageButton playButton, pauseButton, stopButton, nextSongButton, previousSongButton;
    private MediaPlayer soundPlayer;
    private Integer[] songs = new Integer[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still);

        createAndAddAFragment();
        load_Songs();

        playButton = (ImageButton) findViewById(R.id.playButtonRunning);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        stopButton = (ImageButton) findViewById(R.id.stopButton);
        nextSongButton = (ImageButton) findViewById(R.id.nextSongButton);
        previousSongButton = (ImageButton) findViewById(R.id.previousSongButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Error", "Error on Create ");
                soundPlayer = MediaPlayer.create(getApplicationContext(), songs[position]);
                soundPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundPlayer.isPlaying()) {
                    soundPlayer.pause();
                } else
                    soundPlayer.start();

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer.stop();
                position = 0;
                soundPlayer = MediaPlayer.create(getApplicationContext(), songs[position]);

            }
        });
        nextSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundPlayer.isPlaying()) {
                    soundPlayer.stop();
                }
                if (position == 5) {
                    position = 0;
                } else {
                    position++;
                }
                soundPlayer = MediaPlayer.create(getApplicationContext(), songs[position]);
                soundPlayer.start();
            }
        });

        previousSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundPlayer.isPlaying()) {
                    soundPlayer.stop();
                }
                if (position == 0) {
                    position = 0;
                } else {
                    position--;
                }
                soundPlayer = MediaPlayer.create(getApplicationContext(), songs[position]);
                soundPlayer.start();
            }
        });



        /*
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
        }); */
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
    /*
    public void playMusic(View view) {
        mediaPlayer = MediaPlayer.create(this, R.raw.how_deep_is_your_love);
        mediaPlayer.start();
    }


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

    } */

    public void load_Songs() {
        songs[0] = R.raw.calvin_harris;
        songs[1] = R.raw.charda_seeyal;
        songs[2] = R.raw.creez;
        songs[3] = R.raw.sendmylove;
        songs[4] = R.raw.how_deep_is_your_love;
        songs[5] = R.raw.this_is_what_you_came_for;

    }

}