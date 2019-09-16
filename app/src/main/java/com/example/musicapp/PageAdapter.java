package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentRock();
            case 1:
                return new FragmentClassic();
            case 2:
                return new FragmentPop();
            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

