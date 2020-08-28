package com.icandothisallday2020.kingofcooking.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icandothisallday2020.kingofcooking.R;

import java.util.ArrayList;

public class IAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<String> items;

    public IAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.ingredient_list,parent,false);
        VH vh=new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        vh.tv.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.iList);
        }
    }
}
