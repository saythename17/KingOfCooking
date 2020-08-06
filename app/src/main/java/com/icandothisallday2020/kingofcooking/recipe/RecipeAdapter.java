package com.icandothisallday2020.kingofcooking.recipe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.icandothisallday2020.kingofcooking.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<RecipeItem> items;

    public RecipeAdapter(Context context, ArrayList<RecipeItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_recipe,parent,false);
        VH holder=new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        RecipeItem item=items.get(position);
        vh.title.setText(item.title);
        vh.name.setText(item.name);
        Glide.with(context).load(item.image).into(vh.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView title,name;
        ImageView image;
        public VH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.home_recycler_title);
            name=itemView.findViewById(R.id.home_recycler_name);
            image=itemView.findViewById(R.id.home_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecipeItem item= items.get(getLayoutPosition());
                    Intent intent=new Intent(context, RecipeDetail.class);
                    intent.putExtra("title",item.name);
                    intent.putExtra("img",item.image);

                    if(Build.VERSION.SDK_INT<21) context.startActivity(intent);
                    else{
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                new Pair<View, String>(image,"IMG"));
                        context.startActivity(intent,options.toBundle());
                    }
                }
            });
        }
    }
}
