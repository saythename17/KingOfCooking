package com.icandothisallday2020.kingofcooking.my;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.icandothisallday2020.kingofcooking.R;

import java.util.ArrayList;

public class WriteAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<WriteItem> items;
    LinearLayout recipes;

    public WriteAdapter(Context context, ArrayList<WriteItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView =inflater.inflate(R.layout.list_recipe,parent,false);
        VH holder=new VH(itemView);

        recipes=itemView.findViewById(R.id.checklist);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        WriteItem item=items.get(position);
        vh.title.setText(item.title);
       // vh.recipe.setText(item.recipe);

        //TODO 네트워크 이미지 업로드 TODO
        String imgUrl="http://soom0.dothome.co.kr/Retrofit"+item.file;
        Glide.with(context).load(imgUrl).into(vh.iv);

        String s=item.recipe;
        String[] recipeArr=s.split("\n");
        for(int i=0;i<recipeArr.length;i++){
            if(!recipeArr[i].equals("")) {
                //String recipe=recipeArr[i].toString();
                CheckBox checkBox=new CheckBox(context);
                checkBox.setTextSize(16);
                Typeface typeface= context.getResources().getFont(R.font.mapoflowerisland);
                checkBox.setTypeface(typeface,Typeface.BOLD);
                checkBox.setText(recipeArr[i]);
                recipes.addView(checkBox);

            }
        }

        vh.date.setText(item.date);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView title,date;

        ImageView iv;
        ImageView correct,delete;

        public VH(@NonNull final View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            //recipe=itemView.findViewById(R.id.recipe);
            date=itemView.findViewById(R.id.date);
            iv=itemView.findViewById(R.id.recipe_iv);
            correct=itemView.findViewById(R.id.correct);
            delete=itemView.findViewById(R.id.delete);

            correct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context,R.style.MyAlertDialog);
                    builder.setTitle("Do you want to remove this recipe?");
                    builder.setIcon(R.drawable.icon_book);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position=getLayoutPosition();
                            items.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("CANCEL",null);
                    AlertDialog dialog=builder.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            });
        }
    }
}
