package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
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
import com.mobtecnica.wafiapps.model.wishlist.newWishList.GetWishListItems;


/**
 * Created by Siby on 27/10/2016.
 */

public class WishListItemsListingRvAdapter extends RecyclerView.Adapter<WishListItemsListingRvAdapter.DataObjectHolder> {
    private static WishListItemsListingRvAdapter.MyClickListener myClickListener;
    public Context context;
    public static ArrayList<GetWishListItems> cartResponses = new ArrayList<>();

    public WishListItemsListingRvAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(WishListItemsListingRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public WishListItemsListingRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_wishlist, parent, false);
        WishListItemsListingRvAdapter.DataObjectHolder dataObjectHolder = new WishListItemsListingRvAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(WishListItemsListingRvAdapter.DataObjectHolder holder, final int position) {
        Glide.with(context).load(cartResponses.get(position).getPicture().getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.iv_product_image);
        holder.product_name.setText(cartResponses.get(position).getProductName());
        holder.product_spec.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(cartResponses.get(position).getAttributeInfo())) {
            holder.product_spec.setText(Html.fromHtml(cartResponses.get(position).getAttributeInfo()));
        } else {
            holder.product_spec.setVisibility(View.GONE);
        }
        holder.et_quantity.setText(cartResponses.get(position).getQuantity());
        holder.tv_price.setText(cartResponses.get(position).getSubTotal());

    }

    public void setDataset(ArrayList<GetWishListItems> cartResponses) {
        this.cartResponses.clear();
        this.cartResponses.addAll(cartResponses);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartResponses.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

        public void onViewClick(GetWishListItems getWishListItems);

    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView delete_2;
        private EditText et_quantity;//ok
        private ImageView iv_plus;
        private ImageView iv_minus;
        private ImageView iv_product_image;
        private LinearLayout wishlist_layout;
        private TextView product_name;
        private TextView vendor_name;
        private TextView tv_price;
        private TextView product_spec;
        private AppCompatButton btn_Add_to_cart;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            product_spec = (TextView) itemView.findViewById(R.id.product_spec);
            vendor_name = (TextView) itemView.findViewById(R.id.vendor_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            wishlist_layout = (LinearLayout) itemView.findViewById(R.id.wishlist_layout);
            iv_product_image = (ImageView) itemView.findViewById(R.id.iv_product_image);
            iv_minus = (ImageView) itemView.findViewById(R.id.iv_minus_);
            et_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            iv_plus = (ImageView) itemView.findViewById(R.id.iv_plus_);
            delete_2 = (ImageView) itemView.findViewById(R.id.iv_delete);
            btn_Add_to_cart = (AppCompatButton) itemView.findViewById(R.id.btn_Add_to_cart);
            try {
                itemView.setOnClickListener(this);
                product_name.setOnClickListener(this);
                product_spec.setOnClickListener(this);
                vendor_name.setOnClickListener(this);
                tv_price.setOnClickListener(this);
                iv_product_image.setOnClickListener(this);
                et_quantity.setOnClickListener(this);
                iv_minus.setOnClickListener(this);
                iv_plus.setOnClickListener(this);
                btn_Add_to_cart.setOnClickListener(this);
                wishlist_layout.setOnClickListener(this);
                delete_2.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_Add_to_cart:
                case R.id.iv_plus_:
                case R.id.iv_minus_:
                case R.id.iv_delete:
                    try {
                        myClickListener.onItemClick(getAdapterPosition(), v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    myClickListener.onViewClick(cartResponses.get(getAdapterPosition()));
                    break;
            }

        }
    }
}
