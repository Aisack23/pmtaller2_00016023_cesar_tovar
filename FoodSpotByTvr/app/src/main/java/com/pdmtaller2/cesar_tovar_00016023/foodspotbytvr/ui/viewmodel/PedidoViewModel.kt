package com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.pdmtaller2.cesar_tovar_00016023.foodspotbytvr.ui.model.pedido

class PedidoViewModel : ViewModel() {
    private val _pedidos = MutableStateFlow<List<pedido>>(emptyList())
    val pedidos: StateFlow<List<pedido>> = _pedidos

    fun agregarPedido(pedido: pedido) {
        _pedidos.value = _pedidos.value + pedido
    }

    fun eliminarPedido(pedido: pedido) {
        _pedidos.value = _pedidos.value - pedido
    }
}
