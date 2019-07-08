package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;

import java.util.ArrayList;

/**
 * Created by vishnu on 27/10/2016.
 */

public class SelectAddressRvAdapter extends RecyclerView.Adapter<SelectAddressRvAdapter.DataObjectHolder> {
    private static SelectAddressRvAdapter.MyClickListener myClickListener;
    private Context context;
    private ArrayList<Addresses> products;

    public SelectAddressRvAdapter(Context context, ArrayList<Addresses> product) {
        this.context = context;
        products = product;
    }

    public void setOnItemClickListener(SelectAddressRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public SelectAddressRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (products.size() > 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_address, parent, false);
            SelectAddressRvAdapter.DataObjectHolder dataObjectHolder = new SelectAddressRvAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_no_data_address, parent, false);
            SelectAddressRvAdapter.DataObjectHolder dataObjectHolder = new SelectAddressRvAdapter.DataObjectHolder(view);
            return dataObjectHolder;
        }

    }

    @Override
    public void onBindViewHolder(SelectAddressRvAdapter.DataObjectHolder holder, final int position) {
        if (products.size() > 0) {
            holder.name.setText(products.get(position).getFirstName() + " " + products.get(position).getLastName());
            holder.phone.setText("Mob :" + products.get(position).getPhoneNumber());
            holder.address.setText(Html.fromHtml(products.get(position).getFormattedCustomAddressAttributes()));
        }


    }

    public void setDataset(ArrayList<Addresses> product) {
        this.products = product;
    }

    @Override
    public int getItemCount() {
        if (products.size() > 0) {
            return products.size();
        } else {
            return 1;
        }
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView address;
        TextView phone;
        CheckBox checkBox;

        public DataObjectHolder(View itemView) {
            super(itemView);
            try {
                name = (TextView) itemView.findViewById(R.id.tv_name_user);
                address = (TextView) itemView.findViewById(R.id.tv_address_user);
                phone = (TextView) itemView.findViewById(R.id.tv_mob_user);
                checkBox = (CheckBox) itemView.findViewById(R.id.cb_address_add);
                itemView.setOnClickListener(this);
                checkBox.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null) {
                myClickListener.onItemClick(getAdapterPosition(), v);
            }
        }
    }
}
