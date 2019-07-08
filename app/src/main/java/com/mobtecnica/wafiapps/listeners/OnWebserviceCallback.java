package com.mobtecnica.wafiapps.listeners;

import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;

public interface OnWebserviceCallback {
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType);

    public void onFailure(String message);

    public void onCancel(Object result);

}
