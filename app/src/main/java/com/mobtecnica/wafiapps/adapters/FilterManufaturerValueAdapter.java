package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.productsInCategories.productsInCategoriesResponse.FilteredItems;

import java.util.ArrayList;

public class FilterManufaturerValueAdapter extends RecyclerView.Adapter<FilterManufaturerValueAdapter.DataObjectHolder> {

    private  ArrayList<FilteredItems> filteredItems;
    private final ArrayList<FilteredItems> filteredItemsCopy = new ArrayList<>();
    private final Context context;
    private FilterItemViewClickListener clickListener;

    public FilterManufaturerValueAdapter(Context context, ArrayList<FilteredItems> filteredItems) {
        this.filteredItems = filteredItems;
        this.context = context;
        filteredItemsCopy.addAll(filteredItems);
    }

    public void setOnItemClickListener(FilterManufaturerValueAdapter.FilterItemViewClickListener myClickListener) {
        this.clickListener = myClickListener;
    }

    public void setData(ArrayList<FilteredItems> filteredItems) {
        this.filteredItems.clear();
        this.filteredItems.addAll(filteredItems);
        filteredItemsCopy.clear();
        filteredItemsCopy.addAll(filteredItems);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_value_recycler_item, parent, false);

        return (new FilterManufaturerValueAdapter.DataObjectHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull DataObjectHolder holder, int position) {
        holder.filter_value.setText(filteredItems.get(position).getManufacturerName());
        holder.filter_value_layout.setSelected(filteredItems.get(position).isOptionsSelected());
//        holder.checkBox.setChecked(filteredItems.get(position).isOptionsSelected());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public interface FilterItemViewClickListener {
        void onClicked(FilteredItems item);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView filter_value;
        LinearLayout filter_value_layout;

        public DataObjectHolder(View itemView) {
            super(itemView);
            filter_value = (TextView) itemView.findViewById(R.id.filter_value_recycler);
            filter_value_layout = (LinearLayout) itemView.findViewById(R.id.filter_value_layout);
            filter_value_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.setSelected(!v.isSelected());
            filteredItems.get(getAdapterPosition()).setOptionsSelected(v.isSelected());
            filteredItems.get(0).setOptionItemSelected(isAnyItemSelected(filteredItems));
            if(clickListener!=null)
                clickListener.onClicked(filteredItems.get(getAdapterPosition()));
        }
    }

    private boolean isAnyItemSelected(ArrayList<FilteredItems> filteredItems) {
        for (FilteredItems item : filteredItems) {
            if (item.isOptionsSelected()) {
                return true;
            }
        }
        return false;
    }

    public void filter(CharSequence sequence) {
        ArrayList<FilteredItems> temp = new ArrayList<>();
        if (!TextUtils.isEmpty(sequence)) {
            for (FilteredItems s : filteredItemsCopy) {

                if (s!=null && s.getManufacturerName()!=null && (s.getManufacturerName().toLowerCase().trim()).contains((sequence.toString().toLowerCase()).trim())) {
                    temp.add(s);
                }
            }
        } else {
            temp.addAll(filteredItemsCopy);
        }
        filteredItems.clear();
        filteredItems.addAll(temp);
        notifyDataSetChanged();
        temp.clear();
    }
}
