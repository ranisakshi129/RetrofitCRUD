package com.stu.retrofitcrud.utils;
import com.stu.retrofitcrud.retrofit.ApiInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    ApiInterface apiInterface;
    private RetrofitClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tracking.rrinfrapro.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

       apiInterface = retrofit.create(ApiInterface.class);
    }
    public static synchronized RetrofitClient getInstance(){

        if(instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }

    public ApiInterface getApiInterface(){
        return apiInterface;
    }

}
