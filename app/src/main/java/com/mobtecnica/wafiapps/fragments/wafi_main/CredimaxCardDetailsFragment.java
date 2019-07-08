package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mastercard.gateway.android.sdk.Gateway;
import com.mastercard.gateway.android.sdk.GatewayCallback;
import com.mastercard.gateway.android.sdk.GatewayMap;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.PaymentManager;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.checkout.credimaxsession.CredimaxSessionRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CredimaxCardDetailsFragment extends BaseFragment implements GatewayCallback {
    private static final String BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE = "BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE";
    private EditText etCardNumber,etExpireDate,etSecurityCode,etCardHolderName;
    private TextView tvSubTotal,tvContinue;
    private View v;
    int previousLength = 0;
    private String apiVersion,sessionId,sessionVersion,merchantId,orderNo;


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dismissLoadingDialog();
            if (intent.getAction().matches(BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE)) {
                if (intent.getBooleanExtra(PaymentManager.BROADCAST_OPC_CREDIMAX_COMPLETE_TRANSACTION_STATUS, false)) {
                    Fragment fragmentSuccess = new OrderSucccessFragment(orderNo);
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).addFragment(fragmentSuccess);
                    }else{
                        Fragment fragmentFailed = new OrderFailedFragment(orderNo);
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).addFragment(fragmentFailed);
                        }
                    }
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_credimax_card_details, container, false);
        IntentFilter intent = new IntentFilter();
        intent.addAction(PaymentManager.BROADCAST_OPC_COMPLETE_TRANSACTION_RESPONSE);
        addBroadcastListener(receiver, intent);

        if(getArguments()!=null){
            if(!TextUtils.isEmpty(getArguments().getString("apiVersion"))){
                apiVersion = getArguments().getString("apiVersion");
            }
            if(!TextUtils.isEmpty(getArguments().getString("sessionId"))){
                sessionId = getArguments().getString("sessionId");
            }
            if(!TextUtils.isEmpty(getArguments().getString("sessionVersion"))){
                sessionVersion = getArguments().getString("sessionVersion");
            }
            if(!TextUtils.isEmpty(getArguments().getString("merchantId"))){
                merchantId = getArguments().getString("merchantId");
            }
            if(!TextUtils.isEmpty(getArguments().getString("orderNo"))){
                orderNo = getArguments().getString("orderNo");
            }
        }

        initViews();
        return v;
    }

    private void setCardNumberFormat(){
        etCardNumber.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove all spacing char
                int pos = 0;
                while (true) {
                    if (pos >= s.length()) break;
                    if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                        s.delete(pos, pos + 1);
                    } else {
                        pos++;
                    }
                }

                // Insert char where needed.
                pos = 4;
                while (true) {
                    if (pos >= s.length()) break;
                    final char c = s.charAt(pos);
                    // Only if its a digit where there should be a space we insert a space
                    if ("0123456789".indexOf(c) >= 0) {
                        s.insert(pos, "" + space);
                    }
                    pos += 5;
                }
                int length = etCardNumber.getText().toString().trim().length();
                if(length==19){
                    etExpireDate.requestFocus();
                }
            }
        });

        etExpireDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                int length = etExpireDate.getText().toString().trim().length();
                if (previousLength <= length && length < 3) {
                    if(length>0) {
                        int month = Integer.parseInt(etExpireDate.getText().toString());
                        if (length == 1 && month >= 2) {
                            String autoFixStr = "0" + month + "/";
                            etExpireDate.setText(autoFixStr);
                            etExpireDate.setSelection(3);
                        } else if (length == 2 && month <= 12) {
                            String autoFixStr = etExpireDate.getText().toString() + "/";
                            etExpireDate.setText(autoFixStr);
                            etExpireDate.setSelection(3);
                        } else if (length == 2 && month > 12) {
                            etExpireDate.setText("1");
                            etExpireDate.setSelection(1);
                        }
                    }
                } else if (length == 5) {
                    etSecurityCode.requestFocus(); // auto move to next edittext
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousLength = etExpireDate.getText().toString().length();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        etSecurityCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) {
                int length = etSecurityCode.getText().toString().trim().length();
                if(length==3){
                    etCardHolderName.requestFocus();
                }
            }
        });
    }

    private void initViews() {
        etCardNumber = v.findViewById(R.id.etCardNumber);
        etExpireDate = v.findViewById(R.id.etExpireDate);
        etSecurityCode = v.findViewById(R.id.etSecurityCode);
        etCardHolderName = v.findViewById(R.id.etCardHolderName);
        tvSubTotal = v.findViewById(R.id.tvSubTotal);
        tvContinue = v.findViewById(R.id.tvContinue);
        setTitle("Card Details");
        setCardNumberFormat();
        tvContinue.setOnClickListener(v -> {
            updateCrediMaxSession();
        });
        tvSubTotal.setText(Utils.getCheckoutPrice(WebserviceRequestManager.getInstance().getGetCartResponse()));
    }

    private void updateCrediMaxSession(){
        Gateway gateway = new Gateway();
        gateway.setMerchantId(merchantId);
        gateway.setRegion(Gateway.Region.ASIA_PACIFIC);

        String NameOnCard = etCardHolderName.getText().toString().trim();
        String cardNumber = etCardNumber.getText().toString().trim();
        String securityCode = etSecurityCode.getText().toString().trim();

        String expiryMM="";
        if(etExpireDate.length()>=2)
            expiryMM = etExpireDate.getText().toString().substring(0,2);

        String expiryYY="";
        if(etExpireDate.length()>=5)
            expiryYY = etExpireDate.getText().toString().substring(3,5);

        GatewayMap request = new GatewayMap()
                .set("sourceOfFunds.provided.card.nameOnCard",NameOnCard )
                .set("sourceOfFunds.provided.card.number", cardNumber.replaceAll(" ",""))
                .set("sourceOfFunds.provided.card.securityCode", securityCode)
                .set("sourceOfFunds.provided.card.expiry.month", expiryMM)
                .set("sourceOfFunds.provided.card.expiry.year", expiryYY);

        gateway.updateSession(sessionId, apiVersion, request, this);
    }

    @Override
    public void onSuccess(GatewayMap response) {
        Log.e("gateway response",new Gson().toJson(response));
        showLoadingDialog();
        try {
            CredimaxSessionRequest methodRequest = new CredimaxSessionRequest();
            methodRequest.setApiToken("fd2cdf71-3fc9-40b5-9638-a281e39d6b2c");
            methodRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            methodRequest.setOrderId(orderNo);
            methodRequest.setSessionId(response.get("session").toString());
            String json = new Gson().toJson(methodRequest);
            ObjectFactory.getInstance().getPaymentManager(getActivity()).opcCompleteTransaction(json);
        }catch (Exception e){
            Utils.makeSnackBar(tvContinue, R.string.error_occured, Snackbar.LENGTH_LONG);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("gateway response",new Gson().toJson(throwable));
    }
}
