package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.home.HomeCategories;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Jilsha on 08-04-2018.
 */

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.CustomViewHolder> {
    ArrayList<HomeCategories> homeCategories;
    HomeCategoriesAdapter.HomePageCustomViewClickListener clickListener;
    private Context mContext;

    public HomeCategoriesAdapter(Context context, ArrayList<HomeCategories> homeCategories) {
        this.homeCategories = homeCategories;
        this.mContext = context;
    }

    public void setOnItemClickListener(HomeCategoriesAdapter.HomePageCustomViewClickListener myClickListener) {
        this.clickListener = myClickListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_category_home_new, parent, false);
        return new HomeCategoriesAdapter.CustomViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (homeCategories.get(position).getName().equalsIgnoreCase(mContext.getResources().getString(R.string.laundry_label))) {
            holder.category_image.setImageResource(R.drawable.laundry_new);
        } else if (homeCategories.get(position).getName().equalsIgnoreCase(mContext.getResources().getString(R.string.view_all))) {
            holder.category_image.setImageResource(R.drawable.view_all);
        } else {
            Glide.with(mContext).load(homeCategories.get(position).getPictureModel().getImageUrl())
                    .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.category_image);
            /*if(homeCategories.get(position).getName().equalsIgnoreCase("deals")){
                holder.category_image.setLayoutParams(new LinearLayout.LayoutParams((int)Utils.convertDpToPixel(50,mContext),(int)Utils.convertDpToPixel(50,mContext)));
            }else{
                holder.category_image.setLayoutParams(new LinearLayout.LayoutParams((int)Utils.convertDpToPixel(38,mContext),(int)Utils.convertDpToPixel(38,mContext)));
            }*/
            holder.category_image.setLayoutParams(new LinearLayout.LayoutParams((int)Utils.convertDpToPixel(50,mContext),(int)Utils.convertDpToPixel(50,mContext)));
        }
        holder.category_name.setText(homeCategories.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return homeCategories.size();
    }

    public interface HomePageCustomViewClickListener {
        void onClicked(int position, HomeCategories homeCategories);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView category_image;
        TextView category_name;
        LinearLayout linearLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);
            category_image =  itemView.findViewById(R.id.iv_product_image);
            category_name =  itemView.findViewById(R.id.tv_name);
            linearLayout =  itemView.findViewById(R.id.grid_layout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClicked(getAdapterPosition(), homeCategories.get(getAdapterPosition()));
        }
    }

}
