package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.Restaurant

@Composable
fun RestaurantCategorySection(
    title: String,
    restaurants: List<Restaurant>,
    navController: NavController
) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(restaurants) { restaurant ->
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .padding(end = 12.dp)
                        .clickable {
                            navController.navigate("restaurantDetail/${restaurant.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(model = restaurant.imgUrl),
                            contentDescription = restaurant.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                        )
                        Text(
                            text = restaurant.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

