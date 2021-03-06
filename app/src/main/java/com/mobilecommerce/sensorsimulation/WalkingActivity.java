/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/
package com.mobilecommerce.sensorsimulation;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

//import static com.mobilecommerce.sensorsimulation.MainActivity.MY_PERMISSIONS_REQUEST_LOCATION;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastActivityTypeDatabase;
import static com.mobilecommerce.sensorsimulation.MainActivity.lastTimeDatabase;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mCurrLocationMarker;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mGoogleApiClient;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mLocationRequest;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mMap;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mapFragment;
//import static com.mobilecommerce.sensorsimulation.MainActivity.mlastLocation;

public class WalkingActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static int position = 0;

    private Integer[] songs = new Integer[6];
    private TextView walkingScreenMessage;

    private String activityTypeToBeEnteredIntoDatabaseWalking = "WALKING";
    private long timeToBeEnteredIntoDatabaseWalking=0;
    private long timeDifferenceWalking=0;

    public  GoogleMap mMap;
    public  GoogleApiClient mGoogleApiClient;
    public  LocationRequest mLocationRequest;
    public  Location mlastLocation;
    public  Marker mCurrLocationMarker;
    public  final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public  SupportMapFragment mapFragment;
    MyDatabaseHandler myDatabaseHandlerWalking = new MyDatabaseHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        // HANDLING DATABASE
        handlingDatabase();

        // HANDLING GOOGLE MAPS
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentWalking);
        mapFragment.getMapAsync(this);

        // SETTING FONT
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        walkingScreenMessage = (TextView) findViewById(R.id.walkingScreenTextView);
        walkingScreenMessage.setTypeface(typeface3);

        // LOADING SONGS IN AN ARRAY
        load_Songs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.fragment);
        //mapFragment.getMapAsync(this);
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng universityOfOttawa = new LatLng(45.424860, -75.682800);
        //mMap.addMarker(new MarkerOptions().position(universityOfOttawa).title("Marker"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(universityOfOttawa));

        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            //checkLocationPermission();
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mlastLocation = location;
        if(mCurrLocationMarker != null){
            mCurrLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(25));

        if(mGoogleApiClient != null) {
            checkLocationPermission();
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, this);
        }

    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    //Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }


    public void handlingDatabase(){
        timeToBeEnteredIntoDatabaseWalking = ((System.currentTimeMillis()) / 1000);

        List<UserMovementDatabase> userMovementDatabaseList = myDatabaseHandlerWalking.viewAllRecords();

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
            timeDifferenceWalking = timeToBeEnteredIntoDatabaseWalking - lastTimeDatabase;

            // SENDING THE DATA TO BE DISPLAYED IN THE TOAST
            MainActivity mainActivity = new MainActivity();
            mainActivity.showActivityToast(activityTypeToBeEnteredIntoDatabaseWalking, lastActivityTypeDatabase, timeDifferenceWalking);
        }

        if(activityTypeToBeEnteredIntoDatabaseWalking.equals(lastActivityTypeDatabase)) {
            // DO NOTHING
        }else {
            // SAVING CURRENT TIME IN FORM OF SECONDS IN DATABASE
            myDatabaseHandlerWalking.addUserMovement(new UserMovementDatabase(timeToBeEnteredIntoDatabaseWalking, activityTypeToBeEnteredIntoDatabaseWalking), this);
        }
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
