package com.icandothisallday2020.kingofcooking.home;

import android.content.Context;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ChartAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ChartItem> items;

    public ChartAdapter(Context context, ArrayList<ChartItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.home_chart_recycler,
                parent,false);
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        ChartItem item=items.get(position);
        vh.title.setText(item.title);
        vh.n.setText(item.n);
        vh.name.setText(item.name);
        vh.sub.setText(item.sub);
        Glide.with(context).load(item.img).into(vh.iv);
        Glide.with(context).load(item.civ).into(vh.civ);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        CircleImageView civ;
        TextView title,sub,name,n;
        public VH(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.chart_iv);
            civ=itemView.findViewById(R.id.chart_civ);
            title=itemView.findViewById(R.id.chart_title);
            sub=itemView.findViewById(R.id.chart_sub);
            name=itemView.findViewById(R.id.chart_user);
            n=itemView.findViewById(R.id.chart_n);
        }
    }
}
