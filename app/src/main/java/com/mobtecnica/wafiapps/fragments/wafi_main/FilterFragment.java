package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.FilterAdapter;
import com.mobtecnica.wafiapps.adapters.FilterManufaturerValueAdapter;
import com.mobtecnica.wafiapps.adapters.FilterValueAdapter;
import com.mobtecnica.wafiapps.listeners.ApplyFilterInterface;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.FilteredItems;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.productsInManufacturer.ManufacturerModel;
import com.mobtecnica.wafiapps.utils.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private RecyclerView filter_list;
    private RecyclerView filter_value_list;
    private ArrayList<NotFilteredItems> productsInCategoryList = new ArrayList<>();
    private ArrayList<FilteredItems> manufacturerProductsInCategoryList = new ArrayList<>();
    private ArrayList<NotFilteredItems> filter_atribute_values = new ArrayList<>();
    private ArrayList<FilteredItems> filter_manufacturer_atribute_values = new ArrayList<>();
    private ProductsInCategoryResponse productsInCategoryResponse;
    private FilterValueAdapter filterValueAdapter;
    private FilterManufaturerValueAdapter filterManufaturerValueAdapter;
    private EditText search_filter;
    private ImageView img_filter_search;
    private Button apply_filter;
    private ArrayList<NotFilteredItems> filterattribute;
    private ApplyFilterInterface applyFilterInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        onClickListners();
        Bundle arguments = getArguments();
        productsInCategoryResponse = arguments.getParcelable("ProductsInCategory");
        setFilterDatas();
    }

    private void onClickListners() {
        if(search_filter!=null)
            search_filter.setOnClickListener(this);
        if(img_filter_search!=null)
            img_filter_search.setOnClickListener(this);
        if(apply_filter!=null)
            apply_filter.setOnClickListener(this);
    }

    private void setFilterDatas() {
        if (productsInCategoryResponse!=null&&productsInCategoryResponse.getPagingFilteringContext()!=null && (productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled()).equals("true") ||
                productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled()) {
            productsInCategoryList.clear();
            filter_manufacturer_atribute_values.clear();
            manufacturerProductsInCategoryList.clear();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            filter_list.setLayoutManager(linearLayoutManager);
            NotFilteredItems notFilteredItems1 = new NotFilteredItems();
            if( productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled()){
                notFilteredItems1.setSpecificationAttributeName("Manufacturer");
                notFilteredItems1.setSpecificationAttributeId("123456");
                if(getAlreadyFilterManufacAttributeValues(productsInCategoryResponse)!=null && getAlreadyFilterManufacAttributeValues(productsInCategoryResponse).size()>0){
                    notFilteredItems1.setSelected(true);
                    notFilteredItems1.setOptionItemSelected(true);
                }
                productsInCategoryList.add(notFilteredItems1);
            }

            NotFilteredItems[] notFilteredItems=null;
            FilteredItems[] manufacturerModelsNotfiltered=null;
            if(productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getNotFilteredItems()!=null &&
                    productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getNotFilteredItems().length>0){
                productsInCategoryList.addAll(getAlreadyFilterAttributeValues(productsInCategoryResponse));
                notFilteredItems = productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getNotFilteredItems();
            }
            if(productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().getNotFilteredItems()!=null &&
                    productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().getNotFilteredItems().length>0){
                manufacturerModelsNotfiltered = productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().getNotFilteredItems();
                manufacturerProductsInCategoryList.addAll(getAlreadyFilterManufacAttributeValues(productsInCategoryResponse));
                manufacturerProductsInCategoryList.addAll(Arrays.asList(manufacturerModelsNotfiltered));
            }

            if(notFilteredItems!=null && notFilteredItems.length>0){
                productsInCategoryList.addAll(Arrays.asList(notFilteredItems));
            }

            if(productsInCategoryList!=null && productsInCategoryList.size()>0)
            filterattribute = getFilterAttributes(productsInCategoryList);

            final FilterAdapter filterAdapter = new FilterAdapter(getContext(), filterattribute);
            filter_list.setAdapter(filterAdapter);

            if(filterattribute!=null && filterattribute.size()>0){
                search_filter.setHint(getString(R.string.search_filter) + " " + filterattribute.get(0).getSpecificationAttributeName());
            }

            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            filter_value_list.setLayoutManager(linearLayoutManager1);
            filter_atribute_values = getFilterAttributeValue(productsInCategoryList, filterattribute.get(0).getSpecificationAttributeId());
            filterValueAdapter = new FilterValueAdapter(getContext(), filter_atribute_values);
            filter_value_list.setAdapter(filterValueAdapter);
            if( productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled()){
                filter_manufacturer_atribute_values.addAll(manufacturerProductsInCategoryList);
                filterManufaturerValueAdapter = new FilterManufaturerValueAdapter(getContext(), filter_manufacturer_atribute_values);

                if(filterManufaturerValueAdapter!=null) {
                    filter_value_list.setAdapter(filterManufaturerValueAdapter);

                    filterManufaturerValueAdapter.setOnItemClickListener(item -> {
                        notFilteredItems1.setSelected(true);
                        notFilteredItems1.setOptionItemSelected(true);
                        filterAdapter.notifyDataSetChanged();
                    });
                }
            }

            if(filterValueAdapter!=null && filterAdapter!=null )
            filterValueAdapter.setOnItemClickListener(item -> filterAdapter.notifyDataSetChanged());


            filterAdapter.setOnItemClickListener(position -> {
                String id = filterattribute.get(position).getSpecificationAttributeId();
                if(productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled() &&productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled()!= null &&
                        productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled().equalsIgnoreCase("true")){
                    if(position==0){
                        filter_value_list.setAdapter(filterManufaturerValueAdapter);
                        filterManufaturerValueAdapter.setData(manufacturerProductsInCategoryList);
                    }else{
                        filter_value_list.setAdapter(filterValueAdapter);
                        filterValueAdapter.setData(getFilterAttributeValue(productsInCategoryList, id));
                    }
                }else if(productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled()!= null && productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getEnabled().equalsIgnoreCase("true")){
                    filter_value_list.setAdapter(filterValueAdapter);
                    filterValueAdapter.setData(getFilterAttributeValue(productsInCategoryList, id));
                }else if(productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().isEnabled()){
                    filter_value_list.setAdapter(filterManufaturerValueAdapter);
                    filterManufaturerValueAdapter.setData(manufacturerProductsInCategoryList);
                }
                search_filter.setHint(getString(R.string.search_filter) + " " + filterattribute.get(position).getSpecificationAttributeName());

            });

        }
    }

    private ArrayList<NotFilteredItems> getAlreadyFilterAttributeValues(ProductsInCategoryResponse productsInCategoryResponse){
        ArrayList<NotFilteredItems> filterattributes = new ArrayList<>();
        NotFilteredItems[] alreadyFileredItems = productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getAlreadyFilteredItems();
        for (int i = 0; i < alreadyFileredItems.length; i++) {
            NotFilteredItems filteredItem = alreadyFileredItems[i];
            filteredItem.setSelected(true);
            filteredItem.setOptionItemSelected(true);
            filteredItem.setOptionsSelected(true);
            filterattributes.add(filteredItem);
        }
        return filterattributes;
    }
    private ArrayList<FilteredItems> getAlreadyFilterManufacAttributeValues(ProductsInCategoryResponse productsInCategoryResponse){
        ArrayList<FilteredItems> filterattributes = new ArrayList<>();
        FilteredItems[] alreadyFileredItems = productsInCategoryResponse.getPagingFilteringContext().getManufacturerFilter().getAlreadyFilteredItems();
        for (int i = 0; i < alreadyFileredItems.length; i++) {
            FilteredItems filteredItem = alreadyFileredItems[i];
            filteredItem.setSelected(true);
            filteredItem.setOptionItemSelected(true);
            filteredItem.setOptionsSelected(true);
            filterattributes.add(filteredItem);
        }
        return filterattributes;
    }
   /* private ArrayList<NotFilteredItems> getAllFilterAttributes(ArrayList<NotFilteredItems> productsInCategoryResponse) {
        ArrayList<NotFilteredItems> filterattributes = new ArrayList<>();
        NotFilteredItems[] notFileredItems = productsInCategoryResponse.getPagingFilteringContext().getSpecificationFilter().getNotFilteredItems();

        filterattributes.addAll(Arrays.asList(notFileredItems));
        return filterattributes;
    }*/

    private ArrayList getFilterAttributes(ArrayList<NotFilteredItems> notFilteredItems) {
        ArrayList<NotFilteredItems> filterattributes = new ArrayList<>();
        String temp_id = "";
        for (int i = 0; i < notFilteredItems.size(); i++) {
            NotFilteredItems filteredItem = notFilteredItems.get(i);
            if (!temp_id.equals(filteredItem.getSpecificationAttributeId())) {
                filterattributes.add(filteredItem);
            }
            temp_id = filteredItem.getSpecificationAttributeId();
        }
        return filterattributes;
    }

    private ArrayList getFilterAttributeValue(ArrayList<NotFilteredItems> notFilteredItems, String id) {
        ArrayList<NotFilteredItems> filteredItems = new ArrayList<>();
        for (int i = 0; i < notFilteredItems.size(); i++) {
            NotFilteredItems filteredItems1 = notFilteredItems.get(i);
            if (filteredItems1.getSpecificationAttributeId().equals(id)) {
                filteredItems.add(filteredItems1);
            }
        }
        return filteredItems;
    }
  /*  private ArrayList getFilterManufcAttributeValue(ArrayList<NotFilteredItems> notFilteredItems, String id) {
        ArrayList<FilteredItems> filteredItems = new ArrayList<>();
        for (int i = 0; i < notFilteredItems.size(); i++) {
            NotFilteredItems filteredItems1 = notFilteredItems.get(i);
            if (filteredItems1.getSpecificationAttributeId().equals(id)) {
                filteredItems.add(filteredItems1);
            }
        }
        return filteredItems;
    }*/
    private void initViews() {
        filter_list = (RecyclerView) rootView.findViewById(R.id.filter_list);
        filter_value_list = (RecyclerView) rootView.findViewById(R.id.filter_value_list);
        search_filter = (EditText) rootView.findViewById(R.id.f_search);
        img_filter_search = (ImageView) rootView.findViewById(R.id.search_filter);
        filter_value_list = (RecyclerView) rootView.findViewById(R.id.filter_value_list);
        apply_filter = (Button) rootView.findViewById(R.id.apply_filter);

        search_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterValueAdapter.filter(s);
                if(filterManufaturerValueAdapter!=null){
                    filterManufaturerValueAdapter.filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterValueAdapter.filter(s);
                if(filterManufaturerValueAdapter!=null){
                    filterManufaturerValueAdapter.filter(s);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.apply_filter:
                ArrayList<NotFilteredItems> notFilteredItems = getSelectedFilterAttributes(productsInCategoryList);
                ArrayList<FilteredItems> manufactrerFiletrs = getSelectedManufacturerFilterAttributes(manufacturerProductsInCategoryList);
                if(applyFilterInterface!=null && notFilteredItems!=null && manufactrerFiletrs!=null){
                    String[] filteredSpec = new String[notFilteredItems.size()];
                    String[] manufSpec = new String[manufactrerFiletrs.size()];
                    for (int i = 0; i < notFilteredItems.size(); i++) {
                        filteredSpec[i] = notFilteredItems.get(i).getSpecificationAttributeOptionId();
                    }
                    for (int i = 0; i < manufactrerFiletrs.size(); i++) {
                        manufSpec[i] = manufactrerFiletrs.get(i).getManufacturerId();
                    }
                    applyFilterInterface.applyFilter(filteredSpec,manufSpec);
                }
                getActivity().onBackPressed();
                break;
        }
    }

    private ArrayList<NotFilteredItems> getSelectedFilterAttributes(ArrayList<NotFilteredItems> allFilterAttributes) {
        ArrayList<NotFilteredItems> selected_options = new ArrayList<>();
        for (NotFilteredItems items : allFilterAttributes) {
            if (items.isOptionsSelected()) {
                selected_options.add(items);
            }
        }
        return selected_options;
    }
    private ArrayList<FilteredItems> getSelectedManufacturerFilterAttributes(ArrayList<FilteredItems> allFilterAttributes) {
        ArrayList<FilteredItems> selected_options = new ArrayList<>();
        for (FilteredItems items : allFilterAttributes) {
            if (items.isOptionsSelected()) {
                selected_options.add(items);
            }
        }
        return selected_options;
    }
    public void setApplyFilterInterface(ApplyFilterInterface applyFilterInterface){
        this.applyFilterInterface = applyFilterInterface;
    }


}
