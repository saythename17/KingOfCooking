package com.icandothisallday2020.kingofcooking.recipe;

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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MAdatper extends RecyclerView.Adapter {
    Context context;
    ArrayList<ManualItem> items;

    public MAdatper(Context context, ArrayList<ManualItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.method_list,parent,false);
        VH vh=new VH(itemView);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        vh.text.setText(items.get(position).manual);
        if(items.get(position).manual_img!=null)
        Glide.with(context).load(items.get(position).manual_img).into(vh.img);
        else vh.img.setMaxHeight(0);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VH extends RecyclerView.ViewHolder{
        TextView text;
        ImageView img;

        public VH(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.method_text);
            img=itemView.findViewById(R.id.method_img);
        }
    }
}
