package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.Restaurant

class SearchViewModel : ViewModel() {
    // Lista original
    private val allRestaurants = com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.restaurants

    // Estado observable
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _results = MutableStateFlow<List<Restaurant>>(allRestaurants)
    val results: StateFlow<List<Restaurant>> = _results

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        val filtered = allRestaurants.filter { restaurant ->
            restaurant.name.contains(newQuery, ignoreCase = true) ||
                    restaurant.menu.any { it.name.contains(newQuery, ignoreCase = true) }
        }
        _results.value = filtered
    }
}