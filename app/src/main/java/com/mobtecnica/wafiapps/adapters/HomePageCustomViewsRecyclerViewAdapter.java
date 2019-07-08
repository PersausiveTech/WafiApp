package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.home.HomeCustomProducts;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 14-02-2017.
 */

public class HomePageCustomViewsRecyclerViewAdapter extends RecyclerView.Adapter<HomePageCustomViewsRecyclerViewAdapter.MainViewHolder> {
    FragmentActivity activity;
    ArrayList<HomeCustomProducts> productses;
    HomePageCustomViewClickListener clickListener;

    private Context mContext;

    public HomePageCustomViewsRecyclerViewAdapter(Context context, FragmentActivity activity, ArrayList<HomeCustomProducts> productses) {
        this.activity = activity;
        this.productses = productses;
        this.mContext = context;
    }

    public void setOnItemClickListener(HomePageCustomViewsRecyclerViewAdapter.HomePageCustomViewClickListener myClickListener) {
        this.clickListener = myClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_custom_products, parent, false);
        return new HomePageCustomViewsRecyclerViewAdapter.MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(productses.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return productses.size();
    }

    public void updateItems(ArrayList<HomeCustomProducts> custom_products) {
        this.productses = custom_products;
    }

    public interface HomePageCustomViewClickListener {
        void onClicked(HomeCustomProducts homeCustomProduct);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView product_image;
        TextView product_name;
        TextView tv_price;
        TextView tv_old_price;


        public MainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            try {
                product_image = (ImageView) itemView.findViewById(R.id.iv_item_image_custom_products);
                product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
                tv_price = (TextView) itemView.findViewById(R.id.tv_price);
                tv_old_price = (TextView) itemView.findViewById(R.id.tv_old_price);
                tv_price.setVisibility(View.VISIBLE);
                tv_old_price.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void bindData(int position) {
            Glide.with(mContext)
                    .load(productses
                            .get(position)
                            .getDefaultPictureModel()
                            .getImageUrl())
                    .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                    .into(product_image);
            product_name.setText(productses.get(position).getName());
            tv_price.setText(productses.get(position).getProductPrice().getPrice());
            tv_old_price.setText(productses.get(position).getProductPrice().getOldPrice());
            if (TextUtils.isEmpty(productses.get(position).getProductPrice().getOldPrice())) {
                tv_old_price.setVisibility(View.GONE);
            }
            tv_old_price.setPaintFlags(tv_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        @Override
        public void onClick(View v) {
            clickListener.onClicked(productses.get(getAdapterPosition()));
        }

    }
}
