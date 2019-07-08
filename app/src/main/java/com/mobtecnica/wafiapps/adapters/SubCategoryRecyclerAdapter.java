package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.categories.SubCategories;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by SIby on 16-02-2017.
 */

public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<SubCategoryRecyclerAdapter.DataObjectHolder> {
    private MyClickListener myClickListener;
    private Context context;
    private ArrayList<SubCategories> subCategoriesArrayList = new ArrayList<>();

    public SubCategoryRecyclerAdapter(Context context) {
        this.context = context;
        }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public SubCategoryRecyclerAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_category_list, parent, false);
        SubCategoryRecyclerAdapter.DataObjectHolder dataObjectHolder = new SubCategoryRecyclerAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(SubCategoryRecyclerAdapter.DataObjectHolder holder, final int position) {
        holder.category_name.setText(subCategoriesArrayList.get(position).getName());
        Glide.with(context).load(subCategoriesArrayList.get(position).getPictureModel().getFullSizeImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.category_image);
    }

    public void setData(SubCategories[] subCategories) {
        subCategoriesArrayList.clear();
        subCategoriesArrayList.addAll(Arrays.asList(subCategories));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return subCategoriesArrayList.size();
    }

    public interface MyClickListener {
        void onItemClick(SubCategories subCategory);
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category_name;
        CircleImageView category_image;

        public DataObjectHolder(View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.tv_category_name);
            category_image = itemView.findViewById(R.id.iv_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(subCategoriesArrayList.get(getAdapterPosition()));
        }
    }
}
