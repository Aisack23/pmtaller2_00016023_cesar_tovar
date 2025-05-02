package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model

//platillos
data class Dish(
    val id:Int,
    val name: String,
    val description: String,
    val imgUrl: String?
)

//categories
val categories = restaurants.flatMap { it.categories }.distinct()

//restaurante
data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imgUrl: String?,
    val categories: List<String>,
    val menu: List<Dish>
)

data class pedido(
    val id: String,
    val name: String,
    val imgUrl: String
)
