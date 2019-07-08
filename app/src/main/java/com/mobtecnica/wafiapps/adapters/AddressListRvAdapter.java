package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.wafi_main.AddressListFragment;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;

import java.util.ArrayList;

/**
 * Created by vishnu on 27/10/2016.
 */

public class AddressListRvAdapter extends RecyclerView.Adapter<AddressListRvAdapter.DataObjectHolder> {
    private static MyClickListener myClickListener;
    private Context context;
    private ArrayList<Addresses> addressesArrayList = new ArrayList<>();

    public AddressListRvAdapter(Context context) {
        this.context = context;
    }

    public void updateData(ArrayList<Addresses> addressesArrayList) {
        this.addressesArrayList.clear();
        this.addressesArrayList.addAll(addressesArrayList);
        notifyDataSetChanged();

    }

    public Addresses getItem(int position) {
        if (addressesArrayList != null && !addressesArrayList.isEmpty()) {
            return addressesArrayList.get(position);
        }
        return null;
    }

    public void setOnItemClickListener(AddressListRvAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public AddressListRvAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_address_list, parent, false);
            AddressListRvAdapter.DataObjectHolder dataObjectHolder = new AddressListRvAdapter.DataObjectHolder(view);
            return dataObjectHolder;
//
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_no_data_address, parent, false);
//            AddressListRvAdapter.DataObjectHolder dataObjectHolder = new AddressListRvAdapter.DataObjectHolder(view);
//            return dataObjectHolder;


    }

    @Override
    public void onBindViewHolder(AddressListRvAdapter.DataObjectHolder holder, final int position) {
        if (addressesArrayList.size() > 0) {
            holder.name.setText(addressesArrayList.get(position).getFirstName() + " " + addressesArrayList.get(position).getLastName());
            holder.phone.setText("Mob :" + addressesArrayList.get(position).getPhoneNumber());
            holder.address.setText(Html.fromHtml(addressesArrayList.get(position).getFormattedCustomAddressAttributes()));
            holder.position = position;
        }
    }

    public void setDataset(ArrayList<Addresses> product) {
        this.addressesArrayList = product;
    }

    @Override
    public int getItemCount() {
        if (addressesArrayList.size() > 0) {
            return addressesArrayList.size();
        } else {
            return 1;
        }
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);

        public void onDelete(Addresses address,int position);

        public void onEdit(Addresses address ,int position);

        public void onSelect(Addresses address,int position);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView address;
        TextView phone;
        ImageView iv_edit;
        ImageView iv_delete;
        int position;

        public DataObjectHolder(View itemView) {
            super(itemView);
            try {
                name = (TextView) itemView.findViewById(R.id.tv_name_user);
                address = (TextView) itemView.findViewById(R.id.tv_address_user);
                phone = (TextView) itemView.findViewById(R.id.tv_mob_user);
                iv_edit = (ImageView) itemView.findViewById(R.id.iv_address_edit);
                iv_delete = (ImageView) itemView.findViewById(R.id.iv_address_delete);
                itemView.setOnClickListener(this);
                iv_delete.setOnClickListener(this);
                iv_edit.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_address_edit:
                    if (myClickListener != null) {
                        myClickListener.onEdit(getItem(getAdapterPosition()),getAdapterPosition());
                    }
                    break;
                case R.id.iv_address_delete:
                    if (myClickListener != null) {
                        myClickListener.onDelete(getItem(getAdapterPosition()),getAdapterPosition());
                    }
                    break;
                default:
                    if (myClickListener != null) {
                        myClickListener.onSelect(getItem(getAdapterPosition()),getAdapterPosition());
                    }
                    break;
            }

        }
    }
}
