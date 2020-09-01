package com.icandothisallday2020.kingofcooking.recipe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.icandothisallday2020.kingofcooking.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipeDetail extends AppCompatActivity {
    ImageView image;
    TextView title,kcal;
    RecyclerView iList,mList;

    ArrayList<String> ingredients=new ArrayList<>();
    IAdapter iAdapter;

    ArrayList<ManualItem> manualItems=new ArrayList<>();
    MAdatper mAdatper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        image=findViewById(R.id.detail_image);
        title=findViewById(R.id.recipe_title);
        kcal=findViewById(R.id.recipe_kcal);
        iList=findViewById(R.id.list_ingredient);
        mList=findViewById(R.id.recipe_manual);



        Intent intent=getIntent();


        String title=intent.getStringExtra("title");
        this.title.setText(title);
        String img=intent.getStringExtra("image");
        if(img==null) img="https://blogfiles.pstatic.net/MjAyMDA4MTdfODcg/MDAxNTk3NjUzMTI4NTA3.Mdbz9FRcl8r-rHMe41LKgGB66FJGlw2z0GtHy4WBNMQg.DkGuarWyDSghb5Arfu0P3AfvAeh5HUOoPKizIv6YKu4g.PNG.skgmlakfh/%EC%A0%9C%EB%AA%A9%EC%9D%84_%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94._1_%284%29.png?type=w2";
        Glide.with(this).load(img).into(image);
        String fullIngredients=intent.getStringExtra("ingredient");
        String[] arrIngredients=fullIngredients.split(",");
        for(int i=0;i<arrIngredients.length;i++){
            boolean b=arrIngredients[i].contains("\n");
            if(b) arrIngredients[i]=arrIngredients[i].split("\n")[1];
            ingredients.add(arrIngredients[i]);
        }
//        ingredients=new ArrayList(Arrays.asList(arrIngredients));
        iAdapter= new IAdapter(this,ingredients);
        iList.setAdapter(iAdapter);

        String fullManuals=intent.getStringExtra("manual");
        String[] arrManuals=fullManuals.split("#@#");
        String fullManuals_Img=intent.getStringExtra("manual_img");
        Log.i("full",""+fullManuals_Img);
        String[] arrManuals_img=fullManuals_Img.split("#@#");
        for(int i=0; i<arrManuals.length;i++){
            manualItems.add(new ManualItem(arrManuals[i],arrManuals_img[i]));
            i++;
        }
        mAdatper=new MAdatper(this,manualItems);
        mList.setAdapter(mAdatper);

    }

    public void back(View view) {
        finish();
        return;
    }
}
