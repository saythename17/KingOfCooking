package com.icandothisallday2020.kingofcooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ViewPager pager;
    MyAdapter adapter;
    BottomNavigationView bnv;

    boolean musicOn=true;
    MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        drawer=findViewById(R.id.drawer);

        pager=findViewById(R.id.view_pager);
        adapter=new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        bnv=findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.recipe:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.find:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.talk:
                        pager.setCurrentItem(3);
                        break;
                }

                return  true;
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //조금이라도 넘어갈때 전환효과,애니메이션 효과 줄 때 사용하는 메소드
                // positionOffset(비율),positionOffsetPixels(픽셀단위):스크롤한 정도를 알려줌
            }

            int[] bnvIcon= new int[]{R.id.home, R.id.recipe, R.id.find, R.id.talk};

            @Override
            public void onPageSelected(int position) {//스크롤한 페이지가 80%를 넘었을때 기준
               bnv.setSelectedItemId(bnvIcon[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.camera:
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
                break;
            case R.id.music:
                if(musicOn){
                    Intent intent1=new Intent(this,MusicService.class);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) startForegroundService(intent1);
                    else startService(intent1);
                    item.setIcon(R.drawable.ic_music_note_white);
                    musicOn=!musicOn;
                }else {
                    Intent intent1=new Intent(this,MusicService.class);
                    stopService(intent1);
                    item.setIcon(R.drawable.toolbar_item_music);
                    musicOn=!musicOn;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyBinder binder=(MusicService.MyBinder) service;
            musicService=binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            if(resultCode==RESULT_OK) {
                Uri uri=data.getData();//if(uri!=null) Glide.with(this).load(uri).into();
            }else{
                Bundle bundle=data.getExtras();
                Bitmap bm=(Bitmap)bundle.get("data");
                //Glide.with(this).load(bm).into();
            }
        }
    }
    public void clickIcon(View view){
        switch (view.getId()){
            case R.id.ic_bread:
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri=Uri.parse("https://www.google.com/search?q="+"빵"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.ic_fish:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"생선"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.ic_avocado:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"아보카도"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.ic_cheese:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"치즈"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.donut:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"도넛"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.jam:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"잼"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.ice_cream:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"아이스크림"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.mushroom:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"버섯"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.pizza:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"피자"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.pancake:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"핫케이크"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.sausage:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"소세지"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.shrimp:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"새우"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.potato:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"감자"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.corn:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"옥수수"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.eggplant:
                intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri=Uri.parse("https://www.google.com/search?q="+"가지"
                        +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                intent.setData(uri);
                startActivity(intent);
                break;
        }

    }
}
