package com.mobtecnica.wafiapps.fragments.foodHome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.ResturantsListRvAdapter;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.model.wafiEats.getRestaurants.RestaurantsInArea;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by siby on 07-Jun-17.
 */

public class ResturantsListFragment extends BaseFragment {
    View rootView;
    ResturantsListRvAdapter listRvAdapter = null;
    int fragment_id = 0;
    List<RestaurantsInArea> resturants = null;
    private RecyclerView rv_resturants;

    public ResturantsListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ResturantsListFragment(int fragment_id) {
        this.fragment_id = fragment_id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_resturants_list, container, false);
        initViews();
        listResturants();
        onClickListners();
        return rootView;
    }

    private void onClickListners() {
        listRvAdapter.setOnItemClickListener(new ResturantsListRvAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (v.getId()) {
                    case R.id.view_menu:
                        Fragment fragment = new ResturantItemsFragment(resturants.get(position).getLocationID(), resturants.get(position).getShopID());
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                                .addToBackStack(fragment.getClass().getName())
                                .commit();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void listResturants() {
        if (fragment_id == 1) {//selected resturants
            resturants = new ArrayList<>();
            resturants = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getResturants();
            listRvAdapter.setDataset(resturants);
        } else if (fragment_id == 2) {
            resturants = new ArrayList<>();
            resturants = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getAllResturants();
            listRvAdapter.setDataset(resturants);
        }//all resturants
    }

    private void initViews() {
        rv_resturants = (RecyclerView) rootView.findViewById(R.id.rv_resturants_list);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_resturants.setLayoutManager(verticalLayoutmanager);
        listRvAdapter = new ResturantsListRvAdapter(getActivity(), new ArrayList<RestaurantsInArea>());
        rv_resturants.setAdapter(listRvAdapter);
    }
}
