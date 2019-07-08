package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.RestaurantsInArea;

/**
 * Created by siby on 27/10/2016.
 */

public class ResturantsListRvAdapter extends RecyclerView.Adapter<ResturantsListRvAdapter.DataObjectHolder> {
    private static ResturantsListRvAdapter.MyClickListener myClickListener;
    public Context context;
    List<RestaurantsInArea> restaurantsInAreas;
    ArrayList<String> deletedids;

    public ResturantsListRvAdapter(Context context, List<RestaurantsInArea> restaurantsInAreas) {
        this.context = context;
        this.restaurantsInAreas = restaurantsInAreas;

    }

    public void setOnItemClickListener(ResturantsListRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public ResturantsListRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (restaurantsInAreas.size() > 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_resturants_list, parent, false);
            ResturantsListRvAdapter.DataObjectHolder dataObjectHolder = new ResturantsListRvAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_no_data, parent, false);
            ResturantsListRvAdapter.DataObjectHolder dataObjectHolder = new ResturantsListRvAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        }
    }

    @Override
    public void onBindViewHolder(ResturantsListRvAdapter.DataObjectHolder holder, final int position) {
        if (restaurantsInAreas.size() > 0) {
            holder.tv_is_open.setText(restaurantsInAreas.get(position).getOpen());
            holder.tv_delivery_charge.setText(context.getString(R.string.delivery_charges)+restaurantsInAreas.get(position).getDeliveryCharge());
            holder.tv_delivery_time.setText(context.getString(R.string.delivery_time)+restaurantsInAreas.get(position).getTimings());
            holder.tv_min_order.setText(context.getString(R.string.min_order)+restaurantsInAreas.get(position).getMinOrder());
            holder.tv_name.setText(restaurantsInAreas.get(position).getShopName());
            Glide.with(context)
                    .load(restaurantsInAreas.get(position).getHeaderPic())
                    .into(holder.iv_resturant_img);
        } else {

        }

    }

    public void setDataset(List<RestaurantsInArea> restaurantsInAreas) {
        this.restaurantsInAreas = restaurantsInAreas;
    }

    @Override
    public int getItemCount() {
        if (restaurantsInAreas.size() > 0) {
            return restaurantsInAreas.size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_is_open;
        private ImageView iv_resturant_img;
        private TextView tv_is_open;
        private TextView tv_name;
        private TextView tv_min_order;
        private TextView tv_delivery_time;
        private TextView tv_delivery_charge;
        private RatingBar ratingbar;
        private AppCompatButton view_menu;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            iv_is_open = (ImageView) itemView.findViewById(R.id.iv_is_open);
            iv_resturant_img = (ImageView) itemView.findViewById(R.id.iv_resturant_img);
            tv_is_open = (TextView) itemView.findViewById(R.id.tv_is_open);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_min_order = (TextView) itemView.findViewById(R.id.tv_min_order);
            tv_delivery_time = (TextView) itemView.findViewById(R.id.tv_delivery_time);
            tv_delivery_charge = (TextView) itemView.findViewById(R.id.tv_delivery_charge);
            ratingbar = (RatingBar) itemView.findViewById(R.id.ratingbar);
            view_menu = (AppCompatButton) itemView.findViewById(R.id.view_menu);
            try {
                view_menu.setOnClickListener(this);
                itemView.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            try {
                myClickListener.onItemClick(getAdapterPosition(), v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
