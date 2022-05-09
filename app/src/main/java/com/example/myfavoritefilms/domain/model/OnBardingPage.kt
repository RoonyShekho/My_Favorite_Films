package com.example.myfavoritefilms.domain.model

import androidx.annotation.DrawableRes
import com.example.myfavoritefilms.R

sealed class OnBoardingPage(
    @DrawableRes
    val image:Int,
    val title:String,
    val description:String
){
    object First: OnBoardingPage(
        image = R.drawable.ic_launcher_background,
        title = "Greetings",
        description = ""
    )


    object Second: OnBoardingPage(
        image = R.drawable.ic_launcher_background,
        title = "Explore",
        description = ""
    )

    object Third: OnBoardingPage(
        image = R.drawable.ic_launcher_background,
        title = "Power",
        description = ""
    )

}
