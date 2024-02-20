package com.dragonslotos.lotoseveryday.RetroFit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;

    private Retrofit mRetrofit;

    //.....Main URL.....
    private static final String BASE_URL_TRADINGECONOMICS = "https://api.tradingeconomics.com";

    private static final String BASE_URL_NINJA_API = "https://api.api-ninjas.com";
    //Initialize Retrofit...
    private NetworkService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);
        //Ask factory to create instance of retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_TRADINGECONOMICS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    // Get or inizialize Instance
    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }


    //get Http request
    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}