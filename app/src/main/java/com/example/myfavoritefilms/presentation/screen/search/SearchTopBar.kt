package com.example.myfavoritefilms.presentation.screen.search


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myfavoritefilms.navigation.Screen
import com.example.myfavoritefilms.ui.theme.topAppBarBackgroundColor
import com.example.myfavoritefilms.ui.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text:String,
    onTextChange:(String)->Unit,
    onSearchClick:(String)->Unit,
    onCloseClick:()->Unit
) {
    SearchWidget(text = text, onTextChange = onTextChange, onSearchClick = onSearchClick, onCloseClick = onCloseClick)

}


@Composable
fun SearchWidget(
    text:String,
    onTextChange:(String)->Unit,
    onSearchClick:(String)->Unit,
    onCloseClick:()->Unit
) {


    Surface(
        modifier= Modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {onTextChange(it) },
            placeholder = {
                Text(
                    text = "Search here..",
                    color = MaterialTheme.colors.topAppBarContentColor.copy(ContentAlpha.medium)
                )
            },
            
            leadingIcon = {
                IconButton(
                    onClick = {
                            onSearchClick(text)
                    },
                    Modifier.alpha(ContentAlpha.medium)
                ) {
                    
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                    
                }
            },

            trailingIcon = {
                IconButton(
                    onClick = {

                    },
                    Modifier.alpha(ContentAlpha.high)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close icon")
                }
            },

            keyboardOptions =
                KeyboardOptions(
                    imeAction = ImeAction.Search
                )
            ,

            keyboardActions = KeyboardActions(
                onSearch = {onSearchClick(text)}
            )

        )
    }


}