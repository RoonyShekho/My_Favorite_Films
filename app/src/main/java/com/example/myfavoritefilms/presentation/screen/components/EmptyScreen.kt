package com.example.myfavoritefilms.presentation.screen.components


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myfavoritefilms.R
import com.example.myfavoritefilms.ui.theme.LightGray

@Composable
fun EmptyScreen(
    error:LoadState.Error
) {

    val message by remember{
        mutableStateOf(parseMessage(error.toString()))
    }

    val icon by remember{
        mutableStateOf(R.drawable.ic_launcher_background)
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "error icon",
            Modifier.size(120.dp),
            tint = if(isSystemInDarkTheme()) LightGray else Color.DarkGray
        )
        
        Text(
            text = message,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center,
            color = if(isSystemInDarkTheme()) LightGray else Color.DarkGray,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )

    }


}


fun parseMessage(message:String):String{
    return when{
        message.contains("SocketTimeoutException")->{
            "Server Unavailable"
        }

        message.contains("ConnectionException")->{
            "Internet Unavailable"
        }else->{
            "Unknown"
        }
    }
}