package com.example.sbb;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedBack_one extends Fragment {

    private TextView textView1, textView2;

    public FeedBack_one() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_feed_back_one, container, false);

        textView1 = view.findViewById(R.id.sbb);
        textView2 = view.findViewById(R.id.sss);
        return view;
    }

}
