package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.SubCategoryHorizontalRvAdapter;
import com.mobtecnica.wafiapps.adapters.SubCategoryListRvAdapter;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.categories.SubCategories;
import com.mobtecnica.wafiapps.model.home.HomeCategories;
import com.mobtecnica.wafiapps.utils.BaseFragment;

/**
 * Created by siby on 12-Jun-17.
 */

public class HomeCategoriesListFragment extends BaseFragment {
    int position;
    SubCategoryListRvAdapter subCategoryListRvAdapter = null;
    LinearLayout linearLayout_categories;
    android.support.v7.widget.Toolbar toolbar;
    private View rootview;
    private RecyclerView rv_items;
    private ArrayList<HomeCategories> homeCategories;
    private SubCategories[] subCategoryList;
    private ArrayList<SubCategories> arrayList;

    public HomeCategoriesListFragment() {
    }

    @SuppressLint("ValidFragment")
    public HomeCategoriesListFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_home_categories, container, false);
        initView();
        return rootview;
    }

    private void initView() {
        /*rv_items = (RecyclerView) rootview.findViewById(R.id.rv_items);

        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_items.setLayoutManager(verticalLayoutmanager);

        if (homeCategories.get(position).getSubCategories().length > 0) {
            subCategoryListRvAdapter = new SubCategoryListRvAdapter(getActivity(), homeCategories.get(position).getSubCategories());
            rv_items.setAdapter(subCategoryListRvAdapter);
        }*/


        homeCategories = ObjectFactory.getInstance().getHomeManager(getContext()).getSelectedHomeCategories();
        linearLayout_categories = (LinearLayout) rootview.findViewById(R.id.ll_subcategories);
        if(position<homeCategories.size()) {
            arrayList = new ArrayList<SubCategories>(Arrays.asList(homeCategories.get(position).getSubCategories()));
            linearLayout_categories.removeAllViews();
            for (int i = 0; i < arrayList.size(); i++) {
                View view_deals = getActivity().getLayoutInflater().inflate(R.layout.ll_subcategory_list_home, null);
                TextView tv_subcategory = (TextView) view_deals.findViewById(R.id.tv_subcategory);
                RecyclerView recyclerview = (RecyclerView) view_deals.findViewById(R.id.recyclerview_custom_products);
                tv_subcategory.setText(arrayList.get(i).getName());
                final ArrayList<SubCategories> subCategories;
                if (arrayList.get(i).getSubCategories().length > 0) {
                    subCategories = new ArrayList<SubCategories>(Arrays.asList(arrayList.get(i).getSubCategories()));
                    view_deals.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(view_deals.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerview.setLayoutManager(layoutManager);

                    SubCategoryHorizontalRvAdapter adapter = new SubCategoryHorizontalRvAdapter(getActivity(), getActivity(), subCategories);
                    recyclerview.setAdapter(adapter);

                    adapter.setOnItemClickListener(new SubCategoryHorizontalRvAdapter.HomePageCustomViewClickListener() {
                        @Override
                        public void onClicked(int position, View v, String id) {
                            String item_id = String.valueOf(id);
                            ObjectFactory.getInstance().getAppPreference(getActivity()).setCategoryId(item_id);
                            ObjectFactory.getInstance().getHomeManager(getActivity()).setSelectedSubCategory(subCategories.get(position));
                            navigateToProductListFragment(subCategories.get(position).getName());
                        }
                    });

                } else {
                    SubCategories subCate = new SubCategories();
                    subCate = arrayList.get(i);
                    subCategories = new ArrayList<SubCategories>();
                    subCategories.add(subCate);

                    view_deals.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(view_deals.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerview.setLayoutManager(layoutManager);

                    SubCategoryHorizontalRvAdapter adapter = new SubCategoryHorizontalRvAdapter(getActivity(), getActivity(), subCategories);
                    recyclerview.setAdapter(adapter);

                    adapter.setOnItemClickListener(new SubCategoryHorizontalRvAdapter.HomePageCustomViewClickListener() {
                        @Override
                        public void onClicked(int position, View v, String id) {
                            String item_id = String.valueOf(id);
                            ObjectFactory.getInstance().getAppPreference(getActivity()).setCategoryId(item_id);
                            ObjectFactory.getInstance().getHomeManager(getActivity()).setSelectedSubCategory(subCategories.get(position));
                            navigateToProductListFragment(subCategories.get(position).getName());
                        }
                    });
                }
                linearLayout_categories.addView(view_deals);
            }
        }



    }

    private void navigateToProductListFragment(String title) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(title);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addFragment(fragment);
        }
    }

}
