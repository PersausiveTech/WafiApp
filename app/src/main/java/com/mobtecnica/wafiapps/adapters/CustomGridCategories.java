package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.categories.SubCategories;

/**
 * Created by SIby on 03-02-2017.
 */

public class CustomGridCategories extends BaseAdapter {
    private Context mContext;


    SubCategories[] subCategories;

    public CustomGridCategories(Context context, SubCategories[] subCategories) {
        this.mContext = context;
        this.subCategories = subCategories;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return subCategories.length;
    }

    @Override
    public Object getItem(int position) {
        return subCategories[position];
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(subCategories[position].getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_menu, null);
        }
        TextView textView = convertView.findViewById(R.id.item_text);
        ImageView imageView = convertView.findViewById(R.id.item_image);
        textView.setText(subCategories[position].getName());
        Glide.with(mContext)
                .load(subCategories[position].getPictureModel().getImageUrl())
                .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                .into(imageView);
        return convertView;
    }


    public void update(SubCategories[] subCategories) {
        this.subCategories = subCategories;
    }



}