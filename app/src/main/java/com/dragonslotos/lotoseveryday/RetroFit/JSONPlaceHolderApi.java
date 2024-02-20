package com.dragonslotos.lotoseveryday.RetroFit;

import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDataNADTO;
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountrysTeDTO;
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CoutnryInflationNADTO;
import com.dragonslotos.lotoseveryday.DTO.trading_economics.GDPTeDTO;
import com.dragonslotos.lotoseveryday.DTO.trading_economics.NewsAPIDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Url;

//Interface of http requests
public interface JSONPlaceHolderApi {

//-------------------Trading Economics API--------------------------

    //Key registration in Trading Economics
    String API_KEY_REGISTER = "71dcb33e5e3b402:6y5il3nitg5udr0";

    //Get Countrys
    @GET("/country?c="+ API_KEY_REGISTER)
    public Call<List<CountrysTeDTO>> getCountrys();
    @GET("/historical/country/andorra/indicator/gdp?c="+ API_KEY_REGISTER)
    public Call<List<GDPTeDTO>> getGDP();

    //Get GPD
//    @GET()
//    public Call<> getGPD(@Url String url);
//------------------------------------------------------------------

//-------------------------Ninja Api--------------------------------
    @GET()
    public Call<List<CountryDataNADTO>> getCountryData(@Header("X-Api-Key") String token, @Url String url);
    @GET
    public Call<List<CoutnryInflationNADTO>> getInflation(@Header("X-Api-Key") String token, @Url String url);
//------------------------------------------------------------------

//---------------------------News API-------------------------------
    String API_KEY_REGISTER_NEWS = "e76dd514f60d4d13b30aff0fe941486f";
    @GET("https://newsapi.org/v2/top-headlines?country=ru&category=business&apiKey=" + API_KEY_REGISTER_NEWS)
    public Call<NewsAPIDTO> getCountryNews();
//------------------------------------------------------------------
}