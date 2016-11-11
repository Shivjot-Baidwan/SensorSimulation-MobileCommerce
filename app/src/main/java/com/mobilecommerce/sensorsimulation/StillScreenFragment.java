/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
 */
package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class StillScreenFragment extends Fragment  implements OnMapReadyCallback {
    //MediaPlayer mediaPlayer;
    private static int position = 0;
    StillActivity stillActivity = new StillActivity();

    private ImageButton playButton, pauseButton, stopButton, nextSongButton, previousSongButton;
    private MediaPlayer soundPlayer;
    private Integer[] songs = new Integer[6];



    public StillScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layoutFragment = inflater.inflate(R.layout.activity_still, container, false);
        Intent intent = getActivity().getIntent();
        playButton = (ImageButton) getActivity().findViewById(R.id.playButtonRunning);
        pauseButton = (ImageButton) getActivity().findViewById(R.id.pauseButton);
        stopButton = (ImageButton) getActivity().findViewById(R.id.stopButton);
        nextSongButton = (ImageButton) getActivity().findViewById(R.id.nextSongButton);
        previousSongButton = (ImageButton) getActivity().findViewById(R.id.previousSongButton);

        load_Songs();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Error", "Error on Create ");
                soundPlayer = MediaPlayer.create(getContext(), songs[position]);
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
                soundPlayer = MediaPlayer.create(getContext(), songs[position]);

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
                soundPlayer = MediaPlayer.create(getContext(), songs[position]);
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
                soundPlayer = MediaPlayer.create(getActivity().getApplicationContext(), songs[position]);
                soundPlayer.start();
            }
        });

        return layoutFragment;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

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
