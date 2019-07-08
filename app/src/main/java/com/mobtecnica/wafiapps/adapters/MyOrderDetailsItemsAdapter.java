package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.orderDetails.Item;

import java.util.List;

/**
 * Created by ALIAKBAR on 04-08-2017.
 */

public class MyOrderDetailsItemsAdapter extends RecyclerView.Adapter<MyOrderDetailsItemsAdapter.DataObjectHolder> {

    private Context mContext;
    List<Item> items;

    public MyOrderDetailsItemsAdapter(Context mContext, List<Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public MyOrderDetailsItemsAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_list_my_order_details, parent, false);
        MyOrderDetailsItemsAdapter.DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MyOrderDetailsItemsAdapter.DataObjectHolder holder, int position) {
        holder.tv_product_name.setText(items.get(position).getProductName());
        holder.tv_product_id.setText(items.get(position).getProductId().toString());
        holder.tv_product_quantity.setText(items.get(position).getQuantity().toString());
        holder.tv_sub_total.setText(items.get(position).getSubTotal());
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }

    }

    public void updateItemsData(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {

        AppCompatImageView iv_product_item;
        AppCompatTextView tv_product_name;
        AppCompatTextView tv_product_id;
        AppCompatTextView tv_product_quantity;
        AppCompatTextView tv_sub_total;

        public DataObjectHolder(View itemView) {
            super(itemView);
            iv_product_item = (AppCompatImageView) itemView.findViewById(R.id.iv_product_item);
            tv_product_name = (AppCompatTextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_id = (AppCompatTextView) itemView.findViewById(R.id.tv_product_id);
            tv_product_quantity = (AppCompatTextView) itemView.findViewById(R.id.tv_product_quantity);
            tv_sub_total = (AppCompatTextView) itemView.findViewById(R.id.tv_sub_total);
        }
    }
}
