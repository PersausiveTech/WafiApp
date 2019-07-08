package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.mobtecnica.wafiapps.fragments.foodHome.GetMenuOptionsFragment;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurantsMenu.CustomModelForExpandableLIst;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 03-02-2017.
 */

public class ResturentMenuExpandableList extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts;
    String loc_id = "";
    private LayoutInflater layoutInflater;
    private MyGridView myGridView;


    public ResturentMenuExpandableList(Context context, ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts, String loc_id) {
        this.context = context;
        this.customModelForExpandableLIsts = customModelForExpandableLIsts;
        this.loc_id = loc_id;
    }

    public void updateContext(ArrayList<CustomModelForExpandableLIst> customModelForExpandableLIsts) {
        this.customModelForExpandableLIsts = customModelForExpandableLIsts;

    }

    @Override
    public int getGroupCount() {
        return customModelForExpandableLIsts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return categories.get(groupPosition).getSubCategories().length;
        return customModelForExpandableLIsts.get(groupPosition).getList().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return customModelForExpandableLIsts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return customModelForExpandableLIsts.get(groupPosition).getList();
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
            convertView = layoutInflater.inflate(R.layout.expandable_list_menu_list_parent, null);
        }
        ImageView iv_expanded = (ImageView) convertView.findViewById(R.id.iv_expanded);
        if (isExpanded) {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
        } else {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        tv_name.setText(customModelForExpandableLIsts.get(groupPosition).getList().get(0).getMenuType());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.expandable_list_menu_child, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_item_description = (TextView) convertView.findViewById(R.id.tv_item_description);

        tv_item_description.setText(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getDescription());
        ImageView btn_add_to_cart = (ImageView) convertView.findViewById(R.id.btn_add_to_cart);
        TextView iv_price = (TextView) convertView.findViewById(R.id.iv_price);
        if (customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getRate().matches("0")) {
            iv_price.setText("Price on selection ");
        } else
            iv_price.setText(R.string.bd +" "+ customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getRate());
        Glide.with(convertView.getContext()).load(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuPic()).into(imageView);
        name.setText(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenu());

        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new GetMenuOptionsFragment(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuID(),
                        customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getShopID(),
                        loc_id, groupPosition, childPosition);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
