package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.categories.SubCategories;

/**
 * Created by vishnu on 27/10/2016.
 */

public class SubCategoryListRvAdapter extends RecyclerView.Adapter<SubCategoryListRvAdapter.DataObjectHolder> {
    private static SubCategoryListRvAdapter.MyClickListener myClickListener;
    public Context context;
    SubCategories[] subCategories;
    ArrayList<String> deletedids;

    public SubCategoryListRvAdapter(Context context, SubCategories[] subCategories) {
        this.context = context;
        this.subCategories = subCategories;

    }

    public void setOnItemClickListener(SubCategoryListRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public SubCategoryListRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_subcategory, parent, false);
        SubCategoryListRvAdapter.DataObjectHolder dataObjectHolder = new SubCategoryListRvAdapter.DataObjectHolder(view);
        return dataObjectHolder;

    }

    @Override
    public void onBindViewHolder(SubCategoryListRvAdapter.DataObjectHolder holder, final int position) {
        if (subCategories.length > 0) {
            Glide.with(context).load(subCategories[position].getPictureModel().getImageUrl()).into(holder.iv_item);
            holder.name.setText(subCategories[position].getName());
        } else {

        }

    }

    @Override
    public int getItemCount() {
        return subCategories.length;

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView iv_item;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            iv_item = (ImageView) itemView.findViewById(R.id.iv_item);
            name = (TextView) itemView.findViewById(R.id.tv_name);
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
