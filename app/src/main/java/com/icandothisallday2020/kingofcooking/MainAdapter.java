package com.icandothisallday2020.kingofcooking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.icandothisallday2020.kingofcooking.my.FragmentMy;
import com.icandothisallday2020.kingofcooking.recipe.FragmentRecipe;

public class MainAdapter extends FragmentPagerAdapter {
    Fragment[] fragments=new Fragment[4];

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]= new FragmentHome();
        fragments[1]=new FragmentRecipe();
        fragments[2]= new FragmentFind();
        fragments[3]=new FragmentMy();

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
