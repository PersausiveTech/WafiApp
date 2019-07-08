package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.productsInManufacturer.ProductsInManufacturerRequest;
import com.mobtecnica.wafiapps.utils.Utils;

public class ProductsInManufacturerFragment extends ProductListFragment {
    private String manufacturerId = "1";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ObjectFactory.getInstance().getHomeManager(getActivity()).setSelectedSubCategory(null);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadData(int pageNumber, int sortOrderType, String[] filteredSpec, String[] manufSpec) {
        if (pageNumber > 0) {
            ProductsInManufacturerRequest request = new ProductsInManufacturerRequest(manufacturerId, pageNumber,sortOrderType,filteredSpec,manufSpec);
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().getProductsInManufacturer(request), this, WebserviceRequestManager.RequestType.MANUFACTURER_PRODUCT_LIST);
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
            case MANUFACTURER_PRODUCT_LIST:
                if (result instanceof ProductsInCategoryResponse && productListGridRecyclerAdapter != null) {
                    productsInCategoryResponse = (ProductsInCategoryResponse) result;
                    if((data!=null && data.length==0)&& (manufSpec!=null && manufSpec.length==0)){
                        btn_reset.setVisibility(View.GONE);
                    }
                    btn_reset.setOnClickListener(v -> {
                        pageNumber=1;
                        data = new String[0];
                        manufSpec=new String[0];
                        loadData(pageNumber,sortOrderType,new String[0], new String[0]);
                    });
                    productListGridRecyclerAdapter.setData((ProductsInCategoryResponse) result);
                    if (((ProductsInCategoryResponse) result).getProducts().size() > 0) {
                        pageNumber++; // next page
                    } else {
                        pageNumber = -1; // no more pages
                    }

                    dismissLoadingDialog();
                }

                try {
                    if (getActivity() instanceof MainActivity) {
                        setTitle(((ProductsInCategoryResponse) result).getName());
                        ((MainActivity) getActivity()).setTitle(((ProductsInCategoryResponse) result).getName());
                        ((MainActivity) getActivity()).setCurrentFragmentToolbar(this);
                        dismissLoadingDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dismissLoadingDialog();
                }

                break;
        }
        super.onSuccess(result, requestType);
    }
    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();

    }

    @Override
    public void onCancel(Object result) {
        dismissLoadingDialog();
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
