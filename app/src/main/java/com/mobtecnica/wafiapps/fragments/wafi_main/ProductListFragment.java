package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.kcode.bottomlib.BottomDialog;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.ProductListGridRecyclerAdapter;
import com.mobtecnica.wafiapps.adapters.SubCategoryRecyclerAdapter;
import com.mobtecnica.wafiapps.customViews.CustomButton;
import com.mobtecnica.wafiapps.listeners.ApplyFilterInterface;
import com.mobtecnica.wafiapps.listeners.OnProductListGridItemClickListener;
import com.mobtecnica.wafiapps.listeners.OnVerticalScrollListener;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.Products;
import com.mobtecnica.wafiapps.model.cart.addToCart.AddToCartRequest;
import com.mobtecnica.wafiapps.model.cart.addToCart.response.AddToCartResponse;
import com.mobtecnica.wafiapps.model.categories.SubCategories;
import com.mobtecnica.wafiapps.model.productsInCategories.ProductsInCategroiesRequest;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.SearchProductResponse;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.WishListRequest;
import com.mobtecnica.wafiapps.model.wishlist.addToWishlist.WishListResponse;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

public class ProductListFragment extends BaseFragment implements OnWebserviceCallback, OnProductListGridItemClickListener,View.OnClickListener, ApplyFilterInterface {


    private static final String TAG = ProductListFragment.class.getSimpleName();


    private RecyclerView mRecyclerView;
    private ToggleButton mListGridSwitch;

    GridLayoutManager gridLayoutManager;
    ProductListGridRecyclerAdapter productListGridRecyclerAdapter;
    String[] data;
    String[] manufSpec;
    int sortOrderType = 0;
    int pageNumber = 1;
    private Products currentProduct;
    private RecyclerView rvSubCategory;
    private View mContainerSelectionButtons;
    private CustomButton btn_filter_list;
    public CustomButton btn_reset;
    ProductsInCategoryResponse productsInCategoryResponse;
    SearchProductResponse searchProductResponse;
    private AnimatorSet animSetXY;
    private SubCategoryRecyclerAdapter subCategoryRecyclerAdapter;
    private View empty_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        showSubCategories();
        loadData(pageNumber, sortOrderType, new String[0], new String[0]);// load first page of products in category
    }

    private void showSubCategories() {
        subCategoryRecyclerAdapter = new SubCategoryRecyclerAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvSubCategory.setLayoutManager(layoutManager);
        rvSubCategory.setNestedScrollingEnabled(true);
        rvSubCategory.setAdapter(subCategoryRecyclerAdapter);
        subCategoryRecyclerAdapter.setOnItemClickListener(new SubCategoryRecyclerAdapter.MyClickListener() {
            @Override
            public void onItemClick(SubCategories subCategory) {
                String item_id = subCategory.getId();
                ObjectFactory.getInstance().getAppPreference(getActivity()).setCategoryId(item_id);
                ObjectFactory.getInstance().getHomeManager(getActivity()).setSelectedSubCategory(subCategory);
                navigateToProductListFragment(subCategory.getName());

            }
        });
    }

    private void initialize(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        rvSubCategory = view.findViewById(R.id.rvSubCategories);
        btn_filter_list = view.findViewById(R.id.btn_filter_list);
        btn_reset = view.findViewById(R.id.btn_reset);
        btn_filter_list.setOnClickListener(this);
        mRecyclerView.setNestedScrollingEnabled(true);
        mListGridSwitch = view.findViewById(R.id.switch_list_grid);
        mContainerSelectionButtons =view.findViewById(R.id.container_selection_buttons);
        empty_view =view.findViewById(R.id.empty_view);
        View mButtonSort = view.findViewById(R.id.btn_sort_list_grid);
        mListGridSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                gridLayoutManager.setSpanCount(2);
            } else {
                gridLayoutManager.setSpanCount(1);
            }
            productListGridRecyclerAdapter.notifyItemRangeChanged(0, productListGridRecyclerAdapter.getItemCount());
        });
        btn_reset.setOnClickListener(v -> {
            pageNumber=1;
            data = new String[0];
            manufSpec=new String[0];
            productListGridRecyclerAdapter.clearData();
            productsInCategoryResponse = null;
            loadData(pageNumber,sortOrderType,new String[0], new String[0]);
        });
        mButtonSort.setOnClickListener(v -> showSortBottomDialog());
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        productListGridRecyclerAdapter = new ProductListGridRecyclerAdapter(gridLayoutManager, this);
        mRecyclerView.setAdapter(productListGridRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new OnVerticalScrollListener() {

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                showViews();
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                loadData(pageNumber, sortOrderType, data, manufSpec);
                if (pageNumber < 0) {
                    hideViews();
                }
            }

            @Override
            public void onShow() {
                super.onShow();
                showViews();
            }

            @Override
            public void onHide() {
                super.onHide();
                hideViews();
            }
        });
    }

    private void hideViews() {
        if (animSetXY != null && animSetXY.isRunning()) {
            return;
        }
        ObjectAnimator anim1Y = ObjectAnimator.ofFloat(rvSubCategory, "translationY", -(rvSubCategory.getHeight() + mContainerSelectionButtons.getHeight()));
        ObjectAnimator anim2Y = ObjectAnimator.ofFloat(mRecyclerView, "translationY", -(rvSubCategory.getHeight() + mContainerSelectionButtons.getHeight()));
        ObjectAnimator anim3Y = ObjectAnimator.ofFloat(mContainerSelectionButtons, "translationY", -mContainerSelectionButtons.getHeight());
        animSetXY = new AnimatorSet();
        animSetXY.playTogether(anim1Y, anim2Y, anim3Y);
        animSetXY.start();
    }

    private void showViews() {
        if (animSetXY != null && animSetXY.isRunning()) {
            return;
        }
        ObjectAnimator anim1Y = ObjectAnimator.ofFloat(rvSubCategory, "translationY", 0f);
        ObjectAnimator anim2Y = ObjectAnimator.ofFloat(mRecyclerView, "translationY", 0f);
        ObjectAnimator anim3Y = ObjectAnimator.ofFloat(mContainerSelectionButtons, "translationY", 0f);
        animSetXY = new AnimatorSet();
        animSetXY.playTogether(anim1Y, anim2Y, anim3Y);
        animSetXY.start();
    }

    private void showSortBottomDialog() {
        BottomDialog dialog = BottomDialog.newInstance("SORT", "Cancel", new String[]{"Price low to high", "Price high to low",
                "Name ascending", "Name descending", "Created on"});
        dialog.show(getChildFragmentManager(), "dialog");
        //add item click listener
        dialog.setListener(position -> {
            switch (position) {
                case 0:
                    sortOrderType = 10; //Price low to high
                    break;
                case 1:
                    sortOrderType = 11; //Price high to low
                    break;
                case 2:
                    sortOrderType = 5; //Name ascending
                    break;
                case 3:
                    sortOrderType = 6; //Name descending
                    break;
                case 4:
                    sortOrderType = 15; //Created on
                    break;
            }
            productListGridRecyclerAdapter.clearData();
            pageNumber = 1; // reset the page number
            loadData(pageNumber, sortOrderType, data, manufSpec);
        });

    }

    /**
     * @param pageNumber
     * @param sortOrderType
     * @param filteredSpec
     * @param manufSpec
     */
    public void loadData(int pageNumber, int sortOrderType, String[] filteredSpec, String[] manufSpec) {
        if (pageNumber > 0) {
            String category_id = ObjectFactory.getInstance().getAppPreference(getActivity()).getCategoryID();
            ProductsInCategroiesRequest request = new ProductsInCategroiesRequest(category_id, pageNumber, sortOrderType,filteredSpec,manufSpec);
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getProductsInCategory(request), this, WebserviceRequestManager.RequestType.CATEGORY_PRODUCT_LIST);
            showLoadingDialog();
        }
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        switch (requestType) {
            case CATEGORY_PRODUCT_LIST:
                if((data!=null && data.length==0)&& (manufSpec!=null && manufSpec.length==0)){
                    btn_reset.setVisibility(View.GONE);
                }
                if (result instanceof ProductsInCategoryResponse) {
                    productsInCategoryResponse = (ProductsInCategoryResponse) result;
                    if (productListGridRecyclerAdapter != null) {
                        productListGridRecyclerAdapter.setData((ProductsInCategoryResponse) result);
                        if (((ProductsInCategoryResponse) result).getProducts().size() > 0) {
                            pageNumber++; // next page
                        } else {
                            pageNumber = -1; // no more pages
                        }
                    }
                    if (subCategoryRecyclerAdapter != null) {
                        subCategoryRecyclerAdapter.setData(((ProductsInCategoryResponse) result).getSubCategories());
                    }
                    try {
                        if (getActivity() instanceof MainActivity) {
                            setTitle(productsInCategoryResponse.getName());
                            ((MainActivity) getActivity()).setTitle(productsInCategoryResponse.getName());
                            ((MainActivity) getActivity()).setCurrentFragmentToolbar(this);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case ADD_TO_CART:
                if (result instanceof AddToCartResponse) {
                    Utils.changeSnackBarFont(Snackbar.make(mRecyclerView, ((AddToCartResponse) result).getData().getValue(), Snackbar.LENGTH_LONG));
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).setCartCount(((AddToCartResponse) result).getData().getCartItemsCount());
                    }
                } else if (result instanceof String) { //TODO check the message and then navigate if needed.
                    Utils.changeSnackBarFont(Snackbar.make(mRecyclerView, result.toString(), Snackbar.LENGTH_LONG));
                    ObjectFactory.getInstance().getAppPreference(getActivity()).setProductId(currentProduct.getId());
                    navigateToProductDetailFragment(currentProduct.getName());
                }
                break;
            case ADD_TO_WISHLIST:
                if (result instanceof WishListResponse) {
                    Utils.changeSnackBarFont(Snackbar.make(mRecyclerView, ((WishListResponse) result).getData().getValue(), Snackbar.LENGTH_LONG));
                } else if (result instanceof String) { //TODO check the message and then navigate if needed.
                    Utils.changeSnackBarFont(Snackbar.make(mRecyclerView, result.toString(), Snackbar.LENGTH_LONG));
                    ObjectFactory.getInstance().getAppPreference(getActivity()).setProductId(currentProduct.getId());
                    navigateToProductDetailFragment(currentProduct.getName());
                }
                break;
        }


    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        try {
            Utils.changeSnackBarFont(Snackbar.make(mRecyclerView, getResources().getString(R.string.error_occured), Snackbar.LENGTH_LONG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

    @Override
    public void onAddtoCartClick(Products product) {
        Log.d(TAG, "onAddtoCartClick : " + product.getName());
        this.currentProduct = product;
        AddToCartRequest addToCartRequest = new AddToCartRequest(product.getId(), ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().addToCart(addToCartRequest), this, WebserviceRequestManager.RequestType.ADD_TO_CART);
        showLoadingDialog();
    }

    @Override
    public void onShareClick(Products product) {
        Log.d(TAG, "onShareClick : " + product.getName());
        Utils.shareProduct(getActivity(), product.getSeName(),product.getId());
    }

    @Override
    public void onAddtoWishlistClick(Products product) {
        Log.d(TAG, "onAddtoWishlistClick : " + product.getName());
        this.currentProduct = product;
        WishListRequest wishListRequest = new WishListRequest(product.getId(), ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().addToWishLIst(wishListRequest), this, WebserviceRequestManager.RequestType.ADD_TO_WISHLIST);
        showLoadingDialog();
    }

    @Override
    public void onItemClick(Products product) {
        Log.d(TAG, "onItemClick : " + product.getName());
        ObjectFactory.getInstance().getAppPreference(getContext()).setProductId(product.getId());
        navigateToProductDetailFragment(product.getName());
    }

    private void navigateToProductDetailFragment(String title) {
        ProductDetailedViewFragment fragment = new ProductDetailedViewFragment();
        fragment.setTitle(title);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }
    private void navigateToProductListFragment(String title) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(title);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }
    private void navigateToFilterFragment() {
        FilterFragment fragment = new FilterFragment();
        fragment.setTitle("Filter");
        fragment.setApplyFilterInterface(this);
        Bundle arguments = new Bundle();
        arguments.putParcelable( "ProductsInCategory" , productsInCategoryResponse);
        fragment.setArguments(arguments);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_filter_list:
                if (productsInCategoryResponse!=null && ((productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled()).equals("true")
                ||(productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled()))) {
                    navigateToFilterFragment();
                }else if(searchProductResponse!=null && (searchProductResponse.getData().getPagingFilteringContext().getSpecificationFilter().getEnabled().equals("true")
                || (searchProductResponse.getData().getPagingFilteringContext().getManufacturerFilter().isEnabled()))){
                    productsInCategoryResponse = new ProductsInCategoryResponse();
                    productsInCategoryResponse.setPagingFilteringContext(searchProductResponse.getData().getPagingFilteringContext());
                    navigateToFilterFragment();
                }
                else{
                    Utils.changeSnackBarFont(Snackbar.make(rvSubCategory, R.string.no_filters,Snackbar.LENGTH_SHORT));
                }
                break;
        }

    }

    @Override
    public void onListEmpty(boolean isEmpty) {
        if (empty_view != null) {
            empty_view.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void applyFilter(String[] data, String[] manufSpec) {
        btn_reset.setVisibility(View.VISIBLE);
        pageNumber=1;
        this.data = data;
        this.manufSpec = manufSpec;
        if (productListGridRecyclerAdapter != null) {
            productListGridRecyclerAdapter.clearData();
        }
        loadData(pageNumber,sortOrderType,data,manufSpec);
    }
}

