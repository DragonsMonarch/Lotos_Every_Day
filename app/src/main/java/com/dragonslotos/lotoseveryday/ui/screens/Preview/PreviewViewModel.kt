package com.dragonslotos.lotoseveryday.ui.screens.Preview

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDataNADTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountrysTeDTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CoutnryInflationNADTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.NewsAPIDTO
import com.dragonslotos.lotoseveryday.RetroFit.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.concurrent.thread


@HiltViewModel
class PreviewViewModel : ViewModel() {

    private lateinit var context:Context;
    private lateinit var sharedPreferences: SharedPreferences

    //Mutable variables
    val login: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val countryViewSet:MutableStateFlow<Set<String>> = MutableStateFlow(setOf())
    val countryDATAList:MutableStateFlow<List<CountryDataNADTO>> = MutableStateFlow(listOf())
    //Entering Checker
    val enter: MutableStateFlow<Boolean> = MutableStateFlow(false)



    //set context and check data for user
    fun setContext(context: Context){
        this.context = context
        //Get shared preferences
        sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        //Get data for username
        val Name = sharedPreferences.getString("UserName", "NOT_REGISTERED")

        Log.d("chcker", Name.toString())
        //check data
        if(Name.toString().equals("NOT_REGISTERED")){
            login.value = false
        }
        //download data and Entering System
        else{
            //simulation data download
            loadViewCountrys()

            //enter system
            enter.value = true
        }
    }

    //register person
    fun registerPerson(name:String){

        login.value = true
        with(sharedPreferences.edit()){
            putString("UserName", name)
            apply();
        }

    }

    private fun loadInflation(country: String, countryDataNADTO: CountryDataNADTO){
        NetworkService.getInstance().jsonApi.getInflation("+0hIoUYY9jNg64DL6RwcXA==s4cyk2oCJjYs3ZiP", "https://api.api-ninjas.com/v1/inflation?country=" + country)
            .enqueue(
                object : Callback<List<CoutnryInflationNADTO>> {
                    override fun onResponse(
                        call: Call<List<CoutnryInflationNADTO>>,
                        response: Response<List<CoutnryInflationNADTO>>
                    ) {
                        val body = response.body()

                        if(body != null && body.size != 0){
                            countryDataNADTO.yearly_rate_pct = body.get(0).yearly_rate_pct
                        }

                    }
                    override fun onFailure(call: Call<List<CoutnryInflationNADTO>>, t: Throwable) {
                        t.stackTrace
                    }
                }
            )

    }
    fun loadGdp(country: String){
        NetworkService.getInstance().jsonApi.getCountryData("+0hIoUYY9jNg64DL6RwcXA==s4cyk2oCJjYs3ZiP","https://api.api-ninjas.com/v1/country?name=" + country)
            .enqueue(
                object : Callback<List<CountryDataNADTO>> {
                    override fun onFailure(call: Call<List<CountryDataNADTO>>, t: Throwable) {
                        t.stackTrace
                    }

                    override fun onResponse(
                        call: Call<List<CountryDataNADTO>>,
                        response: Response<List<CountryDataNADTO>>
                    ) {
                        val body = response.body()

                        if(body != null){
                            loadInflation(country, body.get(0))
                            countryDATAList.value += body.get(0)
                        }

                    }
                }
            )
    }
    fun loadViewCountrys(){
        var status = viewModelScope.launch {
            countryViewSet.value =  sharedPreferences.getStringSet("CountrysView", setOf())!!.toSet()
            countryDATAList.value = listOf()
            for(countryName in countryViewSet.value){
                loadGdp(countryName)
            }
        }
        if (status.isCompleted){
            //enter system
            enter.value = true
        }
    }
}