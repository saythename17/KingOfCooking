package com.icandothisallday2020.kingofcooking.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.icandothisallday2020.kingofcooking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BottomAdapter extends PagerAdapter {
    ArrayList<Integer> items;
    LayoutInflater inflater;

    public BottomAdapter(ArrayList<Integer> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.home_page1,null);
        ImageView iv=view.findViewById(R.id.home_page1);
//        Picasso.get().load(items.get(position)).into(iv);
//        Log.i("homepage",""+items.get(position));
        iv.setImageResource(items.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
