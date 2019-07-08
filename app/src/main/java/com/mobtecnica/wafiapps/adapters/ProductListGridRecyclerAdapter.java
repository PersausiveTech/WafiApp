package com.mobtecnica.wafiapps.adapters;

import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.CustomTextView;
import com.mobtecnica.wafiapps.listeners.OnProductListGridItemClickListener;
import com.mobtecnica.wafiapps.model.Products;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.ProductsInCategoryResponse;
import com.mobtecnica.wafiapps.model.search.searchProductResponse.SearchProductResponse;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductListGridRecyclerAdapter extends RecyclerView.Adapter<ProductListGridRecyclerAdapter.SimpleViewHolder> {

    private GridLayoutManager gridLayoutManager;
    private OnProductListGridItemClickListener onProductListGridItemClickListener;
    private ArrayList<Products> productsArrayList = new ArrayList<>();

    public ProductListGridRecyclerAdapter(GridLayoutManager gridLayoutManager, OnProductListGridItemClickListener onProductListGridItemClickListener) {
        this.gridLayoutManager = gridLayoutManager;
        this.onProductListGridItemClickListener = onProductListGridItemClickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_card, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.grid_container.setVisibility(gridLayoutManager.getSpanCount() == 1 ? View.GONE : View.VISIBLE);
        holder.list_container.setVisibility(gridLayoutManager.getSpanCount() == 1 ? View.VISIBLE : View.GONE);
        holder.setPosition(position);
        updateView(holder, productsArrayList.get(position));
    }

    private void updateView(SimpleViewHolder holder, Products products) {
        Glide.with(holder.productImage.getContext())
                .load(products.getDefaultPictureModel().getImageUrl())
                .thumbnail(Glide.with(holder.productImage.getContext()).load(R.drawable.loading))
                .into(holder.productImage);
        holder.tvProductNameInGrid.setText(products.getName());
        holder.tvProductNameInList.setText(products.getName());
        holder.tvProductOldPriceInGrid.setText(products.getProductPrice().getOldPrice());
        holder.tvProductOldPriceInList.setText(products.getProductPrice().getOldPrice());
        holder.tvProductPriceInGrid.setText(products.getProductPrice().getPrice());
        holder.tvProductPriceInList.setText(products.getProductPrice().getPrice());
        if(!TextUtils.isEmpty(products.getStockAvailability()) && products.getStockAvailability().equalsIgnoreCase("In stock")){
            holder.llNoStockData.setVisibility(View.GONE);
            holder.ivSoldOut.setVisibility(View.GONE);
        }else{
            holder.llNoStockData.setVisibility(View.VISIBLE);
            holder.ivSoldOut.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(products.getProductPrice().getOldPrice())) {
            holder.offer_tag_text.setVisibility(View.GONE);
        } else {
            int percentage = Utils.calculateDiscount(products.getProductPrice().getOldPrice(),products.getProductPrice().getPrice());
            if (percentage > 2) {
                holder.offer_tag_text.setText("" + percentage + "% OFF");
                holder.offer_tag_text.setVisibility(View.VISIBLE);
            } else {
                holder.offer_tag_text.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public void upDateData(ArrayList updatedProducts) {
        int itemCountBeforeUpdate = productsArrayList.size();
        productsArrayList.addAll(updatedProducts);
        notifyItemRangeInserted(itemCountBeforeUpdate, productsArrayList.size());
        onProductListGridItemClickListener.onListEmpty(productsArrayList.size() == 0);
    }

    public void clearData() {
        notifyItemRangeRemoved(0, productsArrayList.size());
        productsArrayList.clear();
        //onProductListGridItemClickListener.onListEmpty(productsArrayList.size() == 0);
    }

    public void setData(ProductsInCategoryResponse productsInCategoryResponse) {
        try {
            upDateData(productsInCategoryResponse.getProducts());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setData(SearchProductResponse searchProductResponse) {
        try {
            upDateData(new ArrayList<>(Arrays.asList(searchProductResponse.getData().getProducts())));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position;
        ImageView productImage;
        View list_container;
        View grid_container;
        RatingBar ratingBarInGrid, ratingBarInList;
        TextView tvProductNameInGrid, tvProductNameInList;
        TextView tvProductPriceInGrid, tvProductPriceInList;
        TextView tvProductOldPriceInGrid, tvProductOldPriceInList;
        Button tvAddToCartInGrid, tvAddToCartInList;
        ImageView ivShareInGrid, ivShareInList;
        ImageView ivAddToWishlisInGrid, ivAddToWishlisInList;
        CustomTextView offer_tag_text;
        LinearLayout llNoStockData;
        ImageView ivSoldOut;
        SimpleViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_product);
            list_container = itemView.findViewById(R.id.list_view);
            grid_container = itemView.findViewById(R.id.grid_view);
            ratingBarInGrid = itemView.findViewById(R.id.rating_in_grid);
            ratingBarInList = itemView.findViewById(R.id.rating_in_list);
            tvProductNameInGrid = itemView.findViewById(R.id.tv_product_name_in_grid);
            tvProductNameInList = itemView.findViewById(R.id.tv_product_name_in_list);
            tvProductPriceInGrid = itemView.findViewById(R.id.tv_product_price_in_grid);
            tvProductPriceInList = itemView.findViewById(R.id.tv_product_price_in_list);
            tvProductOldPriceInGrid = itemView.findViewById(R.id.tv_product_old_price_in_grid);
            tvProductOldPriceInList = itemView.findViewById(R.id.tv_product_old_price_in_list);
            tvAddToCartInGrid = itemView.findViewById(R.id.tv_add_to_cart_in_grid);
            tvAddToCartInList = itemView.findViewById(R.id.tv_add_to_cart_in_list);
            ivShareInGrid = itemView.findViewById(R.id.iv_share_in_grid);
            ivShareInList = itemView.findViewById(R.id.iv_share_in_list);
            ivAddToWishlisInGrid = itemView.findViewById(R.id.iv_add_to_wishlist_in_grid);
            ivAddToWishlisInList = itemView.findViewById(R.id.iv_add_to_wishlist_in_list);
            offer_tag_text = itemView.findViewById(R.id.offer_tag_text);
            llNoStockData = itemView.findViewById(R.id.llNoStockData);
            ivSoldOut = itemView.findViewById(R.id.ivSoldOut);
            tvProductOldPriceInGrid.setPaintFlags(tvProductOldPriceInGrid.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvProductOldPriceInList.setPaintFlags(tvProductOldPriceInList.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //init clickListeners
            initClickListeners(itemView);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        private void initClickListeners(View view) {
            view.setOnClickListener(this);
            ivShareInGrid.setOnClickListener(this);
            ivShareInList.setOnClickListener(this);
            ivAddToWishlisInGrid.setOnClickListener(this);
            ivAddToWishlisInList.setOnClickListener(this);
            tvAddToCartInGrid.setOnClickListener(this);
            tvAddToCartInList.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onProductListGridItemClickListener != null) {
                switch (v.getId()) {
                    case R.id.iv_share_in_grid:
                    case R.id.iv_share_in_list:
                        onProductListGridItemClickListener.onShareClick(productsArrayList.get(position));
                        break;
                    case R.id.iv_add_to_wishlist_in_grid:
                    case R.id.iv_add_to_wishlist_in_list:
                        onProductListGridItemClickListener.onAddtoWishlistClick(productsArrayList.get(position));
                        break;
                    case R.id.tv_add_to_cart_in_grid:
                    case R.id.tv_add_to_cart_in_list:
                        onProductListGridItemClickListener.onAddtoCartClick(productsArrayList.get(position));
                        break;
                    default:
                        onProductListGridItemClickListener.onItemClick(productsArrayList.get(position));
                        break;
                }
            }
        }
    }


}