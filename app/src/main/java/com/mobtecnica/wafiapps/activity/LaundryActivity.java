package com.mobtecnica.wafiapps.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.laundryHome.LaundryCartFragment;
import com.mobtecnica.wafiapps.fragments.laundryHome.LaundrySelectServiceFragment;
import com.mobtecnica.wafiapps.manager.LaundryManager;
import com.mobtecnica.wafiapps.manager.NetworkManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartRequest;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.LaundryCartResponse;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by SIby on 03-04-2017.
 */

public class LaundryActivity extends BaseActivity implements View.OnClickListener {
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv_laundry_cart;
    private Snackbar snackbar;
    private Dialog dialog;
    String count;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED)) {
                final View coordinatorLayoutView = findViewById(R.id.sb_root_product);
                if (ObjectFactory.getInstance().getNetworkManager(LaundryActivity.this).isDataAvailable()) {
                    showOrDismissSnackBar(true, coordinatorLayoutView);
                } else {
                    showOrDismissSnackBar(false, coordinatorLayoutView);
                }
            } else if (intent.getAction().matches(Constants.Login.BROADCAST_PROGRESS_WHEEL)) {
                if (intent.getBooleanExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false)) {
                    progressDialogshow();
                } else {
                    progressDialogDismiss();
                }
            } else if (intent.getAction().matches(LaundryManager.BROADCAST_LAUNDRY_RESPONSE)) {
                if (intent.getBooleanExtra(LaundryManager.BROADCAST_LAUNDRY_CHECKOUT_CART_STATUS, false)) {
                    count = intent.getStringExtra("cart_count");
                    id_quantity.setText(count);
                } else {

                }
            }
        }
    };
    private ImageView iv_delete;
    private ImageView iv_back_press;
    private TextView id_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.laundryStatusbar));
        }

        toolbar =  findViewById(R.id.toolbar_main);
        iv_delete =  findViewById(R.id.iv_delete);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.laundry);

        initializeViews();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED);
        filter.addAction(LaundryManager.BROADCAST_LAUNDRY_CART);
        filter.addAction(LaundryManager.BROADCAST_LAUNDRY_RESPONSE);
        filter.addAction(LaundryManager.BROADCAST_LAUNDRY_CART_STATUS);
        filter.addAction(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        setBroadcastReceiver(receiver, filter);
        getLaundryCart();
        navigateToLaundrySelectFragement();
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    public void getLaundryCart() {
        id_quantity = findViewById(R.id.id_quantity);
        LaundryCartRequest request = new LaundryCartRequest();
        request.setApiToken(Constants.API_TOKEN_LAUNDRY);
        request.setEmailID(ObjectFactory.getInstance().getAppPreference(this).getEmailId());
        ObjectFactory.getInstance().getLaundryManager(this).getCartCount(request);

//        getCartCount(request);
    }


    private void initializeViews() {
        iv_laundry_cart = findViewById(R.id.iv_laundry_cart);
        iv_back_press = findViewById(R.id.iv_back_press);
        iv_laundry_cart.setOnClickListener(this);
        iv_back_press.setOnClickListener(this);
    }

    private void navigateToLaundrySelectFragement() {
        Fragment fragment = new LaundrySelectServiceFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    @Override
    protected void onStop() {
        showOrDismissSnackBar(true, null);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().show();
        View coordinatorLayoutView = findViewById(R.id.sb_root);
        showOrDismissSnackBar(ObjectFactory.getInstance().getNetworkManager(LaundryActivity.this).isDataAvailable(), coordinatorLayoutView);
    }

    private void showOrDismissSnackBar(boolean dismiss, View viewIfNoData) {
        if (dismiss) {
            if (snackbar != null) {
                snackbar.dismiss();
                snackbar = null;
            }
        } else {
            try {
                snackbar = Snackbar
                        .make(viewIfNoData, R.string.firewall_blocked, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.snack_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.getView().bringToFront();
                snackbar.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void progressDialogshow() {
        if (dialog == null) {
            dialog = new Dialog(LaundryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_progressbar);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    public void progressDialogDismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            finish();
        } else
            super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_laundry_cart:
                navigateToLaundryCartFragment();
                break;
            case R.id.iv_back_press:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    private void navigateToLaundryCartFragment() {
        Fragment fragment = new LaundryCartFragment();
        addFragment(fragment);
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .add(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(Utils.getTagForFragment(fragment))
                .commit();
    }


    public void clearFragments() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

    }
}
