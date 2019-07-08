package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.FoodOnlineActivity;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.utils.BaseFragment;

/**
 * Created by SIby on 04-01-2017.
 */

public class ServicesFragment extends BaseFragment implements View.OnClickListener {
    View rootview;
    android.support.v7.widget.Toolbar toolbar;
    private RelativeLayout rl_food;
    private RelativeLayout rl_laundry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_services, container, false);
        initialize();
        onClickListners();
        return rootview;
    }

    private void onClickListners() {
        rl_food.setOnClickListener(this);
        rl_laundry.setOnClickListener(this);
    }

    private void initialize() {
        rl_food = (RelativeLayout) rootview.findViewById(R.id.rl_food_online);
        rl_laundry = (RelativeLayout) rootview.findViewById(R.id.rl_laundry);
        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
                toolbar.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_food_online:
                Intent intent = new Intent(getActivity(), FoodOnlineActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_laundry:
                Intent intent1 = new Intent(getActivity(), LaundryActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
