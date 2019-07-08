package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.orderHistory.Order;
import com.mobtecnica.wafiapps.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ALIAKBAR on 01-08-2017.
 */

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.DataObjectHolder> {

    private Context mContext;
    List<Order> orderList;
    private static MyOrdersClickListener myClickListener;

    public MyOrdersAdapter(Context mContext, List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @Override
    public MyOrdersAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_my_orders, parent, false);
        MyOrdersAdapter.DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MyOrdersAdapter.DataObjectHolder holder, int position) {
        holder.tv_order_id.setText(orderList.get(position).getId().toString());
        holder.tv_order_total.setText(orderList.get(position).getOrderTotal());
        holder.order_status.setText(orderList.get(position).getOrderStatus());
        holder.tv_payment_status.setText(orderList.get(position).getPaymentStatus());
        holder.tv_shipping_status.setText(orderList.get(position).getShippingStatus());

        Calendar c = Calendar.getInstance();
        if (orderList.get(position).getCreatedOn() != null)
            if (!orderList.get(position).getCreatedOn().equalsIgnoreCase("")) {
                String time = orderList.get(position).getCreatedOn().replace("/", "").replace("Date(", "").replace(")", "");
                c.setTimeInMillis(Long.parseLong(time));
            }

        holder.tv_order_date.setText(new SimpleDateFormat("dd MMM yyyy").format(c.getTimeInMillis()));

        if (orderList.get(position).getOrderStatus().equalsIgnoreCase(Constants.ORDER_STATUS_CANCELLED)) {
            holder.iv_order_status.setImageResource(R.drawable.ic_cancel_red);
        } else if (orderList.get(position).getOrderStatus().equalsIgnoreCase(Constants.ORDER_STATUS_PROCESSING_COMPLETE)) {
            holder.iv_order_status.setImageResource(R.drawable.ic_check_circle_green);
        } else {
            holder.iv_order_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

        if (orderList.get(position).getPaymentStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_PAID)) {
            holder.iv_payment_status.setImageResource(R.drawable.ic_check_circle_green);
        } else if (orderList.get(position).getPaymentStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_PARTIALLY_REFUNDED)) {
            holder.iv_payment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        } else if (orderList.get(position).getPaymentStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_REFUNDED)) {
            holder.iv_payment_status.setImageResource(R.drawable.ic_check_circle_green);
        } else if (orderList.get(position).getPaymentStatus().equalsIgnoreCase(Constants.PAYMENT_STATUS_VOIDED)) {
            holder.iv_payment_status.setImageResource(R.drawable.ic_cancel_red);
        } else {
            holder.iv_payment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

        if (orderList.get(position).getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_SHIPPING_NOT_REQUIRED)) {
            holder.iv_shipment_status.setImageResource(R.drawable.ic_cancel_red);
        } else if (orderList.get(position).getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_PARTIALLY_SHIPPED)) {
            holder.iv_shipment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }  else if (orderList.get(position).getShippingStatus().equalsIgnoreCase(Constants.SHIPPING_STATUS_DELIVERED)) {
            holder.iv_shipment_status.setImageResource(R.drawable.ic_check_circle_green);
        }else{
            holder.iv_shipment_status.setImageResource(R.drawable.ic_watch_later_yellow);
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_order_id;
        TextView tv_order_date;
        TextView tv_order_total;
        TextView order_status;
        TextView tv_payment_status;
        TextView tv_shipping_status;
        RelativeLayout rl_order_item_details;
        Button view_my_orders;

        AppCompatImageView iv_order_status;
        AppCompatImageView iv_payment_status;
        AppCompatImageView iv_shipment_status;

        public DataObjectHolder(View itemView) {
            super(itemView);
            tv_order_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_order_date = (TextView) itemView.findViewById(R.id.tv_order_date);
            tv_order_total = (TextView) itemView.findViewById(R.id.tv_order_total);
            order_status = (TextView) itemView.findViewById(R.id.order_status);
            tv_payment_status = (TextView) itemView.findViewById(R.id.tv_payment_status);
            tv_shipping_status = (TextView) itemView.findViewById(R.id.tv_shipping_status);
            rl_order_item_details = (RelativeLayout) itemView.findViewById(R.id.rl_order_item_details);
            iv_order_status = (AppCompatImageView) itemView.findViewById(R.id.iv_order_status);
            iv_payment_status = (AppCompatImageView) itemView.findViewById(R.id.iv_payment_status);
            iv_shipment_status = (AppCompatImageView) itemView.findViewById(R.id.iv_shipment_status);
            view_my_orders = (Button) itemView.findViewById(R.id.view_my_orders);

            itemView.setOnClickListener(this);
            view_my_orders.setOnClickListener(this);
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

    public interface MyOrdersClickListener {
        public void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(MyOrdersAdapter.MyOrdersClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void updateMyOrdersData(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

}
