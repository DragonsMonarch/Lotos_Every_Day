package com.dragonslotos.lotoseveryday.RetroFit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class NetworkService {
//    private static NetworkService mInstance;
//
//
//    private Retrofit mRetrofit;
//    @Inject
//    public NetworkService( Retrofit retrofit){
//        mRetrofit = retrofit;
//    }
//
//    // Get or inizialize Instance
//    public static NetworkService getInstance() {
//        if (mInstance == null) {
//            mInstance = new NetworkService();
//        }
//        return mInstance;
//    }
//
//
//    //get Http request
//    public JSONPlaceHolderApi getJSONApi() {
//        return mRetrofit.create(JSONPlaceHolderApi.class);
//    }
//}