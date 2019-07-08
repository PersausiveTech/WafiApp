package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.productDetails.PictureModels;

/**
 * Created by SIby on 13-03-2017.
 */

public class ProductImageViewPager extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<PictureModels> product_images;


    public ProductImageViewPager(Context mContext, ArrayList<PictureModels> product_images) {
        this.mContext = mContext;
        this.product_images = product_images;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateContext(ArrayList<PictureModels> product_images) {
        this.product_images = product_images;

    }

    @Override
    public int getCount() {
        return product_images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_image_product_details, container, false);

        PhotoView imageView = (PhotoView) itemView.findViewById(R.id.pager_iv_home);
        // imageView.setImageResource(mResources[position]);
        Glide.with(mContext)
                .load(product_images.get(position).getFullSizeImageUrl())
//                .thumbnail(Glide.with(mContext).load(R.drawable.spinningwheel))
                .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                .into(imageView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
