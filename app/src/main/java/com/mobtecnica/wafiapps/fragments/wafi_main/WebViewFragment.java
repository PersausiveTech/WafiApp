package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.utils.BaseFragment;

/**
 * Created by siby on 12-Jun-17.
 */

public class WebViewFragment extends BaseFragment {
    View rootView;
    android.support.v7.widget.Toolbar toolbar;

    int id = 0;
    private WebView webView;

    public WebViewFragment() {
    }

    @SuppressLint("ValidFragment")
    public WebViewFragment(int id) {
        this.id = id;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_load_webview, container, false);
        initViews();
        loadWebView();
        return rootView;
    }

    private void loadWebView() {
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript


        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
        });
        if (id == 1) {
            webView.loadUrl("https://www.wafiwash.com/");

        } else if (id == 2) {
            webView.loadUrl("https://wafieats.com/");

        }
    }

    private void initViews() {
        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
        webView = (WebView) rootView.findViewById(R.id.web_view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
                toolbar.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
            toolbar.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
