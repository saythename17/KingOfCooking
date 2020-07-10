package com.icandothisallday2020.kingofcooking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {
    @Multipart
    @POST("/Retrofit/koc.php")
    Call<String> postDataWithFile(@PartMap Map<String,String> dataPart, @Part MultipartBody.Part filePart);

    @GET("Retrofit/koc2.php")
    Call<ArrayList<WriteItem>> loadDataFromBoard();
}
