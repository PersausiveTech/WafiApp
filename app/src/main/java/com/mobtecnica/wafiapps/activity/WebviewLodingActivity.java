package com.mobtecnica.wafiapps.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.Constants;

public class WebviewLodingActivity extends BaseActivity {

    int id = 0;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_loding);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("ID");

        loadWeb(id);
    }

    private void loadWeb(int id) {
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        });
        if (id == 1) {
            webView.loadUrl(Constants.WASH_URL);

        } else if (id == 2) {
            webView.loadUrl(Constants.EATS_URL);

        }
    }
}
