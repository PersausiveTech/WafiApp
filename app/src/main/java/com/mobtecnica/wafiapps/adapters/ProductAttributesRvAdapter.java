package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.HomeManager;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.cart.addToCart.Product_attributesCart;
import com.mobtecnica.wafiapps.model.productDetails.Values;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SIby on 13-03-2017.
 */

public class ProductAttributesRvAdapter extends RecyclerView.Adapter<ProductAttributesRvAdapter.DataObjectHolder> {
    public static int pos = 0;
    private static ProductAttributesRvAdapter.MyClickListener myClickListener;
    private String attribute_name1;
    private Context context;
    private ArrayList<Values> products;
    private List<Product_attributesCart> items = new ArrayList<>();

    public ProductAttributesRvAdapter(Context context, ArrayList<Values> product, String attribute_name) {
        this.context = context;
        products = product;
        attribute_name1 = attribute_name;
    }

    public void setOnItemClickListener(ProductAttributesRvAdapter.MyClickListener myClickListener) {
        try {
            this.myClickListener = myClickListener;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductAttributesRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_product_spec, parent, false);
        ProductAttributesRvAdapter.DataObjectHolder dataObjectHolder = new ProductAttributesRvAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ProductAttributesRvAdapter.DataObjectHolder holder, final int position) {
        final ProductAttributesRvAdapter.DataObjectHolder objectHolder = holder;
        holder.product_attribute_name.setText( getFormattedString(products.get(position)));
        items = ObjectFactory.getInstance().getHomeManager(context).getAttribueSet();
        for (int i = 0; i < items.size(); i++) {
            if (products.get(position).getId().matches(items.get(i).getValue())) {
                try {
                    objectHolder.iv_product_color.setBackgroundColor(Color.parseColor(products.get(position).getColorSquaresRgb()));
                } catch (Exception e) {
                    objectHolder.iv_product_color.setBackgroundColor(Color.BLACK);
                    e.printStackTrace();
                }
            }

        }

        holder.iv_product_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String attributes_value = products.get(position).getId();
                ObjectFactory.getInstance().getHomeManager(context).setAttributes(attribute_name1, attributes_value);

                Intent intentRes = new Intent(HomeManager.BROADCAST_UPDATE_FIELD);
                intentRes.putExtra(HomeManager.BROADCAST_UPDATE_FIELD_STATUS, true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentRes);
                try {
                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private CharSequence getFormattedString(Values values) {
        String formattedString = "";
        formattedString = values.getName();
        SpannableString ss1=  new SpannableString(formattedString);
        String priceAdjustment=values.getPriceAdjustment();
        if (!TextUtils.isEmpty(priceAdjustment)) {
            SpannableString ss2=  new SpannableString(priceAdjustment);
            ss2.setSpan(new RelativeSizeSpan(.75f), 0,priceAdjustment.length(), 0); // set size
            ss2.setSpan(new ForegroundColorSpan(Color.GRAY), 0, priceAdjustment.length(), 0);// set color
            return TextUtils.concat(ss1, "\n", ss2);
        }
        return ss1.toString();
    }

    public void setDataset(ArrayList<Values> mDataset) {
        this.products = mDataset;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, int grouppos, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView iv_product_color;
        TextView product_attribute_name;
        public DataObjectHolder(View itemView) {
            super(itemView);
            product_attribute_name = (TextView) itemView.findViewById(R.id.product_attribute_name);
            iv_product_color = (CircleImageView) itemView.findViewById(R.id.iv_product_color);
            itemView.setOnClickListener(this);
            product_attribute_name.setOnClickListener(this);
            iv_product_color.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), ProductAttributesRvAdapter.pos, v);
        }
    }
}
