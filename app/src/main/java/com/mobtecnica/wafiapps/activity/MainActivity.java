package com.mobtecnica.wafiapps.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.CustomButtonToolbarRight;
import com.mobtecnica.wafiapps.customViews.NotificationCreaterAsyncTask;
import com.mobtecnica.wafiapps.fragments.wafi_main.AddNewAddressFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.AddressListFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.CartFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.CategoriesFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ChangePasswordFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.CheckOutFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ContactUsFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.HomeFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.MainFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.MyOrdersFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.OrderFailedFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.OrderSucccessFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProductDetailedViewFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProductListFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProductsInManufacturerFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProfileFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.SearchResultListingFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.SelectAddressFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.WishListFragment;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.NetworkManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.getCartCount.GetCartCountRequest;
import com.mobtecnica.wafiapps.model.notification.AppSubscriber;
import com.mobtecnica.wafiapps.model.notification.NotificationRegistrationRequest;
import com.mobtecnica.wafiapps.model.profile.cusomerDetails.CustomerPersonalDetailsRequest;
import com.mobtecnica.wafiapps.utils.BaseActivity;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.List;


public class MainActivity extends BaseActivity {

    android.support.v7.widget.Toolbar toolbar;
    CustomButtonToolbarRight custom_button_toolbar_right;
    AppCompatTextView tv_header;
    private boolean doubleBackToExitPressedOnce = false;
    private Snackbar snackbar;
    private Dialog dialog;
    private ImageView iv_search;
    private EditText et_search;
    private LinearLayout toolbar_search;
    private AccountHeader headerResult = null;
    private Drawer drawer = null;
    private MainFragment mainFragment;
    private Fragment currentTempFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED)) {
                final View coordinatorLayoutView = findViewById(R.id.sb_root);
                if (ObjectFactory.getInstance().getNetworkManager(MainActivity.this).isDataAvailable()) {
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
            } else if (intent.getAction().matches(HomeManager.BROADCAST_HIDE_SEARCH)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_HIDE_SEARCH_STATUS, false)) {
                    String st = intent.getStringExtra(HomeManager.BROADCAST_TITTLE_TEXT);
                    hideSearch(st);

                } else {
                    String st = intent.getStringExtra(HomeManager.BROADCAST_TITTLE_TEXT);
                    viewSearch(st);
                }
            }
        }
    };

    /**
     * set title in toolbar
     *
     * @param title title name
     */
    private void setToolbarTitle(String title) {
        if (tv_header != null && !TextUtils.isEmpty(title)) {
            tv_header.setText(Html.fromHtml(title));
        }
    }

    private void setToolbarEmptyTitle() {
        if (tv_header != null) {
            tv_header.setText("");
        }
    }

    public void viewSearch(String st) {
        toolbar_search.setVisibility(View.VISIBLE);
        tv_header.setText(st.toString());
        toolbar.setVisibility(View.VISIBLE);

    }

    public void hideSearch(String st) {
        toolbar_search.setVisibility(View.GONE);
        tv_header.setText(st.toString());
        toolbar.setVisibility(View.VISIBLE);
    }


    private void navigateToSearchProductListingFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.sb_root);
        if (currentFragment instanceof SearchResultListingFragment) {
            getSupportFragmentManager().popBackStack();
        }
        SearchResultListingFragment fragment = new SearchResultListingFragment();
        fragment.setTitle(et_search.getText().toString().trim());
        addFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(NetworkManager.BROADCAST_DATA_AVAILABILITY_UPDATED);
        filter.addAction(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        filter.addAction(HomeManager.BROADCAST_SEARCH);
        filter.addAction(HomeManager.BROADCAST_HIDE_SEARCH);
        filter.addAction(HomeManager.BROADCAST_TITTLE);
        filter.addAction(HomeManager.BROADCAST_UPDATE);
        setBroadcastReceiver(receiver, filter);
        registerFCMTokenToServer();

        toolbar_search = findViewById(R.id.toolbar_search);
        toolbar = findViewById(R.id.toolbar_main);
        tv_header = findViewById(R.id.tv_header);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.toolbarColor));
        }
        CustomerPersonalDetailsRequest request = new CustomerPersonalDetailsRequest();
        request.setApiToken(Constants.API_TOKEN);
        request.setGuid(ObjectFactory.getInstance().getAppPreference(MainActivity.this).getGuid());
        ObjectFactory.getInstance().getHomeManager(MainActivity.this).getCustomerDetails(request);
        initialize();
        initSideMenu(savedInstanceState);
        searchProduct();
        navigateToViewPagerFragment();
        initToolbar();
        enableBackButtonInToolbar(true);
        enableBackButtonInToolbar(false);
        loadCartCount();
        checkDeepLink(getIntent());
    }

    private void checkDeepLink(Intent intent) {
        if (intent.hasExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK)) {
            String deep_link = intent.getStringExtra(NotificationCreaterAsyncTask.KEY_DEEP_LINK);
            if (!TextUtils.isEmpty(deep_link)) {
                deepLink(deep_link);
            }
        } else {
            Uri intentUri = intent.getData();
            if (intentUri != null) {
                try {
                    String uri = String.valueOf(intentUri);
                    if (!TextUtils.isEmpty(uri)) {
                        deepLink(uri);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkDeepLink(intent);
    }


    private void deepLink(String uri) {
        if (uri.toLowerCase().contains("categoryid=")) {
            String[] category_id = uri.toLowerCase().split("categoryid=");
            String categoryId = category_id[1];
            setToolbarEmptyTitle();
            navigateToProductListingFragment("",categoryId);
        }
        else if (uri.toLowerCase().contains("brandid=")) {
            String[] brand_id = uri.toLowerCase().split("brandid=");
            String brandId = brand_id[1];
            setToolbarEmptyTitle();
            navigateToManufacturerProductlistFragment("",brandId);
        }
        else if (uri.toLowerCase().contains("manufacturerid=")) {
            String[] manufacturer_id = uri.toLowerCase().split("manufacturerid=");
            String manufacturerId = manufacturer_id[1];
            setToolbarEmptyTitle();
            navigateToManufacturerProductlistFragment("",manufacturerId);
        }
        else if (uri.toLowerCase().contains("id=")) {
            String[] product_id = uri.toLowerCase().split("id=");
            String productID = product_id[1];
            setToolbarEmptyTitle();
            navigateToProductDetailFragment("",productID);
        }
    }

    private void navigateToManufacturerProductlistFragment(String title, String id) {
        ProductsInManufacturerFragment fragment = new ProductsInManufacturerFragment();
        fragment.setManufacturerId(id);
        fragment.setTitle(title);
        addFragment(fragment);
    }
    private void navigateToProductDetailFragment(String title,String productId) {
        ObjectFactory.getInstance().getAppPreference(getApplicationContext()).setProductId(productId);
        ProductDetailedViewFragment fragment = new ProductDetailedViewFragment();
        fragment.setTitle(title);
        addFragment(fragment);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        // Initialize ActionBarDrawerToggle, which will control toggle of hamburger.
        // You set the values of R.string.open and R.string.close accordingly.
        // Also, you can implement drawer toggle listener if you want.
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer.getDrawerLayout(), toolbar, R.string.open, R.string.close);
        // Setting the actionbarToggle to drawer layout
        drawer.getDrawerLayout().setDrawerListener(mDrawerToggle);
        // Calling sync state is necessary to show your hamburger icon...
        // or so I hear. Doesn't hurt including it even if you find it works
        // without it on your test device(s)
        mDrawerToggle.syncState();

    }

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     * <p>
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    private void enableBackButtonInToolbar(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });
//
                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
            // Remove the/any drawer toggle listener
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }
    }

    private void initSideMenu(Bundle savedInstanceState) {
        // Create a few sample profile
        String emailId = ObjectFactory.getInstance().getAppPreference(MainActivity.this).getEmailId();
        final IProfile profile = new ProfileDrawerItem().withName("").withEmail(emailId).withIcon(R.drawable.wafi_logo);

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withTypeface(Utils.getTypefaceLatoRegular(this))
                .withHeaderBackground(R.drawable.side_nav_bar)
                .addProfiles(
                        profile
                ).withSelectionListEnabledForSingleProfile(false)
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.home).withIdentifier(1).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        //new PrimaryDrawerItem().withName(R.string.fresh_fish).withIdentifier(2).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        /*new PrimaryDrawerItem().withName(R.string.nav_laundry).withIdentifier(3).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),*/
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.nav_my_orders).withIdentifier(4).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        new PrimaryDrawerItem().withName(R.string.nav_my_wishlist).withIdentifier(5).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        new PrimaryDrawerItem().withName(R.string.nav_my_account).withIdentifier(6).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        new PrimaryDrawerItem().withName(R.string.nav_my_cart).withIdentifier(7).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.share_app).withIdentifier(8).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground)),
                        new PrimaryDrawerItem().withName(R.string.contact_us).withIdentifier(9).withTypeface(Utils.getTypefaceLatoRegular(this)).withSelectedTextColor(getResources().getColor(R.color.colorAccent)).withSelectedColor(getResources().getColor(R.color.lightRedBackground))
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawerItem != null) {
                    if (drawerItem.getIdentifier() == 1) {
                        navigateToViewPagerFragment(0);
                    } else if (drawerItem.getIdentifier() == 2) {
                        try {
                            navigateToProductListingFragment(getResources().getString(R.string.fresh_fish), getString(R.string.fish_id));
                        } catch (Exception e) {

                        }
                    } else if (drawerItem.getIdentifier() == 3) {
                        navigateToLaundryActivity();
                    } else if (drawerItem.getIdentifier() == 4) {
                        navigateToMyOrdersFragment();
                    } else if (drawerItem.getIdentifier() == 5) {
                        navigateToViewPagerFragment(2);
                    } else if (drawerItem.getIdentifier() == 6) {
                        navigateToViewPagerFragment(3);
                    } else if (drawerItem.getIdentifier() == 7) {
                        navigateToCartFragment();
                    }else if (drawerItem.getIdentifier() == 8) {
                        shareApp();

                    }else if (drawerItem.getIdentifier() == 9) {
                        navigateToViewPagerFragment(4);
                    }
//                            startSupportActionMode(new ActionBarCallBack());

                }

                return false;
            }
        })
                .withSavedInstance(savedInstanceState)
                .build();

        // set the selection to the item with the identifier 5
        if (savedInstanceState == null) {
            drawer.setSelection(1, false);
        }
    }

    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_app_link));
        sendIntent.setType("text/plain");
        try {
            startActivity(Intent.createChooser(sendIntent, getString(R.string.send_email)));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = drawer.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void registerFCMTokenToServer() {
        if (ObjectFactory.getInstance().getAppPreference(MainActivity.this).getIsLoggedIn().equalsIgnoreCase("1")) {
            NotificationRegistrationRequest notificationRegistrationRequest = new NotificationRegistrationRequest();
            AppSubscriber appSubscriber = new AppSubscriber();
            notificationRegistrationRequest.setApitoken(Constants.API_TOKEN);
            appSubscriber.setActive(true);
            appSubscriber.setDeviceName(Utils.getDeviceName());
            String userId = ObjectFactory.getInstance().getAppPreference(MainActivity.this).getCustomerId();
            String token = FirebaseInstanceId.getInstance().getToken();
            String guid = ObjectFactory.getInstance().getAppPreference(MainActivity.this).getGuid();
            appSubscriber.setSubscriberToken(token);
            appSubscriber.setUserId(userId);
            appSubscriber.setUserGuid(guid);
            notificationRegistrationRequest.setAppSubscriber(appSubscriber);
            ObjectFactory.getInstance().getHomeManager(MainActivity.this).apiRegisterToken(notificationRegistrationRequest);
        }

    }


    public void loadCartCount() {
        GetCartCountRequest getCartCountRequest = new GetCartCountRequest(ObjectFactory.getInstance().getAppPreference(MainActivity.this).getGuid());
        OnWebserviceCallback onWebserviceCallback = new OnWebserviceCallback() {
            @Override
            public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
                if (result instanceof Integer) {
                    setCartCount((Integer) result);
                }
            }

            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onCancel(Object result) {
            }
        };
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(MainActivity.this).getApiService().getCartCount(getCartCountRequest), onWebserviceCallback, WebserviceRequestManager.RequestType.GET_CART_COUNT);
    }

    private void searchProduct() {
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setCursorVisible(true);
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_search.getText().toString().trim())) {
                    ObjectFactory.getInstance().getHomeManager(MainActivity.this).setSearchText(et_search.getText().toString().trim());
                    navigateToSearchProductListingFragment();
                }

            }
        });

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (!TextUtils.isEmpty(et_search.getText().toString().trim())) {
                        ObjectFactory.getInstance().getHomeManager(MainActivity.this).setSearchText(et_search.getText().toString().trim());
                        navigateToSearchProductListingFragment();
                    }

                }
                return false;
            }
        });
    }

    private void initialize() {
        et_search = (EditText) findViewById(R.id.et_search);
        iv_search = (ImageView) findViewById(R.id.iv_search_items);
        et_search.setTypeface(Utils.getTypefaceLatoRegular(this));
        custom_button_toolbar_right = (CustomButtonToolbarRight) findViewById(R.id.custom_button_toolbar_right);
        custom_button_toolbar_right.setCustomButtonToolbarRightOnclickListener(new CustomButtonToolbarRight.CustomButtonToolbarRightOnclickListener() {
            @Override
            public void onCartClick() {
                navigateToCartFragment();
            }

            @Override
            public void onDeleteClick() {
            }

            @Override
            public void onAddClick() {
                if (currentTempFragment != null) {
                    if (currentTempFragment instanceof AddressListFragment) {
                        ((AddressListFragment) currentTempFragment).navigateToAddAddressFragment();
                    } else if (currentTempFragment instanceof SelectAddressFragment) {
                        addFragment(new AddNewAddressFragment());
                    }
                }
            }

            @Override
            public void onSearchClick() {
                showSearchBar(toolbar_search.getVisibility() != View.VISIBLE);
            }
        });
    }

    public void navigateToViewPagerFragment() {
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sb_root, mainFragment, "MAIN_FRAGMENT")
                .commit();
    }

    public void navigateToViewPagerFragment(final int page) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if (mainFragment instanceof MainFragment) {
                    ((MainFragment) mainFragment).setViewPagerPage(page);
                }
            }
        });
    }



    @Override
    protected void onStop() {
        showOrDismissSnackBar(true, null);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        View coordinatorLayoutView = findViewById(R.id.sb_root);
        showOrDismissSnackBar(ObjectFactory.getInstance().getNetworkManager(MainActivity.this).isDataAvailable(), coordinatorLayoutView);
        if (currentTempFragment != null) {
            setCurrentFragmentToolbar(currentTempFragment);
            setDrawerItemSelection(currentTempFragment);
        }
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

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.sb_root);
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            if(currentFragment instanceof MainFragment && ((MainFragment) mainFragment).getViewPagerPage()!=0){

                loadCartCount();
                navigateToViewPagerFragment(0);

            }else if (backStackEntryCount == 0) {
                if (doubleBackToExitPressedOnce) {
                    MainActivity.this.finish();
                } else {
                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(MainActivity.this, "Press again to exit app.", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;


                        }
                    }, 2000);
                }
            } else if (currentFragment instanceof OrderSucccessFragment) {
                loadCartCount();
                navigateToViewPagerFragment(0);
            } else if (currentFragment instanceof OrderFailedFragment) {
                loadCartCount();
                navigateToViewPagerFragment(0);
            } else {
                super.onBackPressed();
            }
        }
    }

    public void progressDialogshow() {
        if (dialog == null) {
            dialog = new Dialog(MainActivity.this);
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


    private void navigateToCartFragment() {
        Fragment fragment = new CartFragment();
        addFragment(fragment);
    }

    private void navigateToLaundryActivity() {
        Bundle bundle = new Bundle();
        //Add your data from getFactualResults method to bundle
        bundle.putInt("ID", 1);

        Intent intent = new Intent(MainActivity.this, LaundryActivity.class);
        startActivity(intent);
    }

    private void navigateToMyOrdersFragment() {
        Fragment fragment = new MyOrdersFragment();
        addFragment(fragment);
    }
    private void navigateToProductListingFragment(String names_deal, String id) {
        ObjectFactory.getInstance().getAppPreference(this).setCategoryId(id);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(names_deal);
        addFragment(fragment);
    }

    public void showDrawer(Boolean show) {
        if (show) {
            if (!drawer.isDrawerOpen())
                drawer.openDrawer();
        } else {
            if (!drawer.isDrawerOpen()) {
                drawer.closeDrawer();
            }
        }
    }

    /**
     * All fragment navigation moved to this page related to this Activity
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);
        fragmentManager.beginTransaction()
//                .replace(R.id.sb_root, fragment, Utils.getTagForFragment(fragment))
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .add(R.id.sb_root, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(Utils.getTagForFragment(fragment))
                .commitAllowingStateLoss();
    }

    FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.sb_root);
            if (currentFragment instanceof MainFragment) {
                if (mainFragment != null) {
                    currentFragment = mainFragment.getCurrentAdapterItem();
                    setCurrentFragmentToolbar(currentFragment);
                }
            } else {
                List<Fragment> list = getSupportFragmentManager().getFragments();
                if (list != null && !list.isEmpty() && list.size() > 0) {
                    currentFragment = list.get(list.size() - 1);
                    setCurrentFragmentToolbar(currentFragment);
                }
            }

        }
    };

    /**
     * @param fragment initialize and set the title, searchbar visibility
     */
    public void setCurrentFragmentToolbar(Fragment fragment) {
        setTempCurrentFragment(fragment);
        if (fragment instanceof BaseFragment) {
            showSearchBar(fragment instanceof HomeFragment);
            setToolbarTitle(((BaseFragment) fragment).getTitle());
            setToolbarRightButtons((BaseFragment) fragment);
            enableBackButtonInToolbar(!(fragment instanceof HomeFragment || fragment instanceof CategoriesFragment || fragment instanceof WishListFragment || fragment instanceof ProfileFragment || fragment instanceof ContactUsFragment));
            setDrawerItemSelection(fragment);

            if (fragment instanceof CartFragment) { //TODO Remove this when checkout page API is corrected
                ((CartFragment) fragment).updateViewsTemp();
            }
            if (fragment instanceof HomeFragment) {
                ((HomeFragment) fragment).updateCartItems();
            }
            if (fragment instanceof AddNewAddressFragment) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else if (fragment instanceof ChangePasswordFragment) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else if (fragment instanceof CheckOutFragment) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            }
        }
    }

    private void setDrawerItemSelection(Fragment fragment) {
        if (drawer != null) {
            if (fragment instanceof HomeFragment) {
                drawer.setSelection(1, false);//set HomeFragment selected
            } else if (fragment instanceof MyOrdersFragment) {
                drawer.setSelection(4, false);//set MyOrderFragment selected
            } else if (fragment instanceof ProductListFragment) {
                drawer.setSelection(2, false);//set MyOrderFragment selected
            } else if (fragment instanceof WishListFragment) {
                drawer.setSelection(5, false);//set WishListFragment selected
            } else if (fragment instanceof ProfileFragment) {
                drawer.setSelection(6, false);//set ProfileFragment selected
            } else if (fragment instanceof CartFragment) {
                drawer.setSelection(7, false);//set CartFragment selected
            }else if (fragment instanceof ContactUsFragment) {
                drawer.setSelection(9, false);//set CartFragment selected
            } else {
                drawer.setSelection(-1, false);//set nothing selected
            }
        }
    }

    /**
     * setting current fragment temporarily for communication
     *
     * @param fragment
     */
    private void setTempCurrentFragment(Fragment fragment) {
        //TODO need to change this with some other communication technique.
        this.currentTempFragment = fragment;
    }

    /**
     * set the buttons on the right side of the toolbar
     *
     * @param fragment
     */
    private void setToolbarRightButtons(BaseFragment fragment) {
        if (custom_button_toolbar_right != null) {
            custom_button_toolbar_right.refreshView(fragment.getButtonType());
            if (fragment instanceof HomeFragment) {
                custom_button_toolbar_right.hideSearch();
            }
        }
    }

    /**
     * on toolbar set the count of items added in cart
     *
     * @param cart_count
     */
    public void setCartCount(int cart_count) {
        if (custom_button_toolbar_right != null && cart_count > -1) {
            custom_button_toolbar_right.setCartOrderCount(cart_count);
        }
    }

    /**
     * show Searchbar
     *
     * @param show = true then show search bar else hide.
     */
    private void showSearchBar(boolean show) {
        if (toolbar_search != null) {
            toolbar_search.setVisibility(show ? View.VISIBLE : View.GONE);
            if (et_search != null) {
                et_search.setText("");
            }
        }
    }

}
