package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.activity.MainActivity;
import com.mobtecnica.wafiapps.adapters.ViewPagerAdapters;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 04-01-2017.
 */

public class MainFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    View rootview;
    ViewPagerAdapters viewPagerAdapters;
    android.support.v7.widget.Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MenuItem prevMenuItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_main, container, false);
        intitialize();
        initializeAdapters();
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getInt(Constants.PAGE) == 0) {
                navigateToHomeFragment();
            } else if (bundle.getInt(Constants.PAGE) == 2) {
                navigateToWishList();
            } else if (bundle.getInt(Constants.PAGE) == 3) {
                navigateToProfile();
            }
        }
        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.home));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getResources().getString(R.string.home));
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setCurrentFragmentToolbar(viewPagerAdapters.getItem(0));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void intitialize() {
        try {
            toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar_main);
            bottomNavigationView = (BottomNavigationView) rootview.findViewById(R.id.tablayout);
            viewPager = (ViewPager) rootview.findViewById(R.id.pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeAdapters() {
        viewPagerAdapters = new ViewPagerAdapters(getChildFragmentManager(), 5, getActivity());
        viewPager.setAdapter(viewPagerAdapters);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(0, false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    public Fragment getCurrentAdapterItem() {
        if (viewPagerAdapters != null && viewPager != null) {
            return viewPagerAdapters.getItem(viewPager.getCurrentItem());
        }
        return null;
    }
    public void navigateToWishList() {
        try {
            bottomNavigationView.setSelectedItemId(R.id.action_wish_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToProfile() {
        try {
            bottomNavigationView.setSelectedItemId(R.id.action_profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void navigateToHomeFragment() {
        try {
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewPagerPage(final int page) {
        if (viewPager != null) {
            viewPager.setCurrentItem(page, true);
        }
    }

    public int getViewPagerPage() {
        if (viewPager != null) {
            return viewPager.getCurrentItem();
        }
        return 0;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.action_categories:
                viewPager.setCurrentItem(1);
                break;
            case R.id.action_wish_list:
                viewPager.setCurrentItem(2);
                break;
            case R.id.action_profile:
                viewPager.setCurrentItem(3);
                break;
            case R.id.action_help:
                viewPager.setCurrentItem(4);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        }
        else
        {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setCurrentFragmentToolbar(viewPagerAdapters.getItem(position));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
