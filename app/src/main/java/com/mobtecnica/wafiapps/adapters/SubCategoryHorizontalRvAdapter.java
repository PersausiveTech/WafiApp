package com.mobtecnica.wafiapps.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.CustomAlertDialog;
import com.mobtecnica.wafiapps.model.categories.SubCategories;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby
 */

public class SubCategoryHorizontalRvAdapter extends RecyclerView.Adapter<SubCategoryHorizontalRvAdapter.MainViewHolder> {
    FragmentActivity activity;
    ArrayList<SubCategories> subCategories;
    HomePageCustomViewClickListener clickListener;

    int positionfi = 0;
    private Activity mContext;

    public SubCategoryHorizontalRvAdapter(Activity context, FragmentActivity activity, ArrayList<SubCategories> subCategories) {
        this.activity = activity;
        this.subCategories = subCategories;
        this.mContext = context;
    }

    public void setOnItemClickListener(SubCategoryHorizontalRvAdapter.HomePageCustomViewClickListener myClickListener) {
        this.clickListener = myClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_subsub_categorylist, parent, false);
        return new SubCategoryHorizontalRvAdapter.MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Glide.with(mContext).load(subCategories.get(position).getPictureModel().getFullSizeImageUrl())
                .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                .into(holder.category_image);
        holder.category_name.setText(subCategories.get(position).getName());
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(subCategories.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public void updateItems(ArrayList<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }

    public interface HomePageCustomViewClickListener {
        public void onClicked(int position, View v, String id);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView category_image;
        TextView category_name;


        public MainViewHolder(View itemView) {
            super(itemView);
            try {
                category_image = (ImageView) itemView.findViewById(R.id.iv_item_category);
                category_name = (TextView) itemView.findViewById(R.id.tv_category_name);
                category_image.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            clickListener.onClicked(getAdapterPosition(), v, subCategories.get(getAdapterPosition()).getId());
        }

        public void setAlert(String message, String head) {
            CustomAlertDialog customAlertDialog=   new CustomAlertDialog(message,head, null,null);
            customAlertDialog.show(mContext.getFragmentManager(),"Alert");
        }

        private void progressbarShowing() {
            Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
            intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }

        public void ProgressbarDismiss() {
            Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
            intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, false);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
    }
}
