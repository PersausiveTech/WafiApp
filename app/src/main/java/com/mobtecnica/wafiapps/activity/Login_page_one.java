package com.mobtecnica.wafiapps.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.fragments.wafi_main.RegisterHomeFragment;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.NetworkManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

public class Login_page_one extends BaseActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED)) {
                final View coordinatorLayoutView = findViewById(R.id.sb_root);
                if (ObjectFactory.getInstance().getNetworkManager(Login_page_one.this).isDataAvailable()) {
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
    private Dialog dialog;
    private Snackbar snackbar;

    public void progressDialogshow() {
        if (dialog == null) {
            dialog = new Dialog(Login_page_one.this);
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
    private void showOrDismissSnackBar(boolean dismiss, View viewIfNoData) {
        if (dismiss) {
            if (snackbar != null) {
                snackbar.dismiss();
                snackbar = null;
            }
        } else {
            if (viewIfNoData!=null) {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_one);
        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED);
        filter.addAction(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        filter.addAction(HomeManager.BROADCAST_UPDATE);
        setBroadcastReceiver(receiver, filter);
        loadFrag();
    }

    private void loadFrag() {
        Fragment fragment = new RegisterHomeFragment();
        addFragment(fragment);
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            showExitDialog();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }


    private void showExitDialog() {
        {
            CustomAlertDialog customAlertDialog=   new CustomAlertDialog(getString(R.string.sure_to_exit),null, getString(R.string.no),getString(R.string.yes));
            customAlertDialog.setCancelable(false);
            customAlertDialog.setCustomAlertClickListener(new CustomAlertDialog.CustomAlertClickListener() {
                @Override
                public void onPositive(DialogFragment dialogFragment) {
                    System.exit(0);

                }

                @Override
                public void onNegative(DialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                }
            });
            customAlertDialog.show(getFragmentManager(),"Alert");
        }

    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, true);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .add(R.id.lp_ll_container, fragment, Utils.getTagForFragment(fragment));


        if (addToBackStack) {
            fragmentTransaction.addToBackStack(Utils.getTagForFragment(fragment));
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void onBack(View view) {
        onBackPressed();
    }
}
