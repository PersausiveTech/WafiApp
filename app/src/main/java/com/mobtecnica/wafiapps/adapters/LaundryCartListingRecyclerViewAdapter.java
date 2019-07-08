package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.LaundryModel.LaundryCart.CartsItem;

import java.util.ArrayList;

/**
 * Created by siby on 27/10/2016.
 */

public class LaundryCartListingRecyclerViewAdapter extends RecyclerView.Adapter<LaundryCartListingRecyclerViewAdapter.DataObjectHolder> {
    private static LaundryCartListingRecyclerViewAdapter.MyClickListener myClickListener;
    public Context context;
    ArrayList<CartsItem> cartResponses;
    ArrayList<String> deletedids;

    public LaundryCartListingRecyclerViewAdapter(Context context, ArrayList<CartsItem> cartResponses) {
        this.context = context;
        this.cartResponses = cartResponses;

    }

    public void setOnItemClickListener(LaundryCartListingRecyclerViewAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public LaundryCartListingRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (cartResponses.size() > 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_laundry_cart, parent, false);
            LaundryCartListingRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new LaundryCartListingRecyclerViewAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_no_data, parent, false);
            LaundryCartListingRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new LaundryCartListingRecyclerViewAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        }

    }

    @Override
    public void onBindViewHolder(LaundryCartListingRecyclerViewAdapter.DataObjectHolder holder, final int position) {
        if (cartResponses.size() > 0) {
            deletedids = ObjectFactory.getInstance().getAppPreference(context).getDeleteIdsLaundry();
            if (deletedids.contains(cartResponses.get(position).getItemId())) {
                holder.cb_wishlist_select.setChecked(true);
            } else {
                holder.cb_wishlist_select.setChecked(false);
            }
            Glide.with(context).load(cartResponses.get(position).getImageUrl())
                    .thumbnail(Glide.with(context).load(R.drawable.loading))
                    .into(holder.iv_product_image);
            holder.product_name.setText(cartResponses.get(position).getTitle());

            holder.et_quantity.setText(cartResponses.get(position).getQuantity());
            holder.tv_price.setText(context.getString(R.string.bd) + cartResponses.get(position).getAmount());
            holder.wash_type_laundry.setText(cartResponses.get(position).getServiceType());
        } else {

        }

    }

    public void setDataset(ArrayList<CartsItem> cartResponses) {
        this.cartResponses = cartResponses;
    }

    @Override
    public int getItemCount() {
        if (cartResponses.size() > 0) {
            return cartResponses.size();
        } else {
            return 1;
        }
    }

    public int getNoOfItems() {
        if (cartResponses != null) {
            return cartResponses.size();
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    public void updateList(ArrayList<CartsItem> data) {
        this.cartResponses = data;
        notifyDataSetChanged();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText et_quantity;
        private ImageView iv_plus;
        private ImageView iv_minus;
        private CheckBox cb_wishlist_select;
        private ImageView iv_product_image;
        private TextView product_name;
        private TextView tv_price;
        private TextView wash_type_laundry;
        private LinearLayout remove_from_cart;


        public DataObjectHolder(final View itemView) {
            super(itemView);
            cb_wishlist_select = (CheckBox) itemView.findViewById(R.id.cb_wishlist_select_laundry);
            product_name = (TextView) itemView.findViewById(R.id.product_name_laundry);
            wash_type_laundry = (TextView) itemView.findViewById(R.id.wash_type_laundry);

            tv_price = (TextView) itemView.findViewById(R.id.tv_price_laundry);
            iv_product_image = (ImageView) itemView.findViewById(R.id.iv_product_image_laundry);
            iv_minus = (ImageView) itemView.findViewById(R.id.iv_minus);
            remove_from_cart = (LinearLayout) itemView.findViewById(R.id.remove_from_cart);
            et_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            iv_plus = (ImageView) itemView.findViewById(R.id.iv_plus);
            try {
                itemView.setOnClickListener(this);
                product_name.setOnClickListener(this);
                tv_price.setOnClickListener(this);
                iv_product_image.setOnClickListener(this);
                et_quantity.setOnClickListener(this);
                iv_minus.setOnClickListener(this);
                iv_plus.setOnClickListener(this);
                remove_from_cart.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            try {
                myClickListener.onItemClick(getAdapterPosition(), v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void reduceQuantity() {
            String st = et_quantity.getText().toString().trim();
            int num = Integer.parseInt(st);
            if (num > 1)
                num = num - 1;
            et_quantity.setText(String.valueOf(num));
        }

        private void addQuantity() {
            String st = et_quantity.getText().toString().trim();
            int num = Integer.parseInt(st);
            if (num >= 0 && num <= 4)
                num = num + 1;
            et_quantity.setText(String.valueOf(num));
        }
    }
}
