package com.icandothisallday2020.kingofcooking.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icandothisallday2020.kingofcooking.R;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ShopItem> items;

    public ShopAdapter(Context context, ArrayList<ShopItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.home_shop_recycler,parent,false);
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        ShopItem item=items.get(position);
        vh.iv.setImageResource(item.img);
        vh.tv.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.shop_iv);
            tv=itemView.findViewById(R.id.shop_tv);

        }
    }
}
