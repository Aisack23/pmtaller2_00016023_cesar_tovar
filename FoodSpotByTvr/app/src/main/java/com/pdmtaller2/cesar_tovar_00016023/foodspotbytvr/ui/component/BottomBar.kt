package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController, modifier: Modifier = Modifier) {
    //iconos
    val items = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Search,
        Icons.Outlined.ShoppingCart
    )
    //rutas a usar
    val routes = listOf("home", "search", "orders")
    //labels bajo los iconos
    val labels = listOf("inicio", "buscar", "carrito")

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, icon ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = labels[index],
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(labels[index]) },
                selected = currentRoute == routes[index],
                onClick = {
                    if (currentRoute != routes[index]) {
                        navController.navigate(routes[index]) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}