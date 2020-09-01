package com.icandothisallday2020.kingofcooking.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.icandothisallday2020.kingofcooking.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    DotsIndicator dotsIndicator;
    ViewPager viewPager;
    PagerAdapter adapter;
    ArrayList<Integer> items=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(R.drawable.home_top5);
        items.add(R.drawable.home_top2);
        items.add(R.drawable.home_top6);
        items.add(R.drawable.home_top4);
        items.add(R.drawable.home_top3);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dotsIndicator = view.findViewById(R.id.dots_indicator);

        adapter=new PageAdapter1(items,getLayoutInflater());
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
        return view;
    }
}
