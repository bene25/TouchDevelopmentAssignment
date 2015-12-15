package com.example.aleksandr.touchdevelopmentassignment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.aleksandr.touchdevelopmentassignment.CircleTransformation;
import com.example.aleksandr.touchdevelopmentassignment.R;
import com.squareup.picasso.Picasso;

/**
 * Created by aleksandr on 15.12.15.
 */
public class FirstFragment extends Fragment {

    public static final String SCORE_KEY = "total_score";

    private ImageView mImageView;

    private int mAnswer = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {

                    case R.id.kazbekButton:
                        updateImage(R.drawable.kazbek);
                        mAnswer = 0;
                        break;
                    case R.id.elbrusButton:
                        updateImage(R.drawable.elbrus);
                        mAnswer = 1;
                        break;
                    case R.id.pikPushkinaButton:
                        updateImage(R.drawable.pik_pushkina);
                        mAnswer = 0;
                        break;
                    default:
                        break;
                }
            }
        });
        updateImage(R.drawable.kazbek);
        Button button = (Button) view.findViewById(R.id.nextQuestionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment secondFragment = new SecondFragment();

                Bundle bundle = new Bundle();
                bundle.putInt(SCORE_KEY, mAnswer);
                secondFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, secondFragment);
                transaction.commit();
            }
        });
        return view;
    }

    private void updateImage(Integer resId) {
        Picasso.with(getContext())
                .load(resId)
                .placeholder(R.drawable.progress_animation)
                .transform(new CircleTransformation())
                .resize(400, 400)
                .into(mImageView);

    }
}
