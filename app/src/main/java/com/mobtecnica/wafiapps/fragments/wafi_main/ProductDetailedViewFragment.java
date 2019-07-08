package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.ProductAttributesRvAdapter;
import com.mobtecnica.wafiapps.adapters.ProductImageViewPager;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.addToCart.AddToCartRequest;
import com.mobtecnica.wafiapps.model.cart.addToCart.CustomModelForAttributePick;
import com.mobtecnica.wafiapps.model.cart.addToCart.Model;
import com.mobtecnica.wafiapps.model.cart.addToCart.Product_attributesCart;
import com.mobtecnica.wafiapps.model.cart.addToCart.response.AddToCartResponse;
import com.mobtecnica.wafiapps.model.productDetails.PictureModels;
import com.mobtecnica.wafiapps.model.productDetails.ProductAttributes;
import com.mobtecnica.wafiapps.model.productDetails.ProductDetailsResponse;
import com.mobtecnica.wafiapps.model.productDetails.ProductManufacturers;
import com.mobtecnica.wafiapps.model.productDetails.ProductSpecifications;
import com.mobtecnica.wafiapps.model.productDetails.Values;
import com.mobtecnica.wafiapps.model.productDetails.productDetailsRequest.ProductDetailsRequest;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.Product_attributes;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.UpdateModel;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SIby on 20-02-2017.
 */

public class ProductDetailedViewFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {
    public int id_ = 0;
    ArrayList<Product_attributesCart> attributesCarts;
    List<Product_attributesCart> productAttributesCarts;
    LinearLayout ll_product;
    private View rootView;
    private ImageView manufacture_logo;
    private ImageView iv_back_products_detailed_view, iv_minus, iv_plus, iv_addto_favorite;
    private TextView tv_product_price;
    private TextView offer_tag_text;
    private TextView short_spec_text;
    private TextView brand_value;
    private TextView model_values;
    private TextView tv_product_name, tv_product_name_product_list1;
    private Button tv_add_to_cart_product_details;
    private TextView delivery_text;
    private TextView is_free_shipping;
    private TextView stock_availability;
    private EditText et_quantity;
    ArrayList<ProductAttributes> attributes = new ArrayList<>();
    private LinearLayout ll_product_spec;
    private LinearLayout delivery_time_layout;
    private LinearLayout model_layout;
    private LinearLayout brand_layout;
    private RelativeLayout ll_detailed_details;
    private ScrollView scroll_details;
    private ArrayList<ProductDetailsResponse> detailsResponses;
    private ProductImageViewPager productImageViewPager;
    private ViewPager viewPager_product_image;
    private LinearLayout ll_product_attributes;
    private ProductAttributesRvAdapter adapter;
    private String product_id = "";
    private ImageView iv_share;
    private UpdateModel updateModel;
    private Product_attributes[] product_attributes;
    private TextView description_tv, specification_tv;
    private TextView tv_old_product_price;
    private CardView cv_description;
    private LinearLayout offer_tag;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(HomeManager.BROADCAST_PRODUCT_DETAILS)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_PRODUCT_DETAILS_STATUS, false)) {
                    ll_detailed_details.setVisibility(View.VISIBLE);
                    setProductDetails();
                } else {
                    Utils.makeSnackBar(ll_product, getString(R.string.error_occured), Snackbar.LENGTH_LONG);

                    ll_detailed_details.setVisibility(View.GONE);
                }
            } else if (intent.getAction().matches(HomeManager.BROADCAST_ADD_TO_CART)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_ADD_TO_CART_STATUS, false)) {
                    if (id_ == 1) {
                        Utils.makeSnackBar(ll_product, intent.getStringExtra(HomeManager.BROADCAST_ADD_TO_CART_STATUS_MESSAGE), Snackbar.LENGTH_LONG);
                        id_ = 0;
                        getActivity().onBackPressed();

                    } else if (id_ == 5) {
                        id_ = 0;
                        navigateToCart();
                    } else {
                        Utils.makeSnackBar(ll_product, intent.getStringExtra(HomeManager.BROADCAST_ADD_TO_CART_STATUS_MESSAGE), Snackbar.LENGTH_LONG);
                        iv_addto_favorite.setImageResource(R.drawable.fav_red);
                    }
                } else {
                    Utils.makeSnackBar(ll_product, intent.getStringExtra(HomeManager.BROADCAST_ADD_TO_CART_STATUS_MESSAGE), Snackbar.LENGTH_LONG);
                }
            } else if (intent.getAction().matches(HomeManager.BROADCAST_UPDATE_FIELD)) {
                if (intent.getBooleanExtra(HomeManager.BROADCAST_UPDATE_FIELD_STATUS, false)) {
                    setProductDetails();
                }
            }
        }
    };
    private Button tv_buy_now_product_details;
    private WebView webView;

    private void navigateToCart() {
        Fragment fragment = new CartFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_product_detailed_view, container, false);
        initializeViews();
        onClickListners();
        return rootView;
    }

    private void onClickListners() {
        tv_add_to_cart_product_details.setOnClickListener(this);
        iv_minus.setOnClickListener(this);
        iv_plus.setOnClickListener(this);
        iv_addto_favorite.setOnClickListener(this);
        iv_back_products_detailed_view.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        tv_buy_now_product_details.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingDialog();
        apiCall();
    }

    private void apiCall() {
        product_id = ObjectFactory.getInstance().getAppPreference(getActivity()).getProductId();
        ProductDetailsRequest request = new ProductDetailsRequest();
        request.setApiToken(Constants.API_TOKEN);
        request.setProductId(ObjectFactory.getInstance().getAppPreference(getActivity()).getProductId());
        ObjectFactory.getInstance().getHomeManager(getActivity()).getProductDetails(request);
    }

    private void initializeViews() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideSearch("");
        }
        IntentFilter intent = new IntentFilter();
        intent.addAction(HomeManager.BROADCAST_PRODUCT_DETAILS);
        intent.addAction(HomeManager.BROADCAST_ADD_TO_CART);
        intent.addAction(HomeManager.BROADCAST_UPDATE_FIELD);
        addBroadcastListener(receiver, intent);

        ll_detailed_details = (RelativeLayout) rootView.findViewById(R.id.ll_detailed_details);
        brand_layout = (LinearLayout) rootView.findViewById(R.id.brand_layout);
        model_layout = (LinearLayout) rootView.findViewById(R.id.model_layout);
        manufacture_logo = (ImageView) rootView.findViewById(R.id.manufacture_logo);
        cv_description = (CardView) rootView.findViewById(R.id.cv_description);
        scroll_details = (ScrollView) rootView.findViewById(R.id.scroll_details);
        iv_back_products_detailed_view = (ImageView) rootView.findViewById(R.id.iv_back_products_detailed_view);
        tv_product_price = (TextView) rootView.findViewById(R.id.tv_product_price_detailed_view);
        is_free_shipping = (TextView) rootView.findViewById(R.id.is_free_shipping);
        tv_old_product_price = (TextView) rootView.findViewById(R.id.tv_old_product_price);
        stock_availability = (TextView) rootView.findViewById(R.id.stock_availability);
        ll_product_spec = (LinearLayout) rootView.findViewById(R.id.ll_product_spec);
        ll_product = (LinearLayout) rootView.findViewById(R.id.ll_product);
        ll_product_attributes = (LinearLayout) rootView.findViewById(R.id.ll_product_attributes);
        delivery_time_layout = (LinearLayout) rootView.findViewById(R.id.delivery_time_layout);
        tv_product_name = (TextView) rootView.findViewById(R.id.product_name);
        delivery_text = (TextView) rootView.findViewById(R.id.delivery_text);
        tv_product_name_product_list1 = (TextView) rootView.findViewById(R.id.tv_product_name_product_list1);
        tv_add_to_cart_product_details = (Button) rootView.findViewById(R.id.tv_add_to_cart_product_details);
        webView = (WebView) rootView.findViewById(R.id.web);
        short_spec_text = (TextView) rootView.findViewById(R.id.short_spec_text);
        brand_value = (TextView) rootView.findViewById(R.id.brand_value);
        model_values = (TextView) rootView.findViewById(R.id.model_value);
        offer_tag_text = (TextView) rootView.findViewById(R.id.offer_tag_text);
        iv_minus = (ImageView) rootView.findViewById(R.id.iv_minus);
        iv_addto_favorite = (ImageView) rootView.findViewById(R.id.iv_addto_favorite);
        iv_plus = (ImageView) rootView.findViewById(R.id.iv_plus);
        iv_share = (ImageView) rootView.findViewById(R.id.iv_share);
        et_quantity = (EditText) rootView.findViewById(R.id.et_quantity);
        viewPager_product_image = (ViewPager) rootView.findViewById(R.id.vp_product_image);
        description_tv = (TextView) rootView.findViewById(R.id.description_tv);
        specification_tv = (TextView) rootView.findViewById(R.id.specification_tv);
        tv_buy_now_product_details = (Button) rootView.findViewById(R.id.tv_buy_now_product_details);
        offer_tag = (LinearLayout) rootView.findViewById(R.id.offer_tag);
        if (getArguments() != null) {
            if (getArguments().getBoolean("isFromCart")) ;
            {
                setButtonType(Utils.BUTTON_TYPE.EMPTY);
                tv_buy_now_product_details.setVisibility(View.GONE);
                tv_add_to_cart_product_details.setVisibility(View.GONE);
            }
        }
//        productAttributesCarts = new ArrayList<Product_attributesCart>();
        ObjectFactory.getInstance().getHomeManager(getActivity()).initializeArraylist();
    }


    private void setProductDetails() {

        detailsResponses = ObjectFactory.getInstance().getHomeManager(getActivity()).getProductDetailsList();
        ArrayList<PictureModels> pictureModelses = new ArrayList<PictureModels>(Arrays.asList(detailsResponses.get(0).getPictureModels()));
        productImageViewPager = new ProductImageViewPager(getContext(), new ArrayList<PictureModels>());
        viewPager_product_image.setAdapter(productImageViewPager);
        productImageViewPager.updateContext(pictureModelses);
        productImageViewPager.notifyDataSetChanged();
        if (getActivity() instanceof MainActivity) {
            setTitle(detailsResponses.get(0).getName());
            ((MainActivity) getActivity()).setTitle(detailsResponses.get(0).getName());
        }
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager_product_image, true);
        /*static product details*/
        tv_product_name.setText(detailsResponses.get(0).getName());

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideSearch(detailsResponses.get(0).getName());
        }

        tv_product_name_product_list1.setText(detailsResponses.get(0).getName());
        if (detailsResponses.get(0).getProductPrice().getBasePricePAngV() != null) {
            if (!detailsResponses.get(0).getProductPrice().getBasePricePAngV().isEmpty()) {
                tv_product_price.setText(detailsResponses.get(0).getProductPrice().getPrice() + " " + detailsResponses.get(0).getProductPrice().getBasePricePAngV());
            } else {
                tv_product_price.setText(detailsResponses.get(0).getProductPrice().getPrice());
            }
        } else {
            tv_product_price.setText(detailsResponses.get(0).getProductPrice().getPrice());
        }

        if (TextUtils.isEmpty(detailsResponses.get(0).getProductPrice().getOldPrice())) {
            tv_old_product_price.setVisibility(View.GONE);
            offer_tag.setVisibility(View.GONE);
        } else {
            int percentage = Utils.calculateDiscount(detailsResponses.get(0).getProductPrice().getOldPrice(),detailsResponses.get(0).getProductPrice().getPrice());
            if (percentage > 2) {
                offer_tag_text.setText("" + percentage + "% OFF");
                offer_tag.setVisibility(View.VISIBLE);
            } else {
                offer_tag.setVisibility(View.GONE);
            }
            tv_old_product_price.setPaintFlags(tv_old_product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_old_product_price.setText(detailsResponses.get(0).getProductPrice().getOldPrice());
        }

        if (TextUtils.isEmpty(detailsResponses.get(0).getDeliveryDate())) {
            delivery_time_layout.setVisibility(View.GONE);
        } else {
            delivery_text.setText("Delivery : " + detailsResponses.get(0).getDeliveryDate());
        }

        if (TextUtils.isEmpty(detailsResponses.get(0).getStockAvailability())) {
            stock_availability.setVisibility(View.GONE);
        } else {
            if (detailsResponses.get(0).getStockAvailability().equals("Out of stock")) {
                stock_availability.setVisibility(View.VISIBLE);
            } else {
                stock_availability.setVisibility(View.GONE);
            }
        }

        if (TextUtils.isEmpty(detailsResponses.get(0).getIsFreeShipping())) {
            is_free_shipping.setVisibility(View.GONE);
        } else {
            if (detailsResponses.get(0).getIsFreeShipping().equals("true")) {
                is_free_shipping.setVisibility(View.VISIBLE);
            } else {
                is_free_shipping.setVisibility(View.GONE);
            }
        }

        if (detailsResponses.get(0).getFullDescription() != null) {
            if (!detailsResponses.get(0).getFullDescription().isEmpty()) {
                webView.loadDataWithBaseURL("file:///android_asset/",getStyledText(detailsResponses.get(0).getFullDescription()), "text/html", "UTF-8",null);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.getSettings().setMinimumFontSize(40);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new CustomWebViewClient());
                description_tv.setVisibility(View.VISIBLE);
            } else {
                description_tv.setVisibility(View.GONE);
            }

        } else {
            description_tv.setVisibility(View.GONE);

        }

        if (!TextUtils.isEmpty(detailsResponses.get(0).getShortDescription())) {
            short_spec_text.setText(detailsResponses.get(0).getShortDescription());
            short_spec_text.setVisibility(View.VISIBLE);

        }


        final ArrayList<ProductManufacturers> manufactures = new ArrayList<ProductManufacturers>(Arrays.asList(detailsResponses.get(0).getProductManufacturers()));

        if (manufactures != null && !manufactures.isEmpty() && !TextUtils.isEmpty(manufactures.get(0).getName())) {
            brand_value.setText("Brand : " + manufactures.get(0).getName());
        } else {
            brand_layout.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(detailsResponses.get(0).getManufacturerPartNumber())) {
            model_values.setText("Model : " + detailsResponses.get(0).getManufacturerPartNumber());
        } else {
            model_layout.setVisibility(View.GONE);


        }
        try {
            if (!TextUtils.isEmpty(detailsResponses.get(0).getProductManufacturers()[0].getPictureModel().getImageUrl())) {
                Glide.with(getContext())
                        .load(detailsResponses.get(0).getProductManufacturers()[0].getPictureModel().getImageUrl())
                        .into(manufacture_logo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*product descriprtion (specifications)*/
        ArrayList<ProductSpecifications> specificationses = new ArrayList<ProductSpecifications>(Arrays.asList(detailsResponses.get(0).getProductSpecifications()));
        if (specificationses.size() == 0) {
            specification_tv.setVisibility(View.GONE);
            cv_description.setVisibility(View.GONE);
        }
        ll_product_spec.removeAllViews();
        for (int i = 0; i < specificationses.size(); i++) {
            View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_product_spec_details, null);
            LinearLayout spec_layout = (LinearLayout) view_deals.findViewById(R.id.spec_layout);
            TextView spec_name = (TextView) view_deals.findViewById(R.id.tv_attribute_name);
            TextView spec_value = (TextView) view_deals.findViewById(R.id.tv_attribute_value);
            if (i % 2 == 0) {
                spec_layout.setBackgroundColor(getResources().getColor(R.color.spec_color));
            }
            spec_name.setText(specificationses.get(i).getSpecificationAttributeName() + " :");
            spec_value.setText((specificationses.get(i).getValueRaw()).trim());
            ll_product_spec.addView(view_deals);
        }
        /**
         *product attributes
         *like color ,memory
         * */
        attributes = new ArrayList<ProductAttributes>(Arrays.asList(detailsResponses.get(0).getProductAttributes()));
        final ArrayList<ProductAttributes> attributes1 = new ArrayList<ProductAttributes>(Arrays.asList(detailsResponses.get(0).getProductAttributes()));

        final Product_attributesCart product_attributes1 = new Product_attributesCart();
        final ArrayList<CustomModelForAttributePick> list = new ArrayList<>();
        attributesCarts = new ArrayList<>();
        final CustomModelForAttributePick pick = new CustomModelForAttributePick();
        ll_product_attributes.removeAllViews();
        for (int i = 0; i < attributes.size(); i++) {
            View attributesView = getActivity().getLayoutInflater().inflate(R.layout.ll_product_attributes, null);
            final TextView attributeName = (TextView) attributesView.findViewById(R.id.tv_attribute_name_product);
            attributeName.setText(attributes.get(i).getName());
            RecyclerView rv_attributes = (RecyclerView) attributesView.findViewById(R.id.rv_attributes);
            LinearLayoutManager layoutManager = new LinearLayoutManager(attributesView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_attributes.setLayoutManager(layoutManager);
            ArrayList<Values> values = new ArrayList<Values>(Arrays.asList(attributes.get(i).getValues()));
            final ArrayList<Values> values1 = values;
            String attribute_name = "product_attribute_" + attributes1.get(i).getId();

            adapter = new ProductAttributesRvAdapter(attributesView.getContext(), values, attribute_name);
            rv_attributes.setAdapter(adapter);
            if (adapter != null) {
                adapter.setOnItemClickListener(new ProductAttributesRvAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, int pos, View v) {
                        setProductDetails();
                    }
                });
            }
            ll_product_attributes.addView(attributesView);
        }

    }

    private String getStyledText(String fullDescription) {
//        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"fonts/Gotham-Book-Regular.otf\")}body {font-family: MyFont;font-size: medium;text-align: justify;} body {color: #61636f;}</style></head><body>";
//        String pas = "</body></html>";


//        String myHtmlString = pish + fullDescription + pas;
//        return myHtmlString;
        return "<html><style type='text/css'>@font-face { font-family: spqr; src: url('fonts/Gotham-Book-Regular.otf'); } body p {font-family: spqr;}</style>"
                + "<body >" + "<p align=\"justify\" style=\"font-size: 22px; font-family: spqr;\">" + fullDescription + "</p> "+ "</body></html>";

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_to_cart_product_details:
                if (detailsResponses != null && !detailsResponses.isEmpty() && detailsResponses.get(0).getStockAvailability().equals("Out of stock")) {

                    Utils.makeSnackBar(rootView, getString(R.string.out_of_stock), Snackbar.LENGTH_SHORT);

                } else {
                    addToCart(1, 1);
                }
                break;
            case R.id.iv_back_products_detailed_view:
                getActivity().onBackPressed();
                break;
            case R.id.iv_plus:
                addQuantity();
                break;
            case R.id.iv_minus:
                reduceQuantity();
                break;
            case R.id.iv_addto_favorite:

                if (detailsResponses!=null && detailsResponses.get(0).getStockAvailability().equals("Out of stock")) {
                    Utils.makeSnackBar(rootView, getString(R.string.out_of_stock), Snackbar.LENGTH_SHORT);
                } else {
                    addToCart(2, 0);
                }
                break;
            case R.id.iv_share:
                shareItem();
                break;
            case R.id.tv_buy_now_product_details:
                if (detailsResponses!=null && detailsResponses.size()>0 &&
                        detailsResponses.get(0)!=null && detailsResponses.get(0).getStockAvailability()!=null &&
                        detailsResponses.get(0).getStockAvailability().equals("Out of stock")) {
                    Utils.makeSnackBar(rootView, getString(R.string.out_of_stock), Snackbar.LENGTH_SHORT);
                } else {
                    addToCart(1, 5);
                }
                break;
        }
    }

    private void shareItem() {
        if(detailsResponses!=null)
            Utils.shareProduct(getContext(), detailsResponses.get(0).getSeName(), detailsResponses.get(0).getId());
    }

    private void reduceQuantity() {
        String st = et_quantity.getText().toString().trim();
        int num = Integer.parseInt(st);
        if (num > 1)
            num = num - 1;
        et_quantity.setText(String.valueOf(num));
    }

    private void addQuantity() {
        String st = et_quantity.getText().toString().trim();
        int num = Integer.parseInt(st);
        if (num >= 0 && num <= 4)
            num = num + 1;
        et_quantity.setText(String.valueOf(num));
    }

    private void addToCart(int id, int type) {
        id_ = type;
        String product_id = ObjectFactory.getInstance().getAppPreference(getActivity()).getProductId();
        if (product_id != null) {
            AddToCartRequest addToCartRequest = new AddToCartRequest(product_id, ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid(), et_quantity.getText().toString(), String.valueOf(id));
            productAttributesCarts = ObjectFactory.getInstance().getHomeManager(getActivity()).getAttribueSet();
            if (attributes != null && attributes.size() > 0) {
                if (productAttributesCarts!=null && productAttributesCarts.size() > 0) {
                    Model model = new Model();
                    model.setProduct_attributes(productAttributesCarts);
                    addToCartRequest.setModel(model);
                    WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().addToCart(addToCartRequest), this, WebserviceRequestManager.RequestType.ADD_TO_CART);
                    showLoadingDialog();
                } else {
                    if (scroll_details != null) {
                        scroll_details.smoothScrollTo(0, (ll_product_attributes.getBottom()));
                    }
                    Utils.makeSnackBar(rootView, getString(R.string.select_attribute), Snackbar.LENGTH_SHORT);
                }
            } else {
                Model model = new Model();
                model.setProduct_attributes(productAttributesCarts);
                addToCartRequest.setModel(model);
                WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().addToCart(addToCartRequest), this, WebserviceRequestManager.RequestType.ADD_TO_CART);
                showLoadingDialog();
            }

        }
    }

    public void ProgressbarDismiss() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case ADD_TO_CART:
                if (result !=null && result instanceof AddToCartResponse) {
                    Utils.makeSnackBar(rootView, ((AddToCartResponse) result).getData().getValue(), Snackbar.LENGTH_LONG);
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).setCartCount(((AddToCartResponse) result).getData().getCartItemsCount());
                    }
                    if (id_ == 5) {// TODO change this
                        id_ = 0;
                        navigateToCart();
                    }
                } else if (result !=null && result instanceof String) {
                    Utils.makeSnackBar(rootView, result.toString(), Snackbar.LENGTH_LONG);
                }
                break;
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.makeSnackBar(rootView, R.string.error_occured, Snackbar.LENGTH_LONG);

    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

    public class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading
                (WebView view, String url) {
            // here you can check the url
            // (whitelist / blacklist)
            return true;
            // will NOT load the link
            // use "return false;" to allow it to load
        }
    }
}
