package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.FoodSpotTopBar
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val allRestaurants = com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.restaurants
    val fastFood = allRestaurants.filter { it.categories.contains("Rapida") }
    val mexicanFood = allRestaurants.filter { it.categories.contains("Mexicana") }
    val AsianFood = allRestaurants.filter { it.categories.contains("Asiatica") }
    val ItalianFood = allRestaurants.filter { it.categories.contains("Italiana") }

    Scaffold(
        topBar = {
            FoodSpotTopBar()
        },

        bottomBar = {
            BottomNavBar(navController = navController as NavHostController)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    RestaurantCategorySection(
                        title = "Nuestros Restaurantes",
                        restaurants = allRestaurants,
                        navController
                    )
                }
                item {
                    RestaurantCategorySection(
                        title = "Comida Rápida",
                        restaurants = fastFood,
                        navController
                    )
                }
                item {
                    RestaurantCategorySection(
                        title = "Comida Mexicana",
                        restaurants = mexicanFood,
                        navController
                    )
                }
                item {
                    RestaurantCategorySection(
                        title = "Comida Italiana",
                        restaurants = ItalianFood,
                        navController
                    )
                }
                item {
                    RestaurantCategorySection(
                        title = "Comida Asiatica",
                        restaurants = AsianFood,
                        navController
                    )
                }
            }
        }
    )
}
