package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class ThirdFragment extends BaseFragment {

    GetTopicContentResponse getTopicContentResponse;
    Data data;
    TextView tv_title3;
    View v;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_TOPIC_CONTENT_RESPONSE)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_TOPIC_CONTENT_RESPONSE_STATUS, false)) {

                    getTopicContentResponse = ObjectFactory.getInstance().getHomeManager(getActivity()).getGetTopicContentData();
                    if (getTopicContentResponse.getData().getSystemName().equalsIgnoreCase(Constants.PRIVACY_INFO))
                        setData();

                } else {
                    Utils.makeSnackBar(v, R.string.error_try_again, Snackbar.LENGTH_LONG);
                }
            }
        }
    };

    public static ThirdFragment newInstance() {
        ThirdFragment f = new ThirdFragment();
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_third, container, false);
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
            tv_title3.setText(Html.fromHtml(data.getBody()));

            SpannableString ss = new SpannableString(tv_title3.getText().toString());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    Uri uri = Uri.parse("https://www.wafiapps.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    try{
                        startActivity(Intent.createChooser(intent, null));
                    }catch (android.content.ActivityNotFoundException ex){

                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true);
                }
            };
//r
            ss.setSpan(clickableSpan, 119, 142, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_title3.setText(ss);
            tv_title3.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void initViews() {
        tv_title3 = (TextView) v.findViewById(R.id.tv_title3);
    }

    private void fetchdata() {
        String ApiToken = Constants.API_TOKEN;
        String TopicSystemName = Constants.PRIVACY_INFO;
        ObjectFactory.getInstance().getHomeManager(getActivity()).apiGetTopicContent(ApiToken, TopicSystemName);
    }
}
