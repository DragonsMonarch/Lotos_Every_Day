package com.dragonslotos.lotoseveryday.Dugger;

import android.content.Context;

import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import com.dragonslotos.lotoseveryday.R;
import com.dragonslotos.lotoseveryday.RetroFit.JSONPlaceHolderApi;
import com.dragonslotos.lotoseveryday.ui.Navigation.NavigatorDecorater;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {


    @Provides
    @Singleton
    public NavigatorDecorater getNavigater(){
        return new NavigatorDecorater();
    }

    @Provides
    public JSONPlaceHolderApi getJSONApi(Retrofit retrofit){
        return retrofit.create(JSONPlaceHolderApi.class);
    }
    @Singleton
    @Provides
    public Retrofit retrofit(HttpLoggingInterceptor logging, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl("https://api.tradingeconomics.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

}
