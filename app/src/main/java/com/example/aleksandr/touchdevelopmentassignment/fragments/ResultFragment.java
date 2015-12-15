package com.example.aleksandr.touchdevelopmentassignment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aleksandr.touchdevelopmentassignment.R;

/**
 * Created by aleksandr on 15.12.15.
 */
public class ResultFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView textView = (TextView) view.findViewById(R.id.scoreView);

        int questions = 3;
        String score = getString(R.string.score, getArguments().getInt(FirstFragment.SCORE_KEY), questions);
        textView.setText(score);

        Button button = (Button) view.findViewById(R.id.replayButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, firstFragment);
                transaction.commit();

            }
        });

        return view;
    }
}
