package com.icandothisallday2020.kingofcooking.recipe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.icandothisallday2020.kingofcooking.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class FragmentRecipe extends Fragment {
    ArrayList<RecipeItem> items=new ArrayList<>();
    RecipeAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_recipe,container,false);
        adapter=new RecipeAdapter(getContext(),items);
        recyclerView=view.findViewById(R.id.home_recycler);
        recyclerView.setAdapter(adapter);

        items.clear();
        adapter.notifyDataSetChanged();
        new Thread(){
            @Override
            public void run() {
                try {
                    Log.i("food","a");
                    URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/22c9f42a31d249ce8ab5/COOKRCP01/xml/1/18");
                    InputStream inputStream=url.openStream();
                    InputStreamReader reader=new InputStreamReader(inputStream);

                    XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                    XmlPullParser xpp=factory.newPullParser();
                    xpp.setInput(reader);
                    int eventType=xpp.next();

                    RecipeItem item=null;
                    while (eventType!=XmlPullParser.END_DOCUMENT){
                        Log.i("food","b");




                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                String tag_name=xpp.getName();
                                if(tag_name.equals("row") ) {
                                    item= new RecipeItem();
                                }else if(tag_name.equals("RCP_NM")){
                                    xpp.next();
                                    item.title=xpp.getText();
                                }else if(tag_name.equals("RCP_PAT2")){
                                    xpp.next();
                                    item.name=xpp.getText();
                                }else if(tag_name.equals("ATT_FILE_NO_MAIN")){
                                    xpp.next();
                                    item.image=xpp.getText();
                                }break;
                            case XmlPullParser.END_TAG:
                                String end_name=xpp.getName();
                                if(end_name.equals("row")){
                                    items.add(item);
                                    view.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyItemInserted(items.size()-1);
                                        }
                                    });
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




        return view;
    }

}
