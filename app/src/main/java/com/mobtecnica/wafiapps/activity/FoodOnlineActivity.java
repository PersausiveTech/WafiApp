package com.mobtecnica.wafiapps.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.foodHome.CartWafiEatsFragment;
import com.mobtecnica.wafiapps.fragments.foodHome.FoodHomeFragment;
import com.mobtecnica.wafiapps.manager.NetworkManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

public class FoodOnlineActivity extends BaseActivity implements View.OnClickListener {

    android.support.v7.widget.Toolbar toolbar;
    ImageView iv_laundry_cart;
    private Snackbar snackbar;
    private Dialog dialog;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED)) {
                final View coordinatorLayoutView = findViewById(R.id.sb_root_product);
                if (ObjectFactory.getInstance().getNetworkManager(FoodOnlineActivity.this).isDataAvailable()) {
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
            }
        }
    };
    private boolean doubleBackToExitPressedOnce = false;
    private ImageView iv_delete;
    private ImageView iv_back_press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_online);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.darkRed));
        }
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_main);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        setSupportActionBar(toolbar);
        initializeViews();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED);
        filter.addAction(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        setBroadcastReceiver(receiver, filter);
        int id = 0;
        String product = "0";

        navigateToLaundrySelectFragement();
    }

    private void initializeViews() {
        iv_laundry_cart = (ImageView) findViewById(R.id.iv_laundry_cart);
        iv_back_press = (ImageView) findViewById(R.id.iv_back_press);
        iv_laundry_cart.setOnClickListener(this);
        iv_back_press.setOnClickListener(this);
    }

    private void navigateToLaundrySelectFragement() {
        Fragment fragment = new FoodHomeFragment();
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
        showOrDismissSnackBar(ObjectFactory.getInstance().getNetworkManager(FoodOnlineActivity.this).isDataAvailable(), coordinatorLayoutView);
    }

    private void showOrDismissSnackBar(boolean dismiss, View viewIfNoData) {
        if (dismiss) {
            if (snackbar != null) {
                snackbar.dismiss();
                snackbar = null;
            }
        } else {
            snackbar = Snackbar
                    .make(viewIfNoData, R.string.firewall_blocked, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(R.string.snack_dismiss, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            Utils.changeSnackBarFont(snackbar);
        }
    }

    public void progressDialogshow() {
        if (dialog == null) {
            dialog = new Dialog(FoodOnlineActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_progressbar);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
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
        Fragment fragment = new CartWafiEatsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

}
