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
public class SecondFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView2);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {

                    case R.id.murmanskButton:
                        updateImage(R.drawable.murmansk);
                        mAnswer = 0;
                        break;
                    case R.id.norilskButton:
                        updateImage(R.drawable.norilsk);
                        mAnswer = 0;
                        break;
                    case R.id.verkhoyanskButton:
                        updateImage(R.drawable.verkhoyansk);
                        mAnswer = 1;
                        break;
                    default:
                        break;
                }
            }
        });
        updateImage(R.drawable.murmansk);
        Button button = (Button) view.findViewById(R.id.nextQuestionButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThirdFragment thirdFragment = new ThirdFragment();

                Bundle bundle = new Bundle();
                bundle.putInt(FirstFragment.SCORE_KEY, mAnswer + getArguments().getInt(FirstFragment.SCORE_KEY));
                thirdFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, thirdFragment);
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
