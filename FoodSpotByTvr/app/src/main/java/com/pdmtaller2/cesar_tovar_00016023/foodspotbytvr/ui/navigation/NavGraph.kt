package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view.HomeScreen
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view.OrderScreen
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view.RestaurantDetailScreen
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view.SearchScreen
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel.PedidoViewModel

object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val ORDERS = "orders"
}

@Composable
fun NavGraph(startDestination: String = Routes.HOME) {
    val navController = rememberNavController()
    val pedidoViewModel: PedidoViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(Routes.SEARCH) {
            SearchScreen(navController = navController)
        }
        composable(Routes.ORDERS) {
            OrderScreen(navController = navController, pedidoViewModel = pedidoViewModel)
        }
        composable("restaurantDetail/{restaurantId}") { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId")?.toIntOrNull()
            restaurantId?.let {
                RestaurantDetailScreen(
                    restaurantId = it,
                    navController = navController,
                    pedidoViewModel = pedidoViewModel//este lo voy a usar cuando funcione lo de carrito
                )
            }
        }
    }
}
