package com.example.myfavoritefilms.presentation.screen.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(){
}


@Composable
fun AnimatedShimmerItem(){
    val transition = rememberInfiniteTransition()
    val alphaAnim = transition.animateFloat(
        initialValue = 2f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    ShimmerItem(alpha = alphaAnim.value)
}

@Composable
fun ShimmerItem(alpha:Float){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
        ,
        color = if(isSystemInDarkTheme()) Color.DarkGray else Color.Black
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(0.4f),
            verticalArrangement = Arrangement.Bottom
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(30.dp)
                    .alpha(alpha)
                ,
                color = if(isSystemInDarkTheme()) Color.Black else Color.DarkGray,
                shape = RoundedCornerShape(
                    size = 8.dp
                )

            ){}
            Spacer(modifier = Modifier.padding(all = 10.dp))

            repeat(3){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .alpha(alpha)
                    ,
                    color = if(isSystemInDarkTheme()) Color.Black else Color.DarkGray
                ) {}

                Spacer(modifier = Modifier.padding(4.dp)) 
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5){

                    Surface(
                        modifier = Modifier
                            .size(6.dp)
                            .alpha(alpha)
                        ,
                        color = if(isSystemInDarkTheme()) Color.Black else Color.DarkGray
                    ) {

                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            
        }

    }
}