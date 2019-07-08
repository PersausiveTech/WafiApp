package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.search.SearchRequest;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.SearchProductResponse;

public class SearchResultListingFragment extends ProductListFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ObjectFactory.getInstance().getHomeManager(getActivity()).setSelectedSubCategory(null);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadData(int pageNumber, int sortOrderType, String[] filteredSpec, String[] manufSpec) {
        if (pageNumber > 0) {
            SearchRequest request = new SearchRequest(ObjectFactory.getInstance().getHomeManager(getActivity()).getSearchText(), ""+pageNumber,"" + sortOrderType,filteredSpec,manufSpec);
            WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getActivity()).getApiService().testSearchProduct(request), this, WebserviceRequestManager.RequestType.SEARCH);
            showLoadingDialog();
        }
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case SEARCH:
                if (result instanceof SearchProductResponse && productListGridRecyclerAdapter != null) {
                    productsInCategoryResponse=null;
                    searchProductResponse = (SearchProductResponse)result;
                    if((data!=null && data.length==0)&& (manufSpec!=null && manufSpec.length==0)){
                        btn_reset.setVisibility(View.GONE);
                    }
                    btn_reset.setOnClickListener(v -> {
                        pageNumber=1;
                        data = new String[0];
                        manufSpec=new String[0];
                        loadData(pageNumber,sortOrderType,new String[0], new String[0]);
                    });
                    productListGridRecyclerAdapter.setData((SearchProductResponse) result);
                    if (((SearchProductResponse) result).getData().getProducts().length > 0) {
                        pageNumber++; // next page
                    } else {
                        pageNumber = -1; // no more pages
                    }
                }
                break;
        }
        super.onSuccess(result, requestType);
    }

}
