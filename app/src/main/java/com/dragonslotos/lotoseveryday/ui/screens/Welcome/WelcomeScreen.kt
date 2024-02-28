package com.dragonslotos.lotoseveryday.ui.screens.Welcome

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDataNADTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountrysTeDTO
import com.dragonslotos.lotoseveryday.DTO.trading_economics.NewsAPIDTO
import com.dragonslotos.lotoseveryday.R
import com.dragonslotos.lotoseveryday.ui.Navigation.NavigatorDecorater
import com.dragonslotos.lotoseveryday.ui.theme.Fonts
import com.dragonslotos.lotoseveryday.ui.theme.LotosTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//testing data
private var tdCountrys: List<CountrysTeDTO> = listOf(
    CountrysTeDTO("Britain", "Europe", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Mexico", "South America", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Australia", "Australia", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Britain", "Europe", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Mexico", "South America", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Australia", "Australia", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Britain", "Europe", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Mexico", "South America", "G100", "ISO3", "ISO2"),
    CountrysTeDTO("Australia", "Australia", "G100", "ISO3", "ISO2")
)
private var tdNews: List<String> = listOf(
    "Герман Греф: Сбер предотвращает 99,6% случаев мошенничества в отношении своих клиентов",
    "Герман Греф: Сбер предотвращает 99,6% случаев мошенничества в отношении своих клиентов",
    "Герман Греф: Сбер предотвращает 99,6% случаев мошенничества в отношении своих клиентов",
    "Герман Греф: Сбер предотвращает 99,6% случаев мошенничества в отношении своих клиентов"
)

@AndroidEntryPoint
class WelcomeScreen constructor(ViewModel: WelcomeViewModel): Fragment() {
    private val ViewModel: WelcomeViewModel = ViewModel
    @Inject
    lateinit var navController: NavigatorDecorater
    //Data of country choose
    @Composable
    fun countryRow(countrysTeDTO: CountrysTeDTO){
        //button to select country
        Button(
            colors = ButtonDefaults.buttonColors(contentColor = LotosTheme.colors.borderColor,
                containerColor = LotosTheme.colors.background),
            onClick = { ViewModel.addViewCountrys(countrysTeDTO.country);}) {
            //Data view
            Row (horizontalArrangement = Arrangement.SpaceAround){
                Text(text = countrysTeDTO.country,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 15.sp)
                Text(text = countrysTeDTO.continent,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 15.sp)

                Text(text = countrysTeDTO.isO3,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 15.sp)
                Text(text = countrysTeDTO.isO2,
                    fontSize = 15.sp)
            }
        }
    }

    @Composable
    fun cardStatistic(countryDataNADTO: CountryDataNADTO){
        Column(modifier = Modifier.padding(15.dp,15.dp, 15.dp, bottom = 30.dp)) {

            Row(modifier = Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
                Text(text = countryDataNADTO.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = LotosTheme.colors.borderColor,
                    modifier = Modifier.padding(bottom = 1.dp))
                IconButton(modifier = Modifier.padding(end = 2.dp),
                    onClick = { ViewModel.deleteViewCountry(countryDataNADTO.name) }) {
                    Icon(
                        modifier = Modifier
                            .rotate(45f)
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.add), contentDescription = "ADD", tint = LotosTheme.colors.borderColor)
                }
            }
            Divider(color = LotosTheme.colors.borderColor,
                modifier = Modifier.padding(bottom = 10.dp))
            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = LotosTheme.colors.borderColor)
                .padding(start = 1.dp)){
                Column(modifier = Modifier
                    .background(color = LotosTheme.colors.tertiary)
                    .padding(start = 5.dp)
                    .fillMaxSize()) {
                    Row(modifier = Modifier.padding(bottom = 3.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ВВП:",
                            color = LotosTheme.colors.borderColor,
                            fontSize = 20.sp,
                        )
                        Text(text = countryDataNADTO.gdp.toString(), textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                            color = LotosTheme.colors.borderColor,
                            fontSize = 18.sp)
                    }

                    Row(modifier = Modifier.padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Темпы роста:",
                            color = LotosTheme.colors.borderColor,
                            fontSize = 20.sp)
                        Text(text = countryDataNADTO.gdp_growth.toString(), textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                            color = LotosTheme.colors.borderColor,
                            fontSize = 18.sp)
                    }

                    Row(modifier = Modifier.padding(bottom = 3.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Уровень\nБезработицы:",
                            color = LotosTheme.colors.borderColor,
                            fontSize = 20.sp)
                        if(countryDataNADTO.unemployment != null){
                            Text(text = countryDataNADTO.unemployment, textAlign = TextAlign.End, modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                                color = LotosTheme.colors.borderColor,
                                fontSize = 18.sp)
                        }
                        else{
                            Text(text = "Not DATA", textAlign = TextAlign.End, modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                                color = LotosTheme.colors.borderColor,
                                fontSize = 18.sp)
                        }
                    }
                    Row(modifier = Modifier.padding(bottom = 3.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Инфляция:",
                            color = LotosTheme.colors.borderColor,
                            fontSize = 20.sp)
                        if(countryDataNADTO.yearly_rate_pct != null && countryDataNADTO.yearly_rate_pct != 0.0){
                            Text(text = countryDataNADTO.yearly_rate_pct.toString(), textAlign = TextAlign.End, modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                                color = LotosTheme.colors.borderColor,
                                fontSize = 18.sp)
                        }
                        else{
                            Text(text = "Not DATA", textAlign = TextAlign.End, modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                                color = LotosTheme.colors.borderColor,
                                fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
    //CountryStatistik
    @Composable
    fun countryStatistic(){
        val openAlertDialog = remember { mutableStateOf(false) }
        //Adding Country button
        Column(modifier = Modifier.fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.Start) {
            //Box layout for button with icon
            Box() {
                //Border
                Image(painter = painterResource(id = R.drawable.borders), contentDescription = "",
                    modifier = Modifier.matchParentSize(), colorFilter = ColorFilter.tint(LotosTheme.colors.borderColor))
                //Text with Icon button
                Row(modifier = Modifier.padding(bottom = 10.dp, start = 5.dp, top = 2.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Добавить Страну",
                        fontFamily = Fonts.Karma(),
                        color = LotosTheme.colors.borderColor,
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                    )
                    IconButton(onClick = { openAlertDialog.value = true; ViewModel.loadCountrys() },
                        modifier = Modifier.padding(bottom = 2.dp)) {
                        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "", tint = LotosTheme.colors.borderColor)
                    }
                }
            }

        }

        //checker for dialog
        if(openAlertDialog.value){
            //Ask diolog
            Dialog(onDismissRequest = { openAlertDialog.value = false }) {
                //Card dialog with countrys
                Card(
                    colors = CardDefaults.cardColors(containerColor = LotosTheme.colors.background),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(45.dp),
                ) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        //Exit button
                        IconButton(modifier = Modifier.padding(5.dp),
                            onClick = {openAlertDialog.value = false }) {
                            Icon(
                                modifier = Modifier
                                    .rotate(45f)
                                    .size(30.dp),
                                painter = painterResource(id = R.drawable.add), contentDescription = "ADD")
                        }
                        Text(text = "Страна",
                            fontSize = 20.sp,
                            fontFamily = Fonts.Karma(),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        )
                    }

                    //Lazy column for country list
                    LazyColumn(modifier = Modifier
                        .padding(start = 0.dp, end = 0.dp, bottom = 45.dp)){
                        items(ViewModel.countrysList.value){ country ->
                            //row of country data
                            countryRow(countrysTeDTO = country)
                            //Line of country end
                            Divider(color = LotosTheme.colors.borderColor, thickness = 1.dp)
                        }
                    }
                }
            }
        }





    }


    @Composable
    fun welcome(name:String){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LotosTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Доброе утро,",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(5.dp, 5.dp, 5.dp, 0.dp),
                fontSize = 40.sp,
                color = LotosTheme.colors.borderColor,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
            Text(
                name + "!",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(5.dp, 0.dp, 5.dp, 0.dp),
                fontSize = 40.sp,
                color = LotosTheme.colors.borderColor,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Normal
            )

            Text(
                "Вот сводка для вас сейчас! ",
                Modifier
                    .fillMaxWidth(0.9f)
                    .padding(5.dp, 0.dp, 5.dp, 5.dp),
                color = LotosTheme.colors.secondary,
                fontFamily = Fonts.Pairfair(),
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
    @Composable
    fun countryCard(statistic: CountryDataNADTO){
        //animation parameters
        var lapVisible by remember { mutableStateOf(false) }
        val animatedOffset by animateOffsetAsState(
            targetValue = if (lapVisible) Offset.Zero else Offset(-1000f, 0f),
            label = "Lap alpha",
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing,
            )
        )
        val animatedLapAlpha by animateFloatAsState(
            targetValue = if (lapVisible) 1f else 0f,
            label = "Lap alpha",
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing,
            )
        )
        //data card
        Card(modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(5.dp)
            .offset {
                lapVisible = true;IntOffset(
                animatedOffset.x.toInt(),
                animatedOffset.y.toInt()
            )
            }
            .graphicsLayer {
                lapVisible = true;
                alpha = animatedLapAlpha
            },
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = LotosTheme.colors.tertiary),
            shape = RoundedCornerShape(0.dp, 50.dp, 50.dp, 50.dp)
        ) {
            cardStatistic(statistic)
        }
    }
    @Composable
    fun news(index: Int, item: NewsAPIDTO.Atricles, newsData: List<NewsAPIDTO.Atricles>, activity: Activity){
        // display only 4  item
        if (index > 3) {
        }
        //two cards display in row
        else if ((index + 1) % 2 == 0) {
        } else {
            var lapVisible by remember { mutableStateOf(false) }
            val animatedLapAlpha by animateFloatAsState(
                targetValue = if (lapVisible) 1f else 0f,
                label = "Lap alpha",
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearEasing,
                )
            )
            //news cards
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                        .graphicsLayer {
                            lapVisible = true
                            alpha = animatedLapAlpha
                        },
                    border = BorderStroke(1.dp, LotosTheme.colors.secondary),
                    colors = CardDefaults.outlinedCardColors(containerColor = LotosTheme.colors.background)
                ) {
                    //url to news
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        shape = RectangleShape
                        ,onClick = {
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(item.url)
                            )

                            activity.startActivity(browserIntent)
                        }) {
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 16.sp,
                            color = LotosTheme.colors.borderColor
                        )
                    }
                }
                //news card
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                        .graphicsLayer {
                            lapVisible = true
                            alpha = animatedLapAlpha
                        },
                    border = BorderStroke(1.dp, LotosTheme.colors.secondary),
                    colors = CardDefaults.outlinedCardColors(containerColor = LotosTheme.colors.background)
                ) {
                    //url to news
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        shape = RectangleShape
                        ,onClick = {
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(newsData.get(index+1).url)
                            )

                            activity.startActivity(browserIntent)
                        }) {
                        Text(
                            text = newsData.get(index + 1).title,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 16.sp,
                            color = LotosTheme.colors.borderColor
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun show(){
        //get context and shared preferences to check user and Viewmodel

        val NameingText: String by ViewModel.NamingText.collectAsState()
        val CountryData: List<CountryDataNADTO> by ViewModel.countryDATAList.collectAsState()
        val newsData: List<NewsAPIDTO.Atricles> by ViewModel.newsList.collectAsState()
        val activity = LocalContext.current as Activity

        val pullRefreshState = rememberPullRefreshState(
            refreshing = ViewModel.isRefreshing,
            onRefresh = {
                ViewModel.loadNews()
                ViewModel.loadViewCountrys()
            })


        Scaffold { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(color = LotosTheme.colors.background)
                    .pullRefresh(pullRefreshState)
            ) {
                //Welcom text for user
                LazyColumn{

                    item {
                        welcome(name = NameingText)
                    }
                    //adding country to look statistic
                    item { countryStatistic() }
                    //create card statistic
                    items(CountryData) { statistic ->
                        countryCard(statistic = statistic)
                    }
                    //economics news
                    item {
                        Row (modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(10.dp)
                            ,horizontalArrangement = Arrangement.Center){

                            Text(text = "Новости экономики",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                color = LotosTheme.colors.borderColor,
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        LotosTheme.colors.borderColor,
                                        shape = RoundedCornerShape(45.dp)
                                    )
                                    .padding(10.dp)
                            )
                        }
                    }
                    //view news
                    itemsIndexed(newsData) { index, item ->
                        news(index = index, item = item, newsData = newsData, activity = activity)
                    }
                }
                PullRefreshIndicator(
                    refreshing = ViewModel.isRefreshing,
                    state = pullRefreshState,
                    Modifier.align(Alignment.TopCenter),
                    contentColor = LotosTheme.colors.primary,
                )
            }
        }
    }
}
