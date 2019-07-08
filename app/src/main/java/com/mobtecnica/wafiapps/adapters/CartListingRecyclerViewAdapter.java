package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items;

/**
 * Created by siby on 27/10/2016.
 */

public class CartListingRecyclerViewAdapter extends RecyclerView.Adapter<CartListingRecyclerViewAdapter.DataObjectHolder> {
    private static CartListingRecyclerViewAdapter.CartItemClickListener myClickListener;
    public Context context;
    public ArrayList<Items> itemsArrayList = new ArrayList<>();

    public CartListingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(CartListingRecyclerViewAdapter.CartItemClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public CartListingRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_cart, parent, false);
        return new CartListingRecyclerViewAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(CartListingRecyclerViewAdapter.DataObjectHolder holder, final int position) {
        Glide.with(context).load(itemsArrayList.get(position).getPicture().getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.iv_product_image);
        holder.product_name.setText(itemsArrayList.get(position).getProductName());
        holder.product_spec.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(itemsArrayList.get(position).getAttributeInfo())) {
            holder.product_spec.setText(Html.fromHtml(itemsArrayList.get(position).getAttributeInfo()));
        } else {
            holder.product_spec.setVisibility(View.GONE);
        }
        holder.et_quantity.setText(String.valueOf(itemsArrayList.get(position).getQuantity()));
        holder.tv_price.setText(itemsArrayList.get(position).getSubTotal());
        holder.position = position;
    }

    public void updateData(ArrayList<Items> cartResponses) {
        int itemCountBeforeUpdate = itemsArrayList.size();
        this.itemsArrayList.addAll(cartResponses);
        notifyItemRangeInserted(itemCountBeforeUpdate, this.itemsArrayList.size());
    }

    public void clearData() {
        notifyItemRangeRemoved(0, itemsArrayList.size());
        itemsArrayList.clear();
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    public interface CartItemClickListener {

        public void onAddItemQuantity(Items item);

        public void onReduceItemQuantity(Items item);

        public void onDeleteItem(Items item);

        public void onItemClick(Items item);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText et_quantity;
        private ImageView iv_plus;
        private ImageView iv_minus;
        private LinearLayout layout_cart_item;
        private ImageView iv_delete;
        private ImageView iv_product_image;
        private TextView product_name;
        private TextView tv_price;
        private TextView product_spec;
        private int position;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            product_spec = (TextView) itemView.findViewById(R.id.product_spec);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            layout_cart_item = (LinearLayout) itemView.findViewById(R.id.layout_cart_item);
            iv_product_image = (ImageView) itemView.findViewById(R.id.iv_product_image);
            iv_minus = (ImageView) itemView.findViewById(R.id.iv_minus_);
            et_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            iv_plus = (ImageView) itemView.findViewById(R.id.iv_plus_);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete_cart_item);
            try {
                iv_minus.setOnClickListener(this);
                iv_plus.setOnClickListener(this);
                iv_delete.setOnClickListener(this);
                layout_cart_item.setOnClickListener(this);
                itemView.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null && itemsArrayList != null && !itemsArrayList.isEmpty()) {
                switch (v.getId()) {
                    case R.id.iv_minus_:
                        myClickListener.onReduceItemQuantity(itemsArrayList.get(position));
                        break;
                    case R.id.iv_plus_:
                        myClickListener.onAddItemQuantity(itemsArrayList.get(position));
                        break;
                    case R.id.iv_delete_cart_item:
                        myClickListener.onDeleteItem(itemsArrayList.get(position));
                        break;
                    default:
                        myClickListener.onItemClick(itemsArrayList.get(position));
                        break;
                }
            }
        }
    }
}
