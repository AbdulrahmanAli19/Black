package com.example.black.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.black.Activities.MainActivity;
import com.example.black.Fragments.KidsFragment;
import com.example.black.Fragments.MenFragment;
import com.example.black.Fragments.WomenFragments;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            MainActivity.Title = "Men";
            return new MenFragment();
        } else if (position == 1) {
            MainActivity.Title = "Women";
            return new WomenFragments();
        } else {
            MainActivity.Title = "Kid";
            return new KidsFragment();
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
