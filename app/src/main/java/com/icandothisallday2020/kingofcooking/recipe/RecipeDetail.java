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

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) image.setTransitionName("IMG");
        int id=intent.getIntExtra("id",1);
        new Thread(){
            @Override
            public void run() {
                try {
                    Log.i("food","a");
                    URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/22c9f42a31d249ce8ab5/COOKRCP01/xml/1/100");
                    InputStream inputStream=url.openStream();
                    InputStreamReader reader=new InputStreamReader(inputStream);

                    XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                    XmlPullParser xpp=factory.newPullParser();
                    xpp.setInput(reader);
                    int eventType=xpp.next();

                    while (eventType!=XmlPullParser.END_DOCUMENT){
                        //TODO ingredient & manual list parsing



                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                String tag_name=xpp.getName();
                                if(tag_name.equals("row") ) {
                                    int rowid=Integer.parseInt(xpp.getAttributeValue(0));
                                }else if(tag_name.equals("RCP_NM")){
                                }else if(tag_name.equals("RCP_PAT2")){
                                }else if(tag_name.equals("ATT_FILE_NO_MAIN")){
                                }break;
                            case XmlPullParser.END_TAG:
                                String end_name=xpp.getName();
                                if(end_name.equals("row")){
                                }
                                break;
                        }//switch



                        eventType=xpp.next();

                    }//while

                } catch (Exception e) {
                    Log.i("food",""+e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void back(View view) {
        finish();
        return;
    }
}
