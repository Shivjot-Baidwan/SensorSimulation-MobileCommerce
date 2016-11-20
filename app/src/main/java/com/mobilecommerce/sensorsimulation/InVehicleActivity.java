package com.mobilecommerce.sensorsimulation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import java.util.List;

import static com.mobilecommerce.sensorsimulation.MainActivity.lastActivityTypeDatabase;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastTimeDatabase;

public class InVehicleActivity extends AppCompatActivity {
    private TextView inVehicleScreenMessage;

    private String activityTypeToBeEnteredIntoDatabaseInVehicle = "IN_VEHICLE";
    private long timeToBeEnteredIntoDatabaseInVehicle=0;
    private long timeDifferenceInVehicle=0;
    MyDatabaseHandler myDatabaseHandlerInVehicle = new MyDatabaseHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_vehicle);

        // HANDLING DATABASE
        handlingDatabase();

        // SETTING FONT
        Typeface typeface5 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        inVehicleScreenMessage = (TextView) findViewById(R.id.drivingScreenTextView);
        inVehicleScreenMessage.setTypeface(typeface5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void handlingDatabase(){
        timeToBeEnteredIntoDatabaseInVehicle = ((System.currentTimeMillis()) / 1000);

        List<UserMovementDatabase> userMovementDatabaseList = myDatabaseHandlerInVehicle.viewAllRecords();

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
            timeDifferenceInVehicle = timeToBeEnteredIntoDatabaseInVehicle - lastTimeDatabase;

            // SENDING THE DATA TO BE DISPLAYED IN THE TOAST
            MainActivity mainActivity = new MainActivity();
            mainActivity.showActivityToast("IN_VEHICLE", lastActivityTypeDatabase, timeDifferenceInVehicle);
        }
        // SAVING CURRENT TIME IN FORM OF SECONDS IN DATABASE
        myDatabaseHandlerInVehicle.addUserMovement(new UserMovementDatabase(timeToBeEnteredIntoDatabaseInVehicle, activityTypeToBeEnteredIntoDatabaseInVehicle), this);
    }
}
