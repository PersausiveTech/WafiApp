package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.fragments.wafi_main.CartFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.HomeFragment;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Items;

import java.util.ArrayList;

public class CartItemsInHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static CartItemsInHomeAdapter.cartItemsInHomeAdapter myClickListener;
    private final ArrayList<Items> cartResponses;
    private final Context context;
    private final int PRODUCT_VIEW = 0;
    private final int MORE_PRODUCT_VIEW = 1;
    private HomeFragment fragment;
    public CartItemsInHomeAdapter (ArrayList<Items> cartResponses, Context context, HomeFragment fragment){
        this.cartResponses=cartResponses;
        this.context=context;
        this.fragment = fragment;
    }
    public void setOnItemClickListener(CartItemsInHomeAdapter.cartItemsInHomeAdapter myClickListener) {
        this.myClickListener = myClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =null;
        switch (viewType){
            case PRODUCT_VIEW:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_home_layout,parent,false);
                return new CartItemsInHomeAdapter.DataObjectHolder(view);
            case MORE_PRODUCT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_products, parent, false);
                return new CartItemsInHomeAdapter.MoreProductViewHolder(view);
        }
        return null;
    }
    @Override
    public int getItemViewType(int position) {
        if(cartResponses!=null && cartResponses.size()>3){
            if(position==3){
                return MORE_PRODUCT_VIEW;
            }else{
                return PRODUCT_VIEW;
            }
        }else{
            return PRODUCT_VIEW;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case PRODUCT_VIEW:
                CartItemsInHomeAdapter.DataObjectHolder mainHolder = (CartItemsInHomeAdapter.DataObjectHolder)holder;
                Glide.with(context).load(cartResponses.get(position).getPicture().getImageUrl())
                        .thumbnail(Glide.with(context).load(R.drawable.loading))
                        .into(mainHolder.cart_image);
                mainHolder.item_name.setText(cartResponses.get(position).getProductName());

                mainHolder.item_qty.setText(context.getString(R.string.qty)+String.valueOf(cartResponses.get(position).getQuantity()));
                mainHolder.actual_price.setText(cartResponses.get(position).getSubTotal());
                mainHolder.position=position;
                break;

            case MORE_PRODUCT_VIEW:
                CartItemsInHomeAdapter.MoreProductViewHolder moreProductHolder = (CartItemsInHomeAdapter.MoreProductViewHolder)holder;
                break;
        }

    }

    @Override
    public int getItemCount() {
        if(cartResponses!=null && cartResponses.size()>3){
            return 4;
        }else{
            return cartResponses.size();
        }
    }
    public interface cartItemsInHomeAdapter{
        public void onClickListener(Items item);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView cart_image;
        TextView item_name;
        TextView item_qty;
        TextView actual_price;
        Button proceed;
        int position;
        public DataObjectHolder(View itemView) {
            super(itemView);
            cart_image=(ImageView)itemView.findViewById(R.id.img_cart_home);
            item_name=(TextView)itemView.findViewById(R.id.cart_item_title);
            item_qty=(TextView)itemView.findViewById(R.id.cart_item_qty);
            actual_price=(TextView)itemView.findViewById(R.id.discount_price);
            proceed=(Button) itemView.findViewById(R.id.btn_proceed);
            proceed.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null && cartResponses != null && !cartResponses.isEmpty()) {
                switch (v.getId()){
                    case (R.id.btn_proceed):
                        myClickListener.onClickListener(cartResponses.get(position));
                }
            }

        }
    }
    class MoreProductViewHolder extends RecyclerView.ViewHolder {
        public MoreProductViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.tv_review_cart).setOnClickListener(v->{
                if(fragment!=null)  fragment.navigateToCartList();
            });
        }
    }
}
