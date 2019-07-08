package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.foodHome.RestaurantProfileFragment;
import com.mobtecnica.wafiapps.model.wafiEats.Offers.AllOffer;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 16-02-2017.
 */

public class OfferGridEats extends BaseAdapter implements View.OnClickListener {
    List<AllOffer> allOffer = null;
    private Context mContext;

    public OfferGridEats(Context mContext, List<AllOffer> allOffer) {
        this.mContext = mContext;
        this.allOffer = allOffer;
    }

    @Override
    public int getCount() {
        return allOffer.size();
    }

    @Override
    public Object getItem(int position) {
        return allOffer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_item_offers, null);
        } else {
            grid = (View) convertView;
        }

        ImageView product_image = (ImageView) grid.findViewById(R.id.iv_item);
        TextView tv_item_name = (TextView) grid.findViewById(R.id.tv_item_name);
        TextView discount = (TextView) grid.findViewById(R.id.discount);
        TextView tv_order_now = (TextView) grid.findViewById(R.id.tv_order_now);

        Glide.with(mContext)
                .load(allOffer.get(position).getHeaderPic())
                .into(product_image);
        tv_item_name.setText(allOffer.get(position).getShopName());
        discount.setText(allOffer.get(position).getDiscount() + "%");
        tv_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RestaurantProfileFragment(allOffer.get(position).getShopID().toString());
                ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                        .addToBackStack(null)
                        .commit();

            }
        });

        return grid;
    }

    @Override
    public void onClick(View v) {

    }


}
