package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartItem;

/**
 */

public class EatsCartListingRecyclerViewAdapter extends RecyclerView.Adapter<EatsCartListingRecyclerViewAdapter.DataObjectHolder> {
    private static EatsCartListingRecyclerViewAdapter.MyClickListener myClickListener;
    public Context context;
    List<CartItem> cartResponses;
    ArrayList<String> deletedids;

    public EatsCartListingRecyclerViewAdapter(Context context, List<CartItem> cartResponses) {
        this.context = context;
        this.cartResponses = cartResponses;
    }

    public void setOnItemClickListener(EatsCartListingRecyclerViewAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public EatsCartListingRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_cart_eats, parent, false);
        EatsCartListingRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new EatsCartListingRecyclerViewAdapter.DataObjectHolder(view);
        return dataObjectHolder;

    }

    @Override
    public void onBindViewHolder(EatsCartListingRecyclerViewAdapter.DataObjectHolder holder, int position) {
        if (cartResponses.size() > 0) {
            try {
                if (cartResponses.get(position).getHeaderPic() != null)
                    Glide.with(context).load(cartResponses.get(position).getHeaderPic()).into(holder.iv_item_cart);
                holder.tv_menu_name.setText(cartResponses.get(position).getMenu());
                holder.tv_price.setText("Sub Total(BD) :" + cartResponses.get(position).getRate());
                String items = "";
                for (int i = 0; i < cartResponses.get(position).getLstCartSubItems().size(); i++) {
                    items = items + cartResponses.get(position).getLstCartSubItems().get(i).getMenuSubItem() + ",";
                }
                holder.tv_sub_items.setText("(" + items + ")");
                holder.tv_quantity.setText("Qty :" + cartResponses.get(position).getQuantity());
            } catch (Exception e) {
            }
        }
    }


    @Override
    public int getItemCount() {
        return cartResponses.size();

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setDataset(List<CartItem> cartItems) {
        this.cartResponses = cartItems;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_item_cart;
        private ImageView iv_delete_items;
        private AppCompatTextView tv_menu_name;
        private AppCompatTextView tv_sub_items;
        private AppCompatTextView tv_price;
        private AppCompatTextView tv_quantity;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            try {
                iv_item_cart = (ImageView) itemView.findViewById(R.id.iv_item_cart);
                iv_delete_items = (ImageView) itemView.findViewById(R.id.iv_delete_items);
                tv_menu_name = (AppCompatTextView) itemView.findViewById(R.id.tv_menu_name);
                tv_sub_items = (AppCompatTextView) itemView.findViewById(R.id.tv_sub_items);
                tv_price = (AppCompatTextView) itemView.findViewById(R.id.tv_price);
                tv_quantity = (AppCompatTextView) itemView.findViewById(R.id.tv_quantity);
                iv_delete_items.setOnClickListener(this);
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


    }
}
