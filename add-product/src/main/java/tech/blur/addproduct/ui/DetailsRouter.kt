package tech.blur.addproduct.ui

import androidx.navigation.NavController
import tech.blur.addproduct.R
import javax.inject.Inject

interface AddProductRouter {
    fun goAddProduct(navController: NavController)
}

internal class AddProductRouterImpl @Inject constructor() : AddProductRouter {
    override fun goAddProduct(navController: NavController) {
        navController.navigate(R.id.graph_add_product)
    }
}