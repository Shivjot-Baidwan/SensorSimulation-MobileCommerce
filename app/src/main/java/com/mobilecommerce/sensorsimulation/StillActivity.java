/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
 */
package com.mobilecommerce.sensorsimulation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import java.util.List;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastActivityTypeDatabase;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastTimeDatabase;

public class StillActivity extends AppCompatActivity {
    private TextView stillScreenMessage;
    private String activityTypeToBeEnteredIntoDatabaseStill = "STILL";
    private long timeToBeEnteredIntoDatabaseStill=0;
    private long timeDifferenceStill=0;
    MyDatabaseHandler myDatabaseHandlerStill = new MyDatabaseHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still);


        // HANDLING DATABASE
        handlingDatabase();


        // SETTING FONT
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        stillScreenMessage = (TextView) findViewById(R.id.stillScreenTextView);
        stillScreenMessage.setTypeface(typeface2);

    }

    public void handlingDatabase(){
        timeToBeEnteredIntoDatabaseStill = ((System.currentTimeMillis()) / 1000);

        List<UserMovementDatabase> userMovementDatabaseList = myDatabaseHandlerStill.viewAllRecords();

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
            timeDifferenceStill = timeToBeEnteredIntoDatabaseStill - lastTimeDatabase;

            // SENDING THE DATA TO BE DISPLAYED IN THE TOAST
            MainActivity mainActivity = new MainActivity();
            mainActivity.showActivityToast("STILL", lastActivityTypeDatabase, timeDifferenceStill);
        }
        // SAVING CURRENT TIME IN FORM OF SECONDS IN DATABASE
        myDatabaseHandlerStill.addUserMovement(new UserMovementDatabase(timeToBeEnteredIntoDatabaseStill, activityTypeToBeEnteredIntoDatabaseStill), this);
    }

}
