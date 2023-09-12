package com.artificial.learncompose1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        BoldItalicUnderLineText()
        SimpleClickableText(navController)
    }
}

@Composable
fun SimpleClickableText(navController: NavController){
    Text(
        text = "Click here to open Second Activity",
        fontSize = 18.sp,
        color = Color.Blue,

    )
}

@Composable
fun BoldItalicUnderLineText() {
    Column() {
        Text(
            text = "Smoke",
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight(23),
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Meet",
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight(23),
            textDecoration = TextDecoration.None
        )
        Text(
            text = "Coca Cola",
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight(13),
            textDecoration = TextDecoration.Underline
        )
    }
}
