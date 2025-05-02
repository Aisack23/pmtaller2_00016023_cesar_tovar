package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.FoodSpotTopBar
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.BottomNavBar
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val query by viewModel.query.collectAsState()
    val results by viewModel.results.collectAsState()

    Scaffold(
        topBar = {
            FoodSpotTopBar()
        },
        bottomBar = {
            BottomNavBar(navController = navController as NavHostController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::onQueryChanged,
                label = { Text("Buscar restaurante o plato") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (query.isNotBlank()) {
                if (results.isEmpty()) {
                    Text("No se encontraron resultados")
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            RestaurantCategorySection(
                                title = "Resultados",
                                restaurants = results,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
