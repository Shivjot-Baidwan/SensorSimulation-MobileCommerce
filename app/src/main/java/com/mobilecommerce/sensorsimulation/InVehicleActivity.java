package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class InVehicleActivity extends AppCompatActivity {
    private TextView inVehicleScreenMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_vehicle);

        //createAndAddAFragment();
        Typeface typeface5 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        inVehicleScreenMessage = (TextView) findViewById(R.id.drivingScreenTextView);
        inVehicleScreenMessage.setTypeface(typeface5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void createAndAddAFragment() {

        Intent intent = getIntent();// this is used to grab intent and fragment so that it launches
        MainActivity.FragmentToLoad fragmentToLoad = (MainActivity.FragmentToLoad)
                intent.getSerializableExtra(MainActivity.inVehicleFragmentToLoad);

        //grabs our fragment manager and fragment transaction so that we can add the edit or view fragment dynamically.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        InVehicleScreenFragment inVehicleScreenFragment = new InVehicleScreenFragment();
        setTitle(R.string.inVehicle_screen_title);
        fragmentTransaction.add
                (R.id.activity_in_vehicle, inVehicleScreenFragment, "IN_VEHICLE_SCREEN_FRAGMENT");

        fragmentTransaction.commit();// using commit here to make sure that everything we did above actually happens.
    }
}
