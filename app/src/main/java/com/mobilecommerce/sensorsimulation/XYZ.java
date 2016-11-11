/*package com.mobilecommerce.sensorsimulation;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class XYZ extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener  {

    private LocationRequest lr;
    private LocationRequest  lc;
    MapFragment mapFragment;
    ImageView iv;
    private static View view;

    public XYZ() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

        try {
            view = inflater.inflate(R.layout.fragment_xyz, container,
                    false);

            mapFragment = ((MapFragment) this.getActivity()
                    .getFragmentManager().findFragmentById(R.id.map));
            iv = (ImageView) view.findViewById(R.id.iv);

            map = mapFragment.getMap();
            map.getUiSettings().setAllGesturesEnabled(false);
            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(false);

            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(getActivity(), "Google Play Services missing !",
                    Toast.LENGTH_LONG).show();
        } catch (InflateException e) {
            Toast.makeText(getActivity(), "Problems inflating the view !",
                    Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {
            Toast.makeText(getActivity(), "Google Play Services missing !",
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lr = LocationRequest.create();
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        lc = new LocationRequest (this.getActivity().getApplicationContext(),
                this, this);
        lc.connect();
    }

    @Override
    public void onLocationChanged(Location l2) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                new LatLng(l2.getLatitude(), l2.getLongitude()), 15);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        lc.requestLocationUpdates(lr, this);

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
}
*/