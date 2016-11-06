package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class RunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        createAndAddAFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void createAndAddAFragment() {

        Intent intent = getIntent();// this is used to grab intent and fragment so that it launches
        MainActivity.FragmentToLoad fragmentToLoad = (MainActivity.FragmentToLoad)
                intent.getSerializableExtra(MainActivity.runningFragmentToLoad);

        //grabs our fragment manager and fragment transaction so that we can add the edit or view fragment dynamically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RunningScreenFragment runningScreenFragment = new RunningScreenFragment();
        setTitle(R.string.running_screen_title);
        fragmentTransaction.add
                (R.id.activity_running, runningScreenFragment, "RUNNING_SCREEN_FRAGMENT");

        fragmentTransaction.commit();// using commit here to make sure that everything we did above actually happens.
    }
}
