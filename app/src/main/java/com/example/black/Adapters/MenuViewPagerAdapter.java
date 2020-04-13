package com.example.black.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.black.Fragments.KidsMenuFragment;
import com.example.black.Fragments.MenMenuFragment;
import com.example.black.Fragments.WomenMenuFragment;

public class MenuViewPagerAdapter extends FragmentPagerAdapter {

    public MenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MenMenuFragment();
        } else if (position == 1) {
            return new WomenMenuFragment();
        } else {
            return new KidsMenuFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Men";
        } else if (position == 1) {
            title = "Women";
        } else {
            title = "kids";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
