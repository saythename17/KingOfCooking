package com.icandothisallday2020.kingofcooking;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RecipeDetail extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent=getIntent();


        String title=intent.getStringExtra("title");
        int imgID=intent.getIntExtra("img",R.drawable.ic_launcher_foreground);
        image=findViewById(R.id.detail_image);
        Glide.with(this).load(imgID).into(image);
        //getSupportActionBar().setTitle(title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) image.setTransitionName("IMG");
    }

}
