package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel.PedidoViewModel
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.pedido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    restaurantId: Int,
    navController: NavController,
    pedidoViewModel: PedidoViewModel
) {
    val restaurant = com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.restaurants
        .firstOrNull { it.id == restaurantId }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalles del Restaurante") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { paddingValues ->
            restaurant?.let {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Text(
                        text = "Menú:",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LazyColumn {
                        it.menu.forEach { dish ->
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(dish.imgUrl),
                                            contentDescription = dish.name,
                                            modifier = Modifier
                                                .height(180.dp)
                                                .fillMaxWidth(),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = dish.name,
                                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                                        )
                                        Text(
                                            text = dish.description,
                                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W100, fontSize = 20.sp)
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Button(
                                            onClick = {
                                                val nuevoPedido = pedido(
                                                    id = dish.id.toString(),
                                                    name = dish.name,
                                                    imgUrl = dish.imgUrl.toString()
                                                )
                                                pedidoViewModel.agregarPedido(nuevoPedido)

                                                CoroutineScope(Dispatchers.Main).launch {
                                                    snackbarHostState.showSnackbar(
                                                        message = "${dish.name} añadido al carrito",
                                                        withDismissAction = true
                                                    )
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = "Agregar al carrito"
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(text = "Añadir al carrito")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } ?: Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("El restaurante no está en el sistema :c.", color = Color.White)
            }
        }
    )
}
