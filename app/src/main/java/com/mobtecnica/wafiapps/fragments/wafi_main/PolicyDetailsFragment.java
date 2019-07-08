package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.topicContent.Data;
import com.mobtecnica.wafiapps.model.topicContent.GetTopicContentResponse;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PolicyDetailsFragment extends BaseFragment {

    GetTopicContentResponse getTopicContentResponse;
    Data data;
    TextView tv_title1;
    View v;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_TOPIC_CONTENT_RESPONSE)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS, false)) {

                    getTopicContentResponse = ObjectFactory.getInstance().getHomeManager(getActivity()).getGetTopicContentData();
                    if (getTopicContentResponse.getData().getSystemName().equalsIgnoreCase(Constants.SHIPPING_INFO))
                        setData();

                } else {
                    Utils.makeSnackBar(v, getString(R.string.error_try_again),Snackbar.LENGTH_LONG);
                }
            }
        }
    };

    public static PolicyDetailsFragment newInstance() {
        PolicyDetailsFragment f = new PolicyDetailsFragment();
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_first, container, false);
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_TOPIC_CONTENT_RESPONSE);
        addBroadcastListener(receiver, intent);


        initViews();
        fetchdata();
        setData();
        return v;


    }

    private void setData() {
        if (getTopicContentResponse != null) {
            data = getTopicContentResponse.getData();
//            tv_title.setText(data.getTitle());
            tv_title1.setText(Html.fromHtml(data.getBody()));
        }
    }

    private void initViews() {
        tv_title1 = (TextView) v.findViewById(R.id.tv_title1);
    }

    private void fetchdata() {
        String ApiToken = Constants.API_TOKEN;
        String TopicSystemName = Constants.SHIPPING_INFO;
        ObjectFactory.getInstance().getHomeManager(getActivity()).apiGetTopicContent(ApiToken, TopicSystemName);
    }
}
