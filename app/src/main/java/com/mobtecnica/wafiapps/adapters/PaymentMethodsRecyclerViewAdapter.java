package com.mobtecnica.wafiapps.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.paymentmethod.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsRecyclerViewAdapter extends RecyclerView.Adapter<PaymentMethodsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<PaymentMethod> paymentMethodArrayList = new ArrayList<>();
    private PaymentMethod selectedPaymentMethod;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method_card, parent, false);
        return new ViewHolder(view);
    }

    public void updateData(List<PaymentMethod> paymentMethodArrayList1) {
        this.paymentMethodArrayList.addAll(paymentMethodArrayList1);
        notifyItemRangeInserted(0, paymentMethodArrayList1.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PaymentMethod itemPaymentMethod = getItem(position);
        if (itemPaymentMethod != null) {
            holder.paymentName.setText(itemPaymentMethod.getName());
            Glide.with(holder.paymentLogo.getContext())
                    .load(itemPaymentMethod.getLogoUrl())
                    .thumbnail(Glide.with(holder.paymentLogo.getContext()).load(R.drawable.loading))
                    .into(holder.paymentLogo);
            holder.selectionRadioButton.setChecked(itemPaymentMethod.getSelected());
            if (itemPaymentMethod.getSelected()) {
                selectedPaymentMethod = itemPaymentMethod;
            }


        }
    }

    private PaymentMethod getItem(int position) {
        if (!paymentMethodArrayList.isEmpty() && paymentMethodArrayList.size() > position) {
            return paymentMethodArrayList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return paymentMethodArrayList.size();
    }

    public PaymentMethod getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView paymentName;
        private ImageView paymentLogo;
        private RadioButton selectionRadioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            paymentName = itemView.findViewById(R.id.tv_payment_method_selection);
            paymentLogo = itemView.findViewById(R.id.iv_payment_method_selection);
            selectionRadioButton = itemView.findViewById(R.id.rd_btn_payment_method_selection);
        }

        @Override
        public void onClick(View v) {
            if (selectedPaymentMethod != null) {
                selectedPaymentMethod.setSelected(false);
            }
            getItem(getAdapterPosition()).setSelected(true);
            notifyDataSetChanged();
        }
    }
}
