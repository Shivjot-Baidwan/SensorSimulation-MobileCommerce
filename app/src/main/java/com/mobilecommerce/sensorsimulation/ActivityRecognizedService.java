/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/

package com.mobilecommerce.sensorsimulation;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import java.util.List;


public class ActivityRecognizedService extends IntentService {

    public ActivityRecognizedService(){
        super("ActivityRecognizedService");
    }
    public ActivityRecognizedService(String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    if(activity.getConfidence() >= 75) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText("Are you in a vehicle?");
                        builder.setSmallIcon(R.drawable.icon);
                        builder.setContentTitle(getString(R.string.app_name));
                        NotificationManagerCompat.from(this).notify(0, builder.build());

                        Intent intent = new Intent(this,InVehicleActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(MainActivity.inVehicleFragmentToLoad, MainActivity.FragmentToLoad.APP_IN_VEHICLE);
                        startActivity(intent);
                    }
                    break;
                }
                /*
                case DetectedActivity.ON_BICYCLE: {
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    if(activity.getConfidence() >= 75) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText("Are you on a Bicycle?");
                        builder.setSmallIcon(R.drawable.icon);
                        builder.setContentTitle(getString(R.string.app_name));
                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }
                    break;
                }

                case DetectedActivity.ON_FOOT: {
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    break;
                }
                */
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    if(activity.getConfidence() >= 75) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText("Are you Running?");
                        builder.setSmallIcon(R.drawable.icon);
                        builder.setContentTitle(getString(R.string.app_name));
                        NotificationManagerCompat.from(this).notify(0, builder.build());

                        Intent intent = new Intent(this,RunningActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(MainActivity.runningFragmentToLoad, MainActivity.FragmentToLoad.APP_RUNNING);
                        startActivity(intent);
                    }
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecogition12", "Still: " + activity.getConfidence() );
                    if(activity.getConfidence() >= 75) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText("Are you still?");
                        builder.setSmallIcon(R.drawable.icon);
                        builder.setContentTitle(getString(R.string.app_name));
                        NotificationManagerCompat.from(this).notify(0,builder.build());

                        Intent intent = new Intent(this,StillActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(MainActivity.stillFragmentToLoad, MainActivity.FragmentToLoad.APP_STILL);
                        startActivity(intent);
                    }
                    break;
                }
                /*
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    break;
                }
                */
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 75 ) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText( "Are you walking?" );
                        builder.setSmallIcon( R.mipmap.ic_launcher );
                        builder.setContentTitle( getString( R.string.app_name ) );
                        NotificationManagerCompat.from(this).notify(0, builder.build());

                        Intent intent = new Intent(this,WalkingActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(MainActivity.walkingFragmentToLoad, MainActivity.FragmentToLoad.APP_WALKING);
                        startActivity(intent);
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    break;
                }
            }
        }
    }
}
