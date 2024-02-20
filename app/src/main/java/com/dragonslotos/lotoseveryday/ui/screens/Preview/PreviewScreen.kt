@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.dragonslotos.lotoseveryday.ui.screens.Preview

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dragonslotos.lotoseveryday.DTO.trading_economics.CountryDATA
import com.dragonslotos.lotoseveryday.R
import com.dragonslotos.lotoseveryday.ui.theme.Fonts
import com.dragonslotos.lotoseveryday.ui.theme.LotosTheme

class PreivewScreen {
    private lateinit var ViewModel: PreviewViewModel

    //Inner card content
    @Composable
    private fun cardContent(){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            welcomeText()
            var text by rememberSaveable { mutableStateOf("") }

            var isError by rememberSaveable{ mutableStateOf(false) }
            TextField(
                modifier = Modifier.offset{IntOffset(0, 150)},
                value = text,
                onValueChange = {text = it},
                label = { Text(text = "Имя")},
                colors = TextFieldDefaults.textFieldColors(focusedLabelColor = LotosTheme.colors.primary,
                    focusedIndicatorColor = LotosTheme.colors.primary,
                    containerColor = LotosTheme.colors.background,
                    errorIndicatorColor = LotosTheme.colors.errorColor,
                    errorLabelColor = LotosTheme.colors.errorColor),
                isError = isError)
            //On error text for user
            if(isError){
                Text(modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .offset { IntOffset(0, 150) }, text = "Поле должно быть заполнено",
                    color = LotosTheme.colors.errorColor, textAlign = TextAlign.Start)
            }
            Row(modifier = Modifier.fillMaxHeight(0.9f), verticalAlignment = Alignment.Bottom) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    //check text on null info for registering person
                    onClick = { if(text != ""){ViewModel.registerPerson(text)}
                    //if text null show error
                    else{isError = true}},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LotosTheme.colors.primary)
                ){ Text(text = "Войти", fontSize = 23.sp)}
            }

        }
    }


    //Welcome Text Allign
    @Composable
    private fun welcomeText(){
        Column(modifier = Modifier
            .fillMaxWidth(0.8f),
        ) {
            Text("Добро Пожаловать!",
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                fontSize = 24.sp,
                fontFamily = Fonts.Karma(),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
            Text("Давайте знакомиться",
                fontSize = 15.sp,
                fontFamily = Fonts.Karma(),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start,
                color = LotosTheme.colors.secondary
            )

        }
    }

    //card with animation
    @Composable
    private fun card(){

        //cards states
        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        //card align
        Column(modifier = Modifier
            .fillMaxSize()
            .offset { IntOffset(0, -170) },
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //animation
            AnimatedVisibility(
                visibleState = state,
                enter = slideInVertically() + expandVertically(expandFrom = Alignment.Bottom)
                        + fadeIn(),
                exit = slideOutVertically()
                        + shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut(),
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LotosTheme.colors.background),
                    border = BorderStroke(1.dp, LotosTheme.colors.secondary),
                    elevation = CardDefaults.elevatedCardElevation(5.dp),
                    shape = RoundedCornerShape(45.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .fillMaxHeight(0.5f)
                ) {
                    cardContent()

                }
            }
        }

    }

    //logo incapsuliren
    @Composable
    private fun logo(modifier: Modifier){
        //Logo
        Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.lotos_logo), contentDescription =null, alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(LotosTheme.colors.primary))
            Text(text = "LOTOS EVERY DAY", fontSize = 24.sp, fontFamily = Fonts.Kaushan(), color = LotosTheme.colors.secondary)
        }
    }

    //Fon painter
    @Composable
    private fun Fon(){

        //background image
        Image(painter = painterResource(id = R.drawable.dragon_background), contentDescription = null,
            modifier = Modifier.fillMaxSize(1f))
        //Who created
        Column(modifier = Modifier.fillMaxSize(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "by DragonsLotos", fontSize = 20.sp, fontFamily = Fonts.Kavivanar(), color = LotosTheme.colors.tertiary)
        }
    }
    @Composable
    fun show(navController: NavController, vm: PreviewViewModel = viewModel()){
        //get context and shared preferences to check user and Viewmodel
        ViewModel = vm
        ViewModel.setContext(LocalContext.current)
        //collecting states from viewmodel
        val login by ViewModel.login.collectAsState()
        val DATA by ViewModel.countryDATAList.collectAsState()
        val enter by ViewModel.login.collectAsState()


        //Fon painter
        Fon()



        //change offset for logo
        val offset by animateIntOffsetAsState(
            targetValue = if (!login) {
                IntOffset(0, -700)
            } else { IntOffset.Zero },
            label = "offset"
        )

        // add changes for modifier
        var modifier = Modifier.offset { offset}

        //paint logo
        logo(modifier)

        //Card shower
        if(!login) {
            card()

        }
        // check data for username
        if (enter){

            //load data in printscreen
            val countryDATA = CountryDATA(DATA)
            navController.currentBackStackEntry?.arguments?.apply {
                putSerializable("Data", countryDATA)
            }
            navController.navigate("welcome")
        }
    }
}

