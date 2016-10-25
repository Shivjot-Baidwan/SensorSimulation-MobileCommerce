package com.mobilecommerce.sensorsimulation;

import android.graphics.Typeface;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;


public class WelcomeScreenFragment extends Fragment {

    public WelcomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View textView = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        /*
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        View fragmentLayout = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        TextView dateTextView = (TextView) fragmentLayout.findViewById(R.id.dateTextView);

        dateTextView.setText(currentDateTimeString);

        */



        return textView;
    }

}
