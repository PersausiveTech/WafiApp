package com.mobtecnica.wafiapps.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.customViews.MyGridView;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProductListFragment;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.categories.Categories;
import com.mobtecnica.wafiapps.model.categories.SubCategories;

/**
 * Created by SIby on 03-02-2017.
 */

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<Categories> categories;

    private MyGridView myGridView;


    public CustomExpandableListViewAdapter(Context context, ArrayList<Categories> categories) {
        this.context = context;
        this.categories = categories;
    }

    public void updateData(ArrayList<Categories> categories) {
        this.categories = categories;

    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return categories.get(groupPosition).getSubCategories().length;
        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getSubCategories()[childPosition];
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
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        ImageView is_expanded = convertView.findViewById(R.id.iv_is_expanded);
        if (isExpanded) {
            is_expanded.setImageResource(R.mipmap.collaps);
        } else {
            is_expanded.setImageResource(R.mipmap.expand);
        }
        is_expanded.setVisibility(categories.get(groupPosition).getSubCategories().length > 0 ? View.VISIBLE : View.GONE);

        AppCompatImageView imageView = convertView.findViewById(R.id.iv_icon_list);
        TextView listTitleTextView = convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setText(categories.get(groupPosition).getName());
        Glide.with(context)
                .load(categories.get(groupPosition).getPictureModel().getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(imageView);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final SubCategories[] subCategories = categories.get(groupPosition).getSubCategories();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        myGridView = convertView.findViewById(R.id.GridView_toolbar);
        CustomGridCategories adapter = new CustomGridCategories(context, subCategories);
        adapter.update(subCategories);
        myGridView.setNumColumns(2);
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item_id = String.valueOf(id);
                ObjectFactory.getInstance().getAppPreference(context).setCategoryId(item_id);
                ObjectFactory.getInstance().getHomeManager(context).setSelectedSubCategory(subCategories[position]);
                navigateToProductListFragment(subCategories[position].getName());
            }
        });
        return convertView;
    }

    private void navigateToProductListFragment(String title) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setTitle(title);
        if (context instanceof MainActivity) {
            ((MainActivity) context).addFragment(fragment);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
