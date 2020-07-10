package com.icandothisallday2020.kingofcooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.math.BigInteger;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class FragmentHome extends Fragment {
    ArrayList<WriteItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    WriteAdapter adapter;
    FloatingActionButton fab;
    /////////////////////////////////////////////

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=view.findViewById(R.id.recycler_home);
        adapter=new WriteAdapter(getContext(),items);
        recyclerView.setAdapter(adapter);
        fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WriteActivity.class);
                startActivityForResult(intent,112);
            }
        });
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Retrofit retrofit=RetrofitHelper.getInstanceFromGson();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<WriteItem>> call=retrofitService.loadDataFromBoard();

        call.enqueue(new Callback<ArrayList<WriteItem>>() {
            @Override
            public void onResponse(Call<ArrayList<WriteItem>> call, Response<ArrayList<WriteItem>> response) {
                if(response.isSuccessful()) {
                    ArrayList<WriteItem> comebackItems=response.body();
                    items.clear();
                    adapter.notifyDataSetChanged();

                    for(WriteItem item:comebackItems){
                        items.add(0,item);
                        adapter.notifyItemInserted(0);
                    }

                }

            }

            @Override
            public void onFailure(Call<ArrayList<WriteItem>> call, Throwable t) {
                Toast.makeText(getContext(), "FAIL TO LOAD :"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==112&&resultCode==RESULT_OK){
//            String title=data.getStringExtra("Title");
//            String recipe=data.getStringExtra("Recipe");
//            String date=data.getStringExtra("Date");
//            items.add(new WriteItem(title,recipe,R.drawable.cook,date));
//            adapter.notifyDataSetChanged();
            loadData();
        }



    }
}
