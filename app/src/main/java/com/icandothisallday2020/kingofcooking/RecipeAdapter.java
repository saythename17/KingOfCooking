package com.icandothisallday2020.kingofcooking;

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

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<HF_Item> items;

    public RecipeAdapter(Context context, ArrayList<HF_Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.home_recycler_item,parent,false);
        VH holder=new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        HF_Item item=items.get(position);
        vh.title.setText(item.title);
        vh.name.setText(item.name);
        vh.image.setImageResource(item.image);
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
                    HF_Item hf= items.get(getLayoutPosition());
                    Intent intent=new Intent(context, RecipeDetail.class);
                    intent.putExtra("title",hf.name);
                    intent.putExtra("img",hf.image);

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
