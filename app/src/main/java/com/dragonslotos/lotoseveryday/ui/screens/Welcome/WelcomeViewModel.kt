package com.dragonslotos.lotoseveryday.ui.screens.Welcome

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDATA
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDataNADTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountrysTeDTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CoutnryInflationNADTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.GDPTeDTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.NewsAPIDTO
import com.dragonslotos.lotoseveryday.RetroFit.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import javax.inject.Inject


//factory to create viw model
class WelcomeViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WelcomeViewModel(navController) as T
    }
}

@HiltViewModel
class WelcomeViewModel @Inject constructor(navController: NavController) : ViewModel() {
    //get context of application
    private var context: Context = navController.context
    private var sharedPreferences: SharedPreferences

    //Mutable variables
    val NamingText: MutableStateFlow<String> = MutableStateFlow("")
    val countrysList: MutableStateFlow<List<CountrysTeDTO>> = MutableStateFlow(listOf())
    val countryViewSet:MutableStateFlow<Set<String>> = MutableStateFlow(setOf())
    val countryDATAList:MutableStateFlow<List<CountryDataNADTO>> = MutableStateFlow(listOf())
    val newsList: MutableStateFlow<List<NewsAPIDTO.Atricles>>  = MutableStateFlow(listOf())
    lateinit var GDPList: List<GDPTeDTO>;
    var isRefreshing: Boolean by mutableStateOf(false)
    //set context and check data for user

    init {
        //Get shared preferences
        sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)


        //load data from print screen
        val Data = navController.previousBackStackEntry?.arguments?.customGetSerializable<CountryDATA>("Data")

        //if data null parsing data
        if (Data != null){
            countryDATAList.value = Data.countrys
        }

        //Get data for username
        val Name = sharedPreferences.getString("UserName", "NOT_REGISTERED")

        NamingText.value = Name.toString()
        loadViewCountrys()
        loadCountrys()
        loadNews()
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(key, T::class.java)
        else getSerializable(key) as? T
    }

    fun setContext(context: Context){
        Log.d("Awaken", "2")
        this.context = context

    }
    fun loadCountrys(){
        viewModelScope.launch {
            NetworkService.getInstance().jsonApi.countrys.enqueue(
                object : Callback<List<CountrysTeDTO>> {
                    override fun onFailure(call: Call<List<CountrysTeDTO>>, t: Throwable) {
                        t.stackTrace
                    }

                    override fun onResponse(call: Call<List<CountrysTeDTO>>, response: Response<List<CountrysTeDTO>>) {
                        val body =  response.body()
                        if(body != null){
                            countrysList.value = body
                        }
                    }

                }
            )
        }
    }
    private fun loadInflation(country: String, countryDataNADTO: CountryDataNADTO){
        NetworkService.getInstance().jsonApi.getInflation("+0hIoUYY9jNg64DL6RwcXA==s4cyk2oCJjYs3ZiP", "https://api.api-ninjas.com/v1/inflation?country=" + country)
            .enqueue(
                object : Callback<List<CoutnryInflationNADTO>>{
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
                object : Callback<List<CountryDataNADTO>>{
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

    fun addViewCountrys(country: String){
        var status = viewModelScope.launch {
            countryViewSet.value += country;
            quickSort(countryViewSet.value)
            with(sharedPreferences.edit()){
                putStringSet("CountrysView",countryViewSet.value)
                apply();
            }
        }
        if(status.isCompleted){
            loadViewCountrys()
        }
    }
    fun deleteViewCountry(country:String){
        var status = viewModelScope.launch {
            countryViewSet.value -= country
            quickSort(countryViewSet.value)
            Log.d("XUI", countryViewSet.value.toString())
            with(sharedPreferences.edit()){
                putStringSet("CountrysView",countryViewSet.value)
                apply()
            }

        }
        if(status.isCompleted){
            loadViewCountrys()
        }
    }
    fun loadViewCountrys(){
        var status = viewModelScope.launch {
            countryViewSet.value =  sharedPreferences.getStringSet("CountrysView", setOf())!!.toSet()
            countryDATAList.value = listOf()
            quickSort(countryViewSet.value)
            for(countryName in countryViewSet.value){
                loadGdp(countryName)
            }
        }
        if(status.isCompleted){
            isRefreshing = false
        }
    }
    fun loadNews(){
        viewModelScope.launch {
            NetworkService.getInstance().jsonApi.countryNews.enqueue(
                object : Callback<NewsAPIDTO>{
                    override fun onResponse(
                        call: Call<NewsAPIDTO>,
                        response: Response<NewsAPIDTO>
                    ) {
                        Log.d("XEON", response.body().toString())

                        val body = response.body()

                        if(body != null){
                            newsList.value = body.articles
                            Log.d("News", body.toString())
                        }

                    }

                    override fun onFailure(call: Call<NewsAPIDTO>, t: Throwable) {
                        Log.d("News", t.message.toString())
                    }
                }
            )
        }
    }
    fun quickSort(set: Set<String>) {
        if (set.size < 2) {
            return
        }

        val pivot = set.first()
        val less = set.filter { it < pivot }.toMutableSet()
        val greater = set.filter { it > pivot }.toMutableSet()

        set.drop(set.size)
        quickSort(less)
        quickSort(greater)

        set.plusElement(less)
        set.plusElement(pivot)
        set.plusElement(greater)
    }
}