package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.BottomNavBar
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel.PedidoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.sp
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.component.FoodSpotTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController, pedidoViewModel: PedidoViewModel) {
    val pedidos by pedidoViewModel.pedidos.collectAsState()

    Scaffold(
        topBar = {
            FoodSpotTopBar()
        },

        bottomBar = { BottomNavBar(navController = navController as NavHostController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Carrito de compra",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))


                if (pedidos.isEmpty()) {
                    Text("No hay pedidos aun, ¡Empieza ya!")
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(pedidos) { pedido ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(pedido.imgUrl),
                                        contentDescription = pedido.name,
                                        modifier = Modifier.size(64.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(pedido.name, fontWeight = FontWeight.Bold)
                                    }
                                    IconButton(onClick = { pedidoViewModel.eliminarPedido(pedido) }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Eliminar"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
