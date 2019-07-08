package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.Products;

/**
 * Created by vishnu on 27/10/2016.
 */

public class SearchListRvAdapter extends RecyclerView.Adapter<SearchListRvAdapter.DataObjectHolder> {
    private static SearchListRvAdapter.MyClickListener myClickListener;
    private Context context;
    private ArrayList<Products> products;

    public SearchListRvAdapter(Context context, ArrayList<Products> product) {
        this.context = context;
        products = product;
    }

    public void setOnItemClickListener(SearchListRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public SearchListRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_product_listing, parent, false);
        SearchListRvAdapter.DataObjectHolder dataObjectHolder = new SearchListRvAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(SearchListRvAdapter.DataObjectHolder holder, final int position) {
        Glide.with(context).load(products.get(position).getDefaultPictureModel().getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.product_image);
        holder.item_name_product_list.setText(products.get(position).getName());
        holder.tv_product_price.setText(products.get(position).getProductPrice().getPrice());
        holder.tv_product_old_price.setText(products.get(position).getProductPrice().getOldPrice());
        holder.tv_product_old_price.setPaintFlags(holder.tv_product_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.item_name_product_list.setText(products.get(position).getName());
    }

    public void setDataset(ArrayList<Products> mDataset) {
        this.products = mDataset;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_share_grid_item;
        ImageView wish_list;
        TextView item_name_product_list;
        TextView tv_product_price;
        TextView tv_product_old_price;
        TextView product_model_produt_list;
        TextView product_availability_product_list;
        TextView tv_add_to_cart;
        ImageView product_image;

        public DataObjectHolder(View itemView) {
            super(itemView);
            item_name_product_list = (TextView) itemView.findViewById(R.id.item_name_product_list);
            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            tv_product_old_price = (TextView) itemView.findViewById(R.id.tv_product_old_price);

            product_model_produt_list = (TextView) itemView.findViewById(R.id.product_model_produt_list);
            tv_add_to_cart = (TextView) itemView.findViewById(R.id.tv_add_to_cart);
            product_availability_product_list = (TextView) itemView.findViewById(R.id.product_availability_product_list);
            product_image = (ImageView) itemView.findViewById(R.id.iv_product_image_recycler);
            iv_share_grid_item = (ImageView) itemView.findViewById(R.id.iv_share_grid_item);
            wish_list = (ImageView) itemView.findViewById(R.id.iv_add_to_wishlist);
            itemView.setOnClickListener(this);
            product_image.setOnClickListener(this);
            wish_list.setOnClickListener(this);
            tv_add_to_cart.setOnClickListener(this);
            iv_share_grid_item.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
