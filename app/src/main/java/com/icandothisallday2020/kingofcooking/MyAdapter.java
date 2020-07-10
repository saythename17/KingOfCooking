package com.icandothisallday2020.kingofcooking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    Fragment[] fragments=new Fragment[4];

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]= new FragmentHome();
        fragments[1]=new FragmentRecipe();
        fragments[2]= new FragmentFind();
        fragments[3]=new FragmentTalk();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
