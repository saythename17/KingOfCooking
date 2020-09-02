package com.icandothisallday2020.kingofcooking.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.icandothisallday2020.kingofcooking.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    DotsIndicator dotsIndicator;
    ViewPager viewPager;
    BottomAdapter bottomAdapter;
    ArrayList<Integer> items=new ArrayList<>();

    RecyclerView shop,best;
    ShopAdapter shopAdapter;
    ChartAdapter chartAdapter;
    ArrayList<ShopItem> sItems=new ArrayList<>();
    ArrayList<ChartItem> cItems=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(R.drawable.home_top5);
        items.add(R.drawable.home_top2);
        items.add(R.drawable.home_top6);
        items.add(R.drawable.home_top4);
        items.add(R.drawable.home_top3);

        sItems.add(new ShopItem("시원한 콩국수 특가",R.drawable.home_shop_item1));
        sItems.add(new ShopItem("밥 한공기가 뚝딱!",R.drawable.home_shop_item2));
        sItems.add(new ShopItem("뼈 없는 소갈비",R.drawable.home_shop_item3));
        sItems.add(new ShopItem("장인정신의 팥빙수",R.drawable.home_shop_item4));
        sItems.add(new ShopItem("이거 먹고 힘내잣!죽",R.drawable.home_shop_item5));
        sItems.add(new ShopItem("아이들 간식으로 딱",R.drawable.home_shop_item6));

        cItems.add(new ChartItem("1","https://t1.daumcdn.net/cfile/tistory/9920344B5AA0EB7D18","바삭바삭 끝판왕",
        "뒤돌아서면 생각나는 돈까스","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRLVbTyZjYODfIB2ykkWLAXmPrwlCmVjm8jFQ&usqp=CAU",
                "Master"));
        cItems.add(new ChartItem("2","https://pds.joins.com/news/component/htmlphoto_mmdata/202002/06/dd77dff4-3799-4cbc-8e9c-28e0ff83f811.jpg",
                "50년 전통의 눈물나는 그 감자탕","감자합니다 감자탕",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS1P5NrkuNjXGiUshdvo_AxEoJLBmOuDvMpwA&usqp=CAU","여우같은 girl"));
        cItems.add(new ChartItem("3","https://www.japanhoppers.com/uploads/images/features/372/omurice_azabu_R.jpg",
                "벼는 익을 수록 고개를 오므린다.","오므라이스","https://file2.nocutnews.co.kr/newsroom/image/2020/04/22/20200422143312218214_0_800_800.jpg"
        ,"딸기겅듀님(˶◕ ‿◕˶✿)"));
        cItems.add(new ChartItem("4","https://media.istockphoto.com/photos/spicy-rice-cakes-picture-id1152570620?k=6&m=1152570620&s=612x612&w=0&h=cw_3cPUysYYTteUa-EmTaGePtA0OmUNKX5KqEseu91s=",
                "떡볶이는 이유없이 맛있지 않아","BB분식 시크릿 떡볶이 레시피",
                "https://m.chuing.net/mai/img_character_main/4/370104714728.jpg","UBB"));
        cItems.add(new ChartItem("5","https://d3b39vpyptsv01.cloudfront.net/photo/1/2/e8f83b82c38d56e54d0cf7afe84442f1_l.jpg",
                "피렌체 그릴 파프리카 파릴라아제","파니니를 어디서 파니?니????","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSfENKErJOqKv5PZtHMOqrD_zJScCwS9VbK3Q&usqp=CAU",
                "멍뭉맘"));



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dotsIndicator = view.findViewById(R.id.dots_indicator);
        viewPager = (ViewPager)view.findViewById(R.id.pager2);
        shop=view.findViewById(R.id.home_shop);
        best=view.findViewById(R.id.home_chart);
        best.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        bottomAdapter=new BottomAdapter(items,getLayoutInflater());
        viewPager.setAdapter(bottomAdapter);
        dotsIndicator.setViewPager(viewPager);

        shopAdapter=new ShopAdapter(getContext(),sItems);
        shop.setAdapter(shopAdapter);
        chartAdapter=new ChartAdapter(getContext(),cItems);
        best.setAdapter(chartAdapter);

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }
}
