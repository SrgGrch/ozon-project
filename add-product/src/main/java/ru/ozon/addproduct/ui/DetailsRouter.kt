package ru.ozon.addproduct.ui

import androidx.navigation.NavController
import ru.ozon.addproduct.R
import javax.inject.Inject

interface AddProductRouter {
    fun goAddProduct(navController: NavController)
}

internal class AddProductRouterImpl @Inject constructor() : AddProductRouter {
    override fun goAddProduct(navController: NavController) {
        navController.navigate(R.id.graph_add_product)
    }
}