
package com.mobilecommerce.sensorsimulation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


//public class GoogleMapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    public GoogleMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layoutFragment = inflater.inflate(R.layout.fragment_map, container, false);
        Intent intent = getActivity().getIntent();

        return layoutFragment;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
    /*
    private FragmentActivity fragmentActivity;


    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    LatLng latLng;
    GoogleMap mGoogleMap;
    SupportMapFragment mFragment;
    Marker currLocationMarker;


    public GoogleMapFragment() {
        // Required empty public constructor
    }


   // @Override
   // public void onAttach(Activity activity) {
   //     fragmentActivity=(FragmentActivity) activity;
   //     super.onAttach(activity);
   // }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentManager fragManager = fragmentActivity.getSupportFragmentManager();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragManager = fragmentActivity.getSupportFragmentManager(); //If using fragments from support v4

        //android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        //supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        //Google_Map = supportMapFragment.getMap();



        //MapFragment mapFragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment);
        SupportMapFragment mapFragment = (SupportMapFragment)fragManager.findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMyLocationEnabled(true);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }
    @Override
    public void onLocationChanged(Location location) {

        //place marker at current position
        //mGoogleMap.clear();
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currLocationMarker = mGoogleMap.addMarker(markerOptions);

        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(14).build();

        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        //If you only need one location, unregister the listener
        //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    }

    @Override
    public void OnConnected(){}



}
*/