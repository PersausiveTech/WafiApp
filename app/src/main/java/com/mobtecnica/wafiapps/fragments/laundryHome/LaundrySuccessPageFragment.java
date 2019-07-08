package com.mobtecnica.wafiapps.fragments.laundryHome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.utils.BaseFragment;

/**
 * Created by siby on 27-Jun-17.
 */

public class LaundrySuccessPageFragment extends BaseFragment implements View.OnClickListener {
    View rootView;
    private AppCompatButton btn_ok;
    private ImageView iv_back_success;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_laundry_success, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btn_ok = (AppCompatButton) rootView.findViewById(R.id.btn_ok);
        iv_back_success = (ImageView) rootView.findViewById(R.id.iv_back_success);
        btn_ok.setOnClickListener(this);
        iv_back_success.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                if (getActivity() instanceof LaundryActivity) {
                    ((LaundryActivity) getActivity()).clearFragments();
                    ((LaundryActivity) getActivity()).getLaundryCart();
                }
                break;
            case R.id.iv_back_success:
                if (getActivity() instanceof LaundryActivity) {
                    ((LaundryActivity) getActivity()).clearFragments();
                    ((LaundryActivity) getActivity()).getLaundryCart();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}
