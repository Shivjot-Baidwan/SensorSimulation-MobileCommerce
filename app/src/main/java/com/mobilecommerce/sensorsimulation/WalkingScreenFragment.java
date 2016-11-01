/*
Authors: Venus Pathak - 7972526
         Shivjot Baidwan - 8028412
*/
package com.mobilecommerce.sensorsimulation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class WalkingScreenFragment extends Fragment {

    public WalkingScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walking_screen, container, false);
    }
}
