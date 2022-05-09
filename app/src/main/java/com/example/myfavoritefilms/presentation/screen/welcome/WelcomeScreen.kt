package com.example.myfavoritefilms.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.domain.model.OnBoardingPage
import com.example.myfavoritefilms.navigation.Screen
import com.example.myfavoritefilms.presentation.screen.welcome.WelcomeViewModel
import com.example.myfavoritefilms.ui.theme.Purple700
import com.example.myfavoritefilms.ui.theme.inActivePager
import com.example.myfavoritefilms.ui.theme.welcomeScreenBackgroundColor
import com.google.accompanist.pager.*

@ExperimentalPagingApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun WelcomeScreen(
    navController:NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
){


    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )

    val pagerState = rememberPagerState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3, state = pagerState,
            verticalAlignment = Alignment.Top
        ) {position->
            PagerScreen(onBoardingPage = pages[position])
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
            ,
            pagerState = pagerState,
            activeColor = Purple700,
            inactiveColor = MaterialTheme.colors.inActivePager,
            indicatorWidth = 12.dp,
            spacing = 8.dp,
        )

        FinishButton(
            pagerState = pagerState,
            modifier = Modifier.weight(1f)
        ){
            navController.popBackStack()
            welcomeViewModel.saveOnBoardingState(true)
            navController.navigate(Screen.Home.route)
        }
    }

}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    pagerState:PagerState,
    modifier: Modifier,
    onClick:()->Unit
){

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {

            Button(onClick = onClick) {
                Text(text = "Finish")
            }
        }


    }

}


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        Image(painter = painterResource(id = onBoardingPage.image), contentDescription = "onBoarding image")

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 20.dp),
            text = onBoardingPage.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize,
        )


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            text = onBoardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
        )
    }

}

@Preview
@Composable
fun OnBoardingPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.Second)
}