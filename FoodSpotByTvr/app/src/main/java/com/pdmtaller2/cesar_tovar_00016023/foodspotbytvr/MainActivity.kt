package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.theme.FoodSpotByTvrTheme
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodSpotByTvrTheme {
                NavGraph()
            }
        }
    }
}