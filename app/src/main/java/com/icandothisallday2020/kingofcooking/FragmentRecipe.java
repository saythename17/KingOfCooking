package com.icandothisallday2020.kingofcooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentRecipe extends Fragment {
    ArrayList<HF_Item> items=new ArrayList<>();
    RecipeAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new HF_Item("냉모밀 궁극의 레시피","King of Cooking",R.drawable.food));
        items.add(new HF_Item("초계국수 환상의 레시피","Master of Noodle",R.drawable.food2));
        items.add(new HF_Item("비빔냉면 감동의 레시피","Artist of Mix",R.drawable.food3));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));
        items.add(new HF_Item("The Recipe of Legend","King of Cooking",R.drawable.background_main));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_recipe,container,false);
        adapter=new RecipeAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.home_recycler);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
