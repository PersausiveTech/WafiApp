package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 13-Jun-17.
 */

public class OrderFailedFragment extends BaseFragment implements View.OnClickListener {
    String ordernum = "";
    private View rootView;
    private TextView tv_order_number;
    private AppCompatButton btn_continue;

    public OrderFailedFragment() {
    }

    @SuppressLint("ValidFragment")
    public OrderFailedFragment(String order_num) {
        this.ordernum = order_num;
        setButtonType(Utils.BUTTON_TYPE.EMPTY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_failure, container, false);
        initViews();
        onClickListner();
        return rootView;
    }

    private void onClickListner() {
        btn_continue.setOnClickListener(this);
    }

    private void initViews() {
        setTitle("");
        tv_order_number = (TextView) rootView.findViewById(R.id.tv_order_number);
        btn_continue = (AppCompatButton) rootView.findViewById(R.id.btn_continue);
        tv_order_number.setText(getString(R.string.order_number) + ordernum);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_continue:
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
                if (getActivity() instanceof MainActivity){
                    ((MainActivity) getActivity()).navigateToViewPagerFragment(0);
                    ((MainActivity) getActivity()).loadCartCount();;
                }
                break;
            default:
                break;
        }
    }
}
