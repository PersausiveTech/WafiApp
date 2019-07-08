package com.mobtecnica.wafiapps.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.wafi_main.HomeFragment;
import com.mobtecnica.wafiapps.model.deals.Deal;
import com.mobtecnica.wafiapps.utils.Utils;

import java.util.ArrayList;

public class HomePageDealsOfWeek extends RecyclerView.Adapter<HomePageDealsOfWeek.MainViewHolder> {


    private final Context mContext;
    ArrayList<Deal> dealArraylist = new ArrayList<>();
    private Activity activity;
    private HomePageDealsOfWeekClickListener myClickListener;

    public HomePageDealsOfWeek(Context context, Activity activity) {
        this.mContext = context;
        this.activity = activity;
    }

    public void setData(ArrayList<Deal> dealArraylist) {
        this.dealArraylist.clear();
        this.dealArraylist.addAll(dealArraylist);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(HomePageDealsOfWeek.HomePageDealsOfWeekClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deals_of_week_imageview, parent, false);
        return new MainViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Glide.with(mContext)
                .load(dealArraylist.get(position).getAppPictureModel().getFullSizeImageUrl())
                .thumbnail(Glide.with(mContext).load(R.drawable.loading))
                .into(holder.dealsImage);

    }

    @Override
    public int getItemCount() {
        return dealArraylist.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView dealsImage;
        CardView cvImage;

        public MainViewHolder(View itemView) {
            super(itemView);
            dealsImage = itemView.findViewById(R.id.deals_of_week_image);
            cvImage = itemView.findViewById(R.id.cvImage);
            itemView.setOnClickListener(this);

            /*cvImage.setLayoutParams(new ConstraintLayout.LayoutParams(Utils.getDeviceWidth(activity)-(int)Utils.convertDpToPixel(5,mContext),
                    (int)Utils.convertDpToPixel(180,mContext)));*/
        }

        @Override
        public void onClick(View v) {
            myClickListener.onClicked(dealArraylist.get(getAdapterPosition()));
        }
    }

    public interface HomePageDealsOfWeekClickListener {
        void onClicked(Deal deal);
    }
}
