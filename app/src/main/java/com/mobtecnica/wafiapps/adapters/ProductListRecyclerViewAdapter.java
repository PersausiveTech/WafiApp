package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.Products;

/**
 * Created by SIby on 17-02-2017.
 */

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.MainViewHolder> {
    FragmentActivity activity;
    private Context mContext;
    ProductListCustomClickListner clickListner;
    ArrayList<Products> products;

    ImageView product_image;
    TextView product_model, product_name, product_price, product_old_price, product_availability, product_brand;


    public ProductListRecyclerViewAdapter(Context context, FragmentActivity activity, ArrayList<Products> products) {
        this.mContext = context;
        this.products = products;
        this.activity = activity;
        setHasStableIds(true);
    }

    public void setOnItemClickListener(ProductListRecyclerViewAdapter.ProductListCustomClickListner myClickListener) {
        this.clickListner = myClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_product_listing, parent, false);
        return new ProductListRecyclerViewAdapter.MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateAdapter(ArrayList<Products> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MainViewHolder(View itemView) {
            super(itemView);
            product_image = (ImageView) itemView.findViewById(R.id.iv_product_image_recycler);
            product_name = (TextView) itemView.findViewById(R.id.item_name_product_list);
            product_brand = (TextView) itemView.findViewById(R.id.tv_brand_product_listing);
            product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            product_old_price = (TextView) itemView.findViewById(R.id.tv_product_old_price);
            product_model = (TextView) itemView.findViewById(R.id.product_model_produt_list);
            product_availability = (TextView) itemView.findViewById(R.id.product_availability_product_list);
        }

        public void bindData(int position) {

            Glide.with(mContext)
                    .load(products.get(position).getDefaultPictureModel().getImageUrl())
                    .into(product_image);

            product_name.setText(products.get(position).getName());

            product_price.setText(products.get(position).getProductPrice().getPrice());
            product_old_price.setText(products.get(position).getProductPrice().getOldPrice());

        }

        @Override
        public void onClick(View v) {
            clickListner.onClicked(getAdapterPosition(), v);
        }
    }


    public interface ProductListCustomClickListner {
        public void onClicked(int position, View v);
    }

}
