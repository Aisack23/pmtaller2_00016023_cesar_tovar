package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSpotTopBar(

) {
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Food")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFFDD7F12))) {
                        append("Spot 🍴")
                    }
                },
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = Modifier,
        scrollBehavior = null
    )
}
