package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.MyGridView;
import com.mobtecnica.wafiapps.customViews.NonScrollExpandableListView;
import com.mobtecnica.wafiapps.fragments.laundryHome.LaundryClothDetailsFragment;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.CustomClassForExpandableView;
import com.mobtecnica.wafiapps.model.LaundryModel.getlaundryItems.ListLaundryMenu;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 03-02-2017.
 */

public class LaundryItemsExpandableList extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CustomClassForExpandableView> listLaundryMenus;
    private LayoutInflater layoutInflater;
    //    private MyGridView myGridView;
    private OnChildClick onChildClick;

    public LaundryItemsExpandableList(Context context, ArrayList<CustomClassForExpandableView> listLaundryMenus) {
        this.context = context;
        this.listLaundryMenus = listLaundryMenus;
    }

    public void updateContext(ArrayList<CustomClassForExpandableView> categories) {
        this.listLaundryMenus = categories;

    }

    @Override
    public int getGroupCount() {
        return listLaundryMenus.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return categories.get(groupPosition).getSubCategories().length;
        return listLaundryMenus.get(groupPosition).getList().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listLaundryMenus.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listLaundryMenus.get(groupPosition).getList();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_list_parent_laundry_item, null);
        }
        ImageView iv_expanded = convertView.findViewById(R.id.iv_expanded);
        if (isExpanded) {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
        } else {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
        }
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        tv_name.setText(listLaundryMenus.get(groupPosition).getGroup_name());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_list_child_laundry_item, null);
        }
        ImageView imageView = convertView.findViewById(R.id.iv_cloth_image);
        TextView cloth_name =  convertView.findViewById(R.id.tv_cloth_name);
//        AppCompatButton btn_add_to_cart = (AppCompatButton) convertView.findViewById(R.id.btn_add_to_cart);
//        TextView tv_price_on_selection = (TextView) convertView.findViewById(R.id.tv_price_on_selection);
        Glide.with(convertView.getContext()).load(listLaundryMenus.get(groupPosition).getList().get(childPosition).getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(imageView);
        cloth_name.setText(listLaundryMenus.get(groupPosition).getList().get(childPosition).getDescription());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChildClick != null) {
                    onChildClick.onChildClick(listLaundryMenus.get(groupPosition).getList().get(childPosition));
                }
            }
        });
        /*try {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ListLaundryMenu listLaundryMenu = listLaundryMenus.get(groupPosition).getList().get(childPosition);
                    Fragment fragment = new LaundryClothDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DETAILS", listLaundryMenu);
                    fragment.setArguments(bundle);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                            .add(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                            .addToBackStack(fragment.getClass().getName())
                            .commit();
                }
            });
        } catch (Exception e) {


        }
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLaundryMenu listLaundryMenu = listLaundryMenus.get(groupPosition).getList().get(childPosition);
                Fragment fragment = new LaundryClothDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("DETAILS", listLaundryMenu);
                fragment.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .add(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                        .addToBackStack(null)
                        .commit();
            }
        });*/
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setOnChildClick(OnChildClick onChildClick) {
        this.onChildClick = onChildClick;
    }

    public interface OnChildClick {
        void onChildClick(ListLaundryMenu listLaundryMenu);

//        void onAddToCart(ListLaundryMenu listLaundryMenu); //TODO update this if any new changes for this action
    }
}
