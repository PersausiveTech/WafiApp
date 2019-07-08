package com.mobtecnica.wafiapps.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.fragments.wafi_main.PolicyDetailsFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.SecondFragment;
import com.mobtecnica.wafiapps.fragments.wafi_main.ThirdFragment;

public class Terms_and_Conditions extends FragmentActivity {

    TabLayout tabLayout;
    ViewPager pager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_terms_and__conditions);

            pager = (ViewPager) findViewById(R.id.vpPager);
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            tabLayout.setupWithViewPager(pager);
        }

    public void onBack(View view) {
        onBackPressed();
    }
        private class MyPagerAdapter extends FragmentPagerAdapter {

            public MyPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int pos) {
                if (pos == 0) {
                    return PolicyDetailsFragment.newInstance();
                } else if (pos == 1) {
                    return SecondFragment.newInstance();
                } else  {
                    return ThirdFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0 : return getString(R.string.shipping_information);
                    case 1 : return getString(R.string.condition_use);
                    case 2 : return getString(R.string.privacy_policy);
                    default: return "";
                }
            }
        }
    }

