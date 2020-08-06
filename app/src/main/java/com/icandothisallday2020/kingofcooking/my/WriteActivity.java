package com.icandothisallday2020.kingofcooking.my;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.icandothisallday2020.kingofcooking.R;
import com.icandothisallday2020.kingofcooking.RetrofitHelper;
import com.icandothisallday2020.kingofcooking.RetrofitService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WriteActivity extends AppCompatActivity {
    ImageView camera, iv;
    Uri uri;
    EditText title,recipe;
    TextView remove;
    final int PERMISSION_CODE=217;
    final int REQUEST_CODE=238;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        camera=findViewById(R.id.camera);
        iv =findViewById(R.id.food_image);
        title=findViewById(R.id.et_title);
        recipe=findViewById(R.id.et_recipe);
        remove=findViewById(R.id.removethistext);

        /*카메라 외부저장소 접근 퍼미션*/
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permission=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permission== PackageManager.PERMISSION_DENIED)
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},217);
        }//*
        /*카메라*/
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                setUri();
                if(uri!=null) intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,238);

            }
        });//*
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            Uri uri= data.getData();
            //imgUrl=getRealPathFromUri(uri);
            if(uri!=null) Glide.with(this).load(uri).into(iv);
            imgPath=getRealPathFromUri(uri);
            //잘 되었는지 확인
            new AlertDialog.Builder(this).setMessage(imgPath).show();


            if(data!=null &&uri!=null)Glide.with(this).load(uri).into(iv);
            else if(data!=null){
                Bundle bundle=data.getExtras();
                Bitmap bm=(Bitmap) bundle.get("data");
                Glide.with(this).load(bm).into(iv);
            }
            else Toast.makeText(this, "ERROR - No Intent", Toast.LENGTH_SHORT).show();
        }
        //Glide.with(this).load(uri).into(iv);
        else if(requestCode==282 && requestCode==RESULT_OK){
            Uri uri = data.getData();
            if(uri!=null) Glide.with(this).load(uri).into(iv);
            imgPath=getRealPathFromUri(uri);

            new AlertDialog.Builder(this).setMessage(imgPath).show();
        }
        remove.setText("");
    }
    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        View snackBar=findViewById(R.id.sb);
        switch (requestCode){
            case PERMISSION_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Snackbar.make(snackBar,"You can save photos",
                            Snackbar.LENGTH_LONG).setAction("OK",null).show();
                }else {
                    Snackbar.make(snackBar,"You can't save photos",
                            Snackbar.LENGTH_LONG).setAction("OK",null).show();
                }
                break;
        }
    }
    
    public void setUri(){//캡쳐이미지 uri 저장 메소드
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        SimpleDateFormat date=new SimpleDateFormat("yyyyMMddhhmmss");
        String file="IMG_"+date.format(new Date())+".jpg";
        File file1=new File(path,file);
        
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.N) uri=Uri.fromFile(file1);
        else uri= FileProvider.getUriForFile(this,"com.icandothisallday2020.kingofcooking.provider",file1);
        //new AlertDialog.Builder(WriteActivity.this).setMessage("Wow!!! Looks delicious! ").create().show();
    }
    

    public void clickSave(View view) {
        String title=this.title.getText().toString();
        String recipe=this.recipe.getText().toString();
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd h:mmaa");
        String date = simpleDate.format(rightNow.getTime());

//        Retrofit.Builder builder=new Retrofit.Builder();
//        builder.baseUrl("http://soon0.dothome.co.kr");
//        builder.addConverterFactory(ScalarsConverterFactory.create());
//        Retrofit retrofit=builder.build();
//
//        File file=new File(imgUrl);
//        RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),file);
//        MultipartBody.Part filePart=MultipartBody.Part.createFormData("img",file.getName(),requestBody);
//
//        Map<String,String> dataPart=new HashMap<>();
//        dataPart.put("title",title);
//        dataPart.put("recipe",recipe);
//        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
//        Call<String> call=retrofitService.postDataWithFile(dataPart,filePart);
//
        Retrofit retrofit= RetrofitHelper.getInstanceFromScalars();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Map<String,String> dataPart=new HashMap<>();
        dataPart.put("title",title);
        dataPart.put("recipe",recipe);
        dataPart.put("date",date);

        MultipartBody.Part filePart=null;
        if(imgPath!=null){
            File file=new File(imgPath);
            RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),file);
            filePart=MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        }
        Call<String> call=service.postDataWithFile(dataPart,filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                    Toast.makeText(WriteActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(WriteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


//        Intent i=getIntent();
//        i.putExtra("Title",title);
//        i.putExtra("Recipe",recipe);
//        i.putExtra("Date",date);
//        this.setResult(RESULT_OK,i);

        finish();
    }

    public void clickCancel(View view) {
        finish();
    }
}