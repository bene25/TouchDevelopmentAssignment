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
public class ThirdFragment extends Fragment {

    private ImageView mImageView;

    private int mAnswer = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView3);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup3);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {

                    case R.id.sakhalinButton:
                        updateImage(R.drawable.sakhalin);
                        mAnswer = 1;
                        break;
                    case R.id.yuzhnuyButton:
                        updateImage(R.drawable.yuzhnuy);
                        mAnswer = 0;
                        break;
                    case R.id.severnuyButton:
                        updateImage(R.drawable.severnuy);
                        mAnswer = 0;
                        break;
                    default:
                        break;
                }
            }
        });
        updateImage(R.drawable.sakhalin);
        Button button = (Button) view.findViewById(R.id.resultsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultFragment resultFragment = new ResultFragment();

                Bundle bundle = new Bundle();
                bundle.putInt(FirstFragment.SCORE_KEY, mAnswer + getArguments().getInt(FirstFragment.SCORE_KEY));
                resultFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, resultFragment);
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
