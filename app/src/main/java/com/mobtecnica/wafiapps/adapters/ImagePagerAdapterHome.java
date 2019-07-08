package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.home.HomeSliders;

/**
 * Created by SIby on 09-02-2017.
 */

public class ImagePagerAdapterHome extends PagerAdapter implements View.OnClickListener {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<HomeSliders> homeSliderses;

    private OnSliderItemClickListener sliderItemClickListener;

    public ImagePagerAdapterHome(Context mContext, ArrayList<HomeSliders> homeSliderses) {
        this.mContext = mContext;
        this.homeSliderses = homeSliderses;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateContext(ArrayList<HomeSliders> categories) {
        this.homeSliderses = categories;

    }

    @Override
    public int getCount() {
        return homeSliderses.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_image_sliding_home, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.pager_iv_home);
        View container_slider_image =  itemView.findViewById(R.id.container_slider_image);
        Glide.with(mContext)
                .load(homeSliderses.get(position).getImageUrl())
                .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                .into(imageView);

        container.addView(itemView);
        container_slider_image.setTag(position);
        container_slider_image.setOnClickListener(this);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((LinearLayout) object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSliderItemClickListener(OnSliderItemClickListener sliderItemClickListener) {
        this.sliderItemClickListener = sliderItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (sliderItemClickListener != null && homeSliderses != null) {
            try {
                sliderItemClickListener.onItemClick(homeSliderses.get((int) v.getTag()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public interface OnSliderItemClickListener {
        void onItemClick(HomeSliders homeSlider);
    }
}
