package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.FoodOnlineActivity;
import com.mobtecnica.wafiapps.activity.LaundryActivity;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.activity.WebviewLodingActivity;
import com.mobtecnica.wafiapps.adapters.CartItemsInHomeAdapter;
import com.mobtecnica.wafiapps.adapters.HomeCategoriesAdapter;
import com.mobtecnica.wafiapps.adapters.HomePageBrandRecyclerView;
import com.mobtecnica.wafiapps.adapters.HomePageCustomViewsRecyclerViewAdapter;
import com.mobtecnica.wafiapps.adapters.HomePageDealsOfWeek;
import com.mobtecnica.wafiapps.adapters.ImagePagerAdapterHome;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.cart.getCart.GetCart;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items;
import com.mobtecnica.wafiapps.model.deals.Deal;
import com.mobtecnica.wafiapps.model.deals.DealsResponse;
import com.mobtecnica.wafiapps.model.home.CustomeHomeViewProducts;
import com.mobtecnica.wafiapps.model.home.DealsRequest;
import com.mobtecnica.wafiapps.model.home.HomeBrands;
import com.mobtecnica.wafiapps.model.home.HomeBrandsResponse;
import com.mobtecnica.wafiapps.model.home.HomeCategories;
import com.mobtecnica.wafiapps.model.home.HomeCustomProducts;
import com.mobtecnica.wafiapps.model.home.HomePageRequest;
import com.mobtecnica.wafiapps.model.home.HomeResponse;
import com.mobtecnica.wafiapps.model.home.HomeSliders;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;
import com.zoho.salesiqembed.ZohoSalesIQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by SIby on 04-01-2017.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnWebserviceCallback {
    private ArrayList<HomeCustomProducts> custom_products;
    private ArrayList<Items> cartResponses;
    private LinearLayout ll_special_items_home;
    private LinearLayout cart_in_home;
    private AutoScrollViewPager mViewPager;
    private RecyclerView recyclerView;
    private HomePageCustomViewsRecyclerViewAdapter customViewsRecyclerViewAdapter;
    private RecyclerView rv_custom_views;
    private RecyclerView ll_deals_of_week;
    private RecyclerView recyclerview_items_in_cart;
    private RelativeLayout rl_home;
    //private ScrollView scrollView_home;
    private TabLayout tabDots;
    private LinearLayout wafi_laundry;
    private LinearLayout wafi_food;
    private TextView featured_brands;
    private RecyclerView buy_now, order_now;
    private FloatingActionButton fbChat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        apiCallQueue();
        onClickLIstners();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.home));
    }

    private void onClickLIstners() {
        wafi_laundry.setOnClickListener(this);
        wafi_food.setOnClickListener(this);
    }

    private void apiCallQueue() {
        showLoadingDialog();
        HomePageRequest homePageRequest = new HomePageRequest(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
//        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getHomePageData(homePageRequest),this,WebserviceRequestManager.RequestType.HOME_DATA);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getHomePageDeals(new DealsRequest()), this, WebserviceRequestManager.RequestType.HOME_DEALS);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getHomeBrand(homePageRequest), this, WebserviceRequestManager.RequestType.HOME_BRANDS);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getHomeHeader(homePageRequest), this, WebserviceRequestManager.RequestType.HOME_HEADER);
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getHomeProducts(homePageRequest), this, WebserviceRequestManager.RequestType.HOME_PRODUCTS);
        GetCart getCartRequest = new GetCart(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getCart(getCartRequest), this, WebserviceRequestManager.RequestType.CART_LIST);

    }

    private void initialize(View rootView) {
        setTitle(getResources().getString(R.string.home));

        fbChat = rootView.findViewById(R.id.fbChat);
        mViewPager = rootView.findViewById(R.id.image_pager);
        recyclerView = rootView.findViewById(R.id.recyclerview_home_brands);
        ll_deals_of_week = rootView.findViewById(R.id.ll_deals_of_week);
        ll_special_items_home = rootView.findViewById(R.id.ll_special_items_home);
        recyclerview_items_in_cart = rootView.findViewById(R.id.recyclerview_items_in_cart);
        rl_home = rootView.findViewById(R.id.rl_home);
        cart_in_home = rootView.findViewById(R.id.cart_in_home);
        //scrollView_home = rootView.findViewById(R.id.scrollView_home);
        wafi_laundry = rootView.findViewById(R.id.ll_laundry);
        wafi_food = rootView.findViewById(R.id.ll_food);
        featured_brands = rootView.findViewById(R.id.featured_brands);
        buy_now = rootView.findViewById(R.id.scroll_categories);
        order_now = rootView.findViewById(R.id.scroll_Ordernow);
        tabDots = rootView.findViewById(R.id.tabDots);
        tabDots.setupWithViewPager(mViewPager, true);

        fbChat.setOnClickListener(this);
    }


    private void setStaticCategories(ArrayList<HomeCategories> homeCategories) {
        //Add custom category "View All"
//        HomeCategories customCategoryObject = new HomeCategories();
//        customCategoryObject.setName(getString(R.string.view_all));
//        customCategoryObject.setSeName("");
//        homeCategories.add(0, customCategoryObject);
        //Add custom category "Laundry"
//        ArrayList<HomeCategories> homeCategories_order = new ArrayList<>();
        HomeCategories customCategoryObject = new HomeCategories();
        customCategoryObject.setName(getString(R.string.laundry_label));
        customCategoryObject.setId("0");
        //homeCategories.add(customCategoryObject);

//        for (int j = 0; j < homeCategories.size(); j++) {
//            if (homeCategories.get(j).getSeName().equalsIgnoreCase("recharge")
//                    || homeCategories.get(j).getSeName().equalsIgnoreCase("cakes-flowers")
//                    || homeCategories.get(j).getSeName().equalsIgnoreCase("grocery_home")
//                    || homeCategories.get(j).getSeName().equalsIgnoreCase("diy-tools")
//                    || homeCategories.get(j).getSeName().equalsIgnoreCase("mum-kids")) {
//                homeCategories_order.add(homeCategories.get(j));
//            }
//        }
//        homeCategories.removeAll(homeCategories_order);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        buy_now.setLayoutManager(layoutManager);
        HomeCategoriesAdapter adapter = new HomeCategoriesAdapter(getContext(), homeCategories);
        buy_now.setAdapter(adapter);
//        LinearLayoutManager layout_Manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        buy_now.setLayoutManager(layoutManager);
//        order_now.setLayoutManager(layout_Manager);
//        HomeCategoriesAdapter homeCategoriesAdapter = new HomeCategoriesAdapter(getContext(), homeCategories_order);
//        order_now.setAdapter(homeCategoriesAdapter);

        adapter.setOnItemClickListener(new HomeCategoriesAdapter.HomePageCustomViewClickListener() {
            @Override
            public void onClicked(int i, HomeCategories categories) {
                if (categories.getId().matches("0")) {
                    Bundle bundle = new Bundle();
                    //Add your data from getFactualResults method to bundle
                    bundle.putInt("ID", 1);
                    Intent intent = new Intent(getActivity(), LaundryActivity.class);
                    startActivity(intent);

                } else {
                    navigateToProductListFragment(categories.getName(), categories.getId());
                }
            }
        });

//        homeCategoriesAdapter.setOnItemClickListener(new HomeCategoriesAdapter.HomePageCustomViewClickListener() {
//            @Override
//            public void onClicked(int i, HomeCategories categories) {
//                if (categories.getId().matches("0")) {
//                    Bundle bundle = new Bundle();
//                    //Add your data from getFactualResults method to bundle
//                    bundle.putInt("ID", 1);
//                    Intent intent = new Intent(getActivity(), LaundryActivity.class);
//                    startActivity(intent);
//
//                } else if (categories.getId().matches("1")) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("ID", 2);
//                    Intent intent = new Intent(getActivity(), WebviewLodingActivity.class);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                } else {
//                    navigateToProductListFragment(categories.getName(), categories.getId());
//                }
//            }
//        });
    }

    private void setSlidingImages(ArrayList<HomeSliders> homeSliders) {
        ImagePagerAdapterHome imagePagerAdapterHome = new ImagePagerAdapterHome(getContext(), new ArrayList<HomeSliders>());
        imagePagerAdapterHome.setSliderItemClickListener(new ImagePagerAdapterHome.OnSliderItemClickListener() {
            @Override
            public void onItemClick(HomeSliders homeSlider) {
                String linkClick = homeSlider.getImageClickLink();
                if (!TextUtils.isEmpty(linkClick)) {
                    if (linkClick.startsWith("categoryId=")) {
                        navigateToProductListFragment(homeSlider.getDisplayText(), linkClick.replace("categoryId=", ""));
                    } else if (linkClick.startsWith("brandId=")) {
                        navigateToManufacturerProductlistFragment(homeSlider.getDisplayText(), linkClick.replace("brandId=", ""));
                    }
                }
            }
        });
        mViewPager.setAdapter(imagePagerAdapterHome);
        mViewPager.startAutoScroll();
        mViewPager.setInterval(5000);
        if (homeSliders != null && homeSliders.size() > 0) {
            imagePagerAdapterHome.updateContext(homeSliders);
            imagePagerAdapterHome.notifyDataSetChanged();
        }

    }

    private void setHomeBrands(ArrayList<HomeBrands> homeBrands) {
        featured_brands.setVisibility(View.VISIBLE);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        HomePageBrandRecyclerView homePageBrandRecyclerView = new HomePageBrandRecyclerView(getContext(), getActivity(), homeBrands);
        recyclerView.setAdapter(homePageBrandRecyclerView);
        homePageBrandRecyclerView.setOnItemClickListener(new HomePageBrandRecyclerView.HomePageBrandClickListener() {
            @Override
            public void onClicked(HomeBrands brand) {
                navigateToManufacturerProductlistFragment(brand.getName().trim(), brand.getId());
            }
        });

    }

    private void showDealsOfWeek(ArrayList<Deal> deals) {
        if(deals!=null&&deals.size()>0){
            LinearLayoutManager layout_Manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            ll_deals_of_week.setLayoutManager(layout_Manager);

            HomePageDealsOfWeek homePageDealsOfWeek = new HomePageDealsOfWeek(getContext(), getActivity());
            ll_deals_of_week.setAdapter(homePageDealsOfWeek);
            homePageDealsOfWeek.setData(deals);
            homePageDealsOfWeek.setOnItemClickListener(new HomePageDealsOfWeek.HomePageDealsOfWeekClickListener() {

                @Override
                public void onClicked(Deal deal) {
                    String id = deal.getCategoryId();
                    if (!TextUtils.isEmpty(id) && !id.equals("0")) {
                        navigateToProductListFragment(deal.getName(), id);
                    } else {
                        id = deal.getManufacturerId();
                        if (!TextUtils.isEmpty(id) && !id.equals("0")) {
                            navigateToManufacturerProductlistFragment(deal.getName(), id);
                        }
                    }
                }
            });
        }
        else{
            ll_deals_of_week.setVisibility(View.GONE);
        }


    }

    private void navigateToProductListFragment(String names_deal, String id) {
        ObjectFactory.getInstance().getAppPreference(getContext()).setCategoryId(id);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(names_deal);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    private void navigateToManufacturerProductlistFragment(String title, String id) {
        ProductsInManufacturerFragment fragment = new ProductsInManufacturerFragment();
        fragment.setManufacturerId(id);
        fragment.setTitle(title);
        ((MainActivity) getActivity()).addFragment(fragment);
    }


    private void setHomeFeaturedList(ArrayList<HomeCustomProducts> homeCustomProducts) {
        List<String> group_id = new ArrayList();
        if (homeCustomProducts == null) {
            return;
        }
        if (getActivity() == null) {
            return;
        }
        for (int i = 0; i < homeCustomProducts.size(); i++) {
            if (!group_id.contains(homeCustomProducts.get(i).getGroupId())) {
                group_id.add(homeCustomProducts.get(i).getGroupId());
            } else {

            }

        }

        ArrayList<CustomeHomeViewProducts> homeViewProductses = new ArrayList<CustomeHomeViewProducts>();
        for (int i = 0; i < group_id.size(); i++) {                              //number of different group id
            CustomeHomeViewProducts products = new CustomeHomeViewProducts();
            ArrayList<HomeCustomProducts> customProductses = new ArrayList<HomeCustomProducts>();
            for (int j = 0; j < homeCustomProducts.size(); j++) {                            //number of total items
                if (group_id.get(i).matches(homeCustomProducts.get(j).getGroupId())) {    //seperating w.r.t grop id
                    customProductses.add(homeCustomProducts.get(j));
                }
            }
            products.setGroupId(group_id.get(i));
            products.setHomeCustomProducts(customProductses);
            homeViewProductses.add(i, products);
        }
        ll_special_items_home.removeAllViews();
        for (int k = 0; k < group_id.size(); k++) {
            final int pos = k;
            View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_custom_products_home, null);
            TextView tv_tittle = (TextView) view_deals.findViewById(R.id.tv_item_name_custom_products);
            TextView tv_see_all_custom_products = (TextView) view_deals.findViewById(R.id.tv_see_all_custom_products);
            rv_custom_views = (RecyclerView) view_deals.findViewById(R.id.recyclerview_custom_products);

            view_deals.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            custom_products = new ArrayList<HomeCustomProducts>();
            custom_products = homeViewProductses.get(k).getHomeCustomProducts();
            try {
                tv_tittle.setText(custom_products.get(0).getProductGroupTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(view_deals.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_custom_views.setLayoutManager(layoutManager);

            customViewsRecyclerViewAdapter = new HomePageCustomViewsRecyclerViewAdapter(view_deals.getContext(), getActivity(), custom_products);
            rv_custom_views.setAdapter(customViewsRecyclerViewAdapter);

            customViewsRecyclerViewAdapter.updateItems(custom_products);
            customViewsRecyclerViewAdapter.notifyDataSetChanged();
            if (customViewsRecyclerViewAdapter != null) {
                customViewsRecyclerViewAdapter.setOnItemClickListener(new HomePageCustomViewsRecyclerViewAdapter.HomePageCustomViewClickListener() {
                    @Override
                    public void onClicked(HomeCustomProducts customProduct) {
                        ObjectFactory.getInstance().getAppPreference(getContext()).setProductId(String.valueOf(customProduct.getId()));
                        navigateToProductDetailFragment(customProduct.getName());
                    }
                });
            }
            tv_see_all_custom_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.makeSnackBar(rv_custom_views, "" + custom_products.get(pos).getProductGroupTitle(), Snackbar.LENGTH_LONG);
                    ObjectFactory.getInstance().getAppPreference(getContext()).setCategoryId(custom_products.get(pos).getGroupId());
                    navigateToProductListPage();

                }
            });
            ll_special_items_home.addView(view_deals);
        }

    }

    private void navigateToProductDetailFragment(String title) {
        try {
            ProductDetailedViewFragment fragment = new ProductDetailedViewFragment();
            fragment.setTitle(title);
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).addFragment(fragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_laundry:
                Intent intent = new Intent(getActivity(), LaundryActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_food:
                Intent intent1 = new Intent(getActivity(), FoodOnlineActivity.class);
                startActivity(intent1);
                break;
            case R.id.fbChat:
                ZohoSalesIQ.Chat.show();
                break;
            default:
                break;
        }
    }

    private void navigateToProductListPage() {
        Fragment fragment = new ProductListFragment();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case HOME_DATA:
                rl_home.setVisibility(View.VISIBLE);
                if (result instanceof HomeResponse) {
                    setSlidingImages(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeSliders())));
                    setStaticCategories(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeCategoriesMenu().getCategories())));
                    setHomeFeaturedList(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeProducts().getHomeCustomProducts())));
                }
                dismissLoadingDialog();
                break;
            case HOME_DEALS:
                if (result instanceof DealsResponse) {
                    showDealsOfWeek(new ArrayList<>(Arrays.asList(((DealsResponse) result).getData().getDeals())));
                }
                break;
            case HOME_BRANDS:
                if (result instanceof HomeBrandsResponse) {
                    setHomeBrands(new ArrayList<>(Arrays.asList(((HomeBrandsResponse) result).getHomeBrands())));
                }
                break;
            case HOME_HEADER:
                rl_home.setVisibility(View.VISIBLE);
                if (result instanceof HomeResponse) {
                    setSlidingImages(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeSliders())));
                    setStaticCategories(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeCategoriesMenu().getCategories())));
                }
                dismissLoadingDialog();
                break;
            case HOME_PRODUCTS:
                if (result instanceof HomeResponse) {
                    setHomeFeaturedList(new ArrayList<>(Arrays.asList(((HomeResponse) result).getHomeProducts().getHomeCustomProducts())));
                }
                break;
            case CART_LIST:
                if (result instanceof GetCartResponse) {
                    if((((GetCartResponse) result).getData().getCart().getItems().size()>0)&&(((GetCartResponse) result).getData().getCart().getItems()!=null)){
                        setCartItemsInHome(((GetCartResponse) result).getData().getCart().getItems());
                    }
                    else{
                        cart_in_home.setVisibility(View.GONE);
                    }

                }
                break;
        }
    }

    private void setCartItemsInHome(ArrayList<Items> items) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview_items_in_cart.setLayoutManager(layoutManager);

        CartItemsInHomeAdapter cartItemsInHomeAdapter = new CartItemsInHomeAdapter(items,this.getContext(),HomeFragment.this);
        recyclerview_items_in_cart.setAdapter(cartItemsInHomeAdapter);
        cartItemsInHomeAdapter.setOnItemClickListener(item -> navigateToCartList());

        if (items == null || items.isEmpty()) {
            cart_in_home.setVisibility(View.GONE);
        }
    }

    public void navigateToCartList() {
        Fragment fragment = new CartFragment();
        if(getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).addFragment(fragment);
        }
    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.makeSnackBar(recyclerView, R.string.error_occured, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

    public void updateCartItems() {
        try {
            GetCart getCartRequest = new GetCart(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getCart(getCartRequest), this, WebserviceRequestManager.RequestType.CART_LIST);
            setCartItemsInHome(WebserviceRequestManager.getInstance().getGetCartResponse().getData().getCart().getItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
