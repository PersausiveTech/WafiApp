package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.NotFilteredItems;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.DataObjectHolder> {
    private final ArrayList<NotFilteredItems> productsInCategoryList;
    private final Context context;
    private FilterItemClickListener clickListener;

    public FilterAdapter(Context context, ArrayList<NotFilteredItems> productsInCategoryList) {
        this.productsInCategoryList = productsInCategoryList;
        if (productsInCategoryList!=null&&productsInCategoryList.size()>0){
            if(productsInCategoryList.get(0)!=null){
                productsInCategoryList.get(0).setSelected(true);
            }
        }
        this.context = context;
    }

    public void setOnItemClickListener(FilterAdapter.FilterItemClickListener myClickListener) {
        this.clickListener = myClickListener;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_recycler_item, parent, false);
        return (new FilterAdapter.DataObjectHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull DataObjectHolder holder, final int position) {
        holder.filter_recycler.setText(productsInCategoryList.get(position).getSpecificationAttributeName());
        holder.filter_recycler.setSelected(productsInCategoryList.get(position).isSelected());
        holder.view_red.setVisibility(productsInCategoryList.get(position).isOptionItemSelected() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return productsInCategoryList.size();
    }

    public interface FilterItemClickListener {
        void onClicked(int position);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView filter_recycler;
        View view_red;

        public DataObjectHolder(View itemView) {
            super(itemView);
            filter_recycler = (TextView) itemView.findViewById(R.id.filter_recycler);
            view_red = (View) itemView.findViewById(R.id.view_red);
            filter_recycler.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClicked(getAdapterPosition());
            setItemSelected(getAdapterPosition());
            notifyDataSetChanged();
        }
    }

    private void setItemSelected(int adapterPosition) {
        for (NotFilteredItems item : productsInCategoryList) {
            item.setSelected(false);
        }
        productsInCategoryList.get(adapterPosition).setSelected(true);
    }
}
