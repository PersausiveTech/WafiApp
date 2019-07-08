package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.customViews.MyGridView;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.wafiEats.addToCart.SubAttributeSelection;
import com.mobtecnica.wafiapps.model.wafiEats.getMenuOptions.CustomModelForMenuExpandableList;

/**
 * Created by SIby on 03-02-2017.
 */

public class ResturentMenuOptionsExpandableList extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CustomModelForMenuExpandableList> customModelForExpandableLIsts;
    ArrayList<SubAttributeSelection> selections = new ArrayList<>();
    ArrayList<String> checked_items = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private MyGridView myGridView;

    public ResturentMenuOptionsExpandableList(Context context, ArrayList<CustomModelForMenuExpandableList> customModelForExpandableLIsts) {
        this.context = context;
        this.customModelForExpandableLIsts = customModelForExpandableLIsts;
    }

    public void updateContext(ArrayList<CustomModelForMenuExpandableList> customModelForExpandableLIsts) {
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
            convertView = layoutInflater.inflate(R.layout.expandable_list_menu_options_parent, null);
        }
        ImageView iv_expanded = (ImageView) convertView.findViewById(R.id.iv_expanded);
        if (isExpanded) {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
        } else {
            iv_expanded.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        tv_name.setText(customModelForExpandableLIsts.get(groupPosition).getList().get(0).getMenuSubItemGroup());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.expandable_list_menu_options_child, null);

        final AppCompatCheckBox radioButton = (AppCompatCheckBox) convertView.findViewById(R.id.rb_menu_option);
        radioButton.setText(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItem());
        radioButton.setTag(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItem());
        radioButton.setChecked(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).isChecked());

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton checkboxView, boolean isChecked) {
                int count = 0;
                String item = customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItemID() + "-"
                        + customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuID() + "."
                        + customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItemGroupID();

                if (isChecked) {
                    customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).setChecked(true);
                    Toast.makeText(context, "checked", Toast.LENGTH_SHORT).show();
                    count = customModelForExpandableLIsts.get(groupPosition).getCount();
                    customModelForExpandableLIsts.get(groupPosition).setCount(count + 1);
                    count = customModelForExpandableLIsts.get(groupPosition).getCount();
                } else if (!isChecked) {
                    Toast.makeText(context, "not checked", Toast.LENGTH_SHORT).show();

                }

                if (count <= customModelForExpandableLIsts.get(groupPosition).getMaxCount()) {// it will allow 3 checkboxes only
                    if (isChecked) {
                        checked_items.add(item);
                        ObjectFactory.getInstance().getWafiEatsManager(context).setCheckedItems(checked_items);

                    } else if (!isChecked) {
                        for (int i = 0; i < checked_items.size(); i++) {
                            if (item.matches(checked_items.get(i))) {
                                checked_items.remove(i);
                                count = customModelForExpandableLIsts.get(groupPosition).getCount();
                                customModelForExpandableLIsts.get(groupPosition).setCount(count - 1);
                                count = customModelForExpandableLIsts.get(groupPosition).getCount();
                                customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).setChecked(false);
                                ObjectFactory.getInstance().getWafiEatsManager(context).setCheckedItems(checked_items);
                                return;
                            }
                        }
                    }

                } else {
                    Toast.makeText(context, R.string.max_item, Toast.LENGTH_LONG).show();
                    count = customModelForExpandableLIsts.get(groupPosition).getCount();
                    customModelForExpandableLIsts.get(groupPosition).setCount(count - 1);
                    count = customModelForExpandableLIsts.get(groupPosition).getCount();
                    customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).setChecked(false);
                    radioButton.setChecked(false);
                }
            }
        });

       /* radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + radioButton.getTag(), Toast.LENGTH_SHORT).show();
                if (radioButton.isChecked()) {

                    int count = 0;
                    int maxcount = Integer.parseInt(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMaxValue());
                    for (int i = 0; i < selections.size(); i++) {
                        if (selections.get(i).getMenuSubItemGroupID() == customModelForExpandableLIsts.get(groupPosition).getMenuSubItemGroupID()) {
                            count = count + 1;
                        }
                    }
                    if (count <= maxcount) {

                        String checkedItem = customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItemGroupID() + "-"
                                + customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuID();

                        SubAttributeSelection selection = new SubAttributeSelection();
                        selection.setAttribute(checkedItem);
                        selection.setId(Integer.parseInt(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMaxValue()));
                        selection.setMenuSubItemGroupID(Integer.parseInt(customModelForExpandableLIsts.get(groupPosition).getList().get(childPosition).getMenuSubItemGroupID()));

                        selections.add(selection);

                    } else {
                        Toast.makeText(context, "Maximum number of selection reached", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                }
            }

        });
        */
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
