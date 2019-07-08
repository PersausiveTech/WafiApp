package com.mobtecnica.wafiapps.manager;

import android.content.Context;

import com.mobtecnica.wafiapps.utils.AppPreference;


public class ObjectFactory {

    private static ObjectFactory instance = null;

    private NetworkManager networkManager = null;
    private RestClient restClient = null;
    private AuthManager authManager = null;
    private AppPreference appPreference = null;
    private ProfileManager profileManager = null;
    private HomeManager homeManager = null;
    private LaundryManager laundryManager = null;
    private PaymentManager paymentManager = null;
    private WafiEatsManager wafiEatsManager = null;


    protected ObjectFactory() {

    }

    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    public RestClient getRestClient(Context context) {
        if (restClient == null) {
            restClient = new RestClient(context);
        } else {
            restClient.updateContext(context);
        }
        return restClient;
    }

    public PaymentManager getPaymentManager(Context context) {
        if (paymentManager == null) {
            paymentManager = new PaymentManager(context);
        } else {
            paymentManager.updateContext(context);
        }
        return paymentManager;
    }

    public WafiEatsManager getWafiEatsManager(Context context) {
        if (wafiEatsManager == null) {
            wafiEatsManager = new WafiEatsManager(context);
        } else {
            wafiEatsManager.updateContext(context);
        }
        return wafiEatsManager;
    }


    public LaundryManager getLaundryManager(Context context) {
        if (laundryManager == null) {
            laundryManager = new LaundryManager(context);
        } else {
            laundryManager.updateContext(context);
        }
        return laundryManager;
    }


    public NetworkManager getNetworkManager(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(context);
        } else {
            networkManager.updateContext(context);
        }
        return networkManager;
    }

    public AuthManager geLoginManager(Context context) {
        if (authManager == null) {
            authManager = new AuthManager(context);

        } else {
            authManager.updateContext(context);
        }
        return authManager;
    }

    public static boolean isDestroyed() {
        return instance == null;
    }

    public AppPreference getAppPreference(Context context) {
        if (appPreference == null) {
            appPreference = new AppPreference(context);

        } else {
            appPreference.updateContext(context);
        }
        return appPreference;
    }

    public ProfileManager getProfileManager(Context context) {
        if (profileManager == null) {
            profileManager = new ProfileManager(context);

        } else {
            profileManager.updateContext(context);
        }
        return profileManager;
    }

    public HomeManager getHomeManager(Context context) {
        if (homeManager == null) {
            homeManager = new HomeManager(context);

        } else {
            homeManager.updateContext(context);
        }
        return homeManager;
    }

    public AuthManager getAuthManager(Context context) {
        if (authManager == null) {
            authManager = new AuthManager(context);

        } else {
            authManager.updateContext(context);
        }
        return authManager;
    }

}