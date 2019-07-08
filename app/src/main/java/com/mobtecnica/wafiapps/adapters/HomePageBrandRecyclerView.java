package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.home.HomeBrands;

/**
 * Created by SIby on 31-10-2016.
 */
public class HomePageBrandRecyclerView extends RecyclerView.Adapter<HomePageBrandRecyclerView.MainViewHolder> {
    FragmentActivity activity;
    ArrayList<HomeBrands> brandses;
    private Context mContext;

    private HomePageBrandClickListener myClickListener;

    public HomePageBrandRecyclerView(Context context, FragmentActivity activity, ArrayList<HomeBrands> brandses) {
        this.mContext = context;
        this.activity = activity;
        this.brandses = brandses;
    }

    public void setOnItemClickListener(HomePageBrandRecyclerView.HomePageBrandClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_brand, parent, false);
        return new MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        holder.brand.getLayoutParams().width = screenWidth / 3;
        holder.brand.requestLayout();
        if (!TextUtils.isEmpty(brandses.get(position).getPictureModel().getImageUrl())) {
            Glide.with(mContext)
                    .load(brandses.get(position).getPictureModel().getImageUrl())
                    .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                    .into(holder.brand);
        }
    }
    @Override
    public int getItemCount() {
        return brandses.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView brand;

        public MainViewHolder(View itemView) {
            super(itemView);
            brand = (ImageView) itemView.findViewById(R.id.iv_brand_recyclerview);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onClicked(brandses.get(getAdapterPosition()));
        }
    }

    public interface HomePageBrandClickListener {
         void onClicked(HomeBrands homeBrand);
    }

}
