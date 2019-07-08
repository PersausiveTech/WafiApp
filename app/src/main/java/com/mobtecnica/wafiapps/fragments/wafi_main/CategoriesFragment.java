package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.CustomExpandableListViewAdapter;
import com.mobtecnica.wafiapps.listeners.OnWebserviceCallback;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WebserviceRequestManager;
import com.mobtecnica.wafiapps.model.categories.Categories;
import com.mobtecnica.wafiapps.model.categories.CategoriesRequest;
import com.mobtecnica.wafiapps.model.categories.GetAllCategoriesResponse;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SIby on 04-01-2017.
 */

public class CategoriesFragment extends BaseFragment implements OnWebserviceCallback {
    CustomExpandableListViewAdapter customExpandableListViewAdapter;
    private ExpandableListView expandableListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private void setData(GetAllCategoriesResponse categoriesResponse) {
        final ArrayList<Categories> categories_api = new ArrayList<>(Arrays.asList(categoriesResponse.getCategories()));
        customExpandableListViewAdapter = new CustomExpandableListViewAdapter(getContext(), categories_api);
        expandableListView.setAdapter(customExpandableListViewAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Categories categories = categories_api.get(groupPosition);
                if (categories != null && (categories.getSubCategories() == null || categories.getSubCategories().length == 0)) {
                    ObjectFactory.getInstance().getAppPreference(getContext()).setCategoryId(categories.getId());
                    navigateToProductListFragment(categories.getName());
                    return true;
                }
                return false;
            }
        });
    }

    private void navigateToProductListFragment(String title) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(title);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_categories, container, false);
        intialize(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCall();
    }

    private void apiCall() {
        CategoriesRequest request = new CategoriesRequest();
        WebserviceRequestManager.getInstance().enqueueRequest(ObjectFactory.getInstance().getRestClient(getContext()).getApiService().getCategories(request), this, WebserviceRequestManager.RequestType.CATEGORY_LIST);
    }


    private void intialize(View view) {
        expandableListView = view.findViewById(R.id.expandableListView);
        mSwipeRefreshLayout =view.findViewById(R.id.swipe_refresh_category_list);
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
            }
        });
        setTitle(getString(R.string.categories));
    }

    @Override
    public void onSuccess(Object result, WebserviceRequestManager.RequestType requestType) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        switch (requestType) {
            case CATEGORY_LIST:
                if (result instanceof GetAllCategoriesResponse) {
                    GetAllCategoriesResponse categoriesResponse = (GetAllCategoriesResponse) result;
                    setData(categoriesResponse);
                }
                break;
        }
        showRefreshview(false);
    }

    @Override
    public void onFailure(String message) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        Utils.makeSnackBar(expandableListView, R.string.error_occured, Snackbar.LENGTH_LONG);
        showRefreshview(false);
    }

    @Override
    public void onCancel(Object result) {
        if (getActivity() == null) {
            dismissLoadingDialog();
            return;
        }
        dismissLoadingDialog();
        showRefreshview(false);
    }
    private void showRefreshview(boolean show){
        if (mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setRefreshing(show);
        }

    }

}
