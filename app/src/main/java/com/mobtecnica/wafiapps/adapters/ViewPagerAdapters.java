package com.mobtecnica.wafiapps.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.mobtecnica.wafiapps.fragments.wafi_main.CategoriesFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ContactUsFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.HomeFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ProfileFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.WishListFragment;

/**
 * Created by SIby on 04-01-2017.
 */

public class ViewPagerAdapters extends FragmentStatePagerAdapter {


    private boolean isPagingEnabled = true;

    private int tabcount;
    private FragmentActivity fragmentActivity;
    private HomeFragment homeFragment;
    //    private CategoriesFragment categoriesFragment;
    private ContactUsFragment servicesFragment;
    private WishListFragment wishListFragment;
    private ProfileFragment profileFragment;
    private CategoriesFragment categoriesFragment;

    public ViewPagerAdapters(FragmentManager fm, int tabcount, FragmentActivity fragmentActivity) {
        super(fm);
        this.fragmentActivity = fragmentActivity;
        this.tabcount = tabcount;
        this.homeFragment = new HomeFragment();
//        this.categoriesFragment = new CategoriesFragment();
        this.servicesFragment = new ContactUsFragment();
        this.wishListFragment = new WishListFragment();
        this.profileFragment = new ProfileFragment();
        this.categoriesFragment = new CategoriesFragment();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return categoriesFragment;
            case 2:
                return wishListFragment;
            case 3:
                return profileFragment;
            case 4:
                return servicesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;

    }
}
