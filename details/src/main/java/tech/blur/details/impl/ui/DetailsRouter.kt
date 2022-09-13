package tech.blur.details.impl.ui

import androidx.navigation.NavController
import tech.blur.details.R
import tech.blur.details.ui.ProductDetailsFragmentArgs
import java.util.*
import javax.inject.Inject

interface DetailsRouter {
    fun goDetails(navController: NavController, uuid: UUID)
}

internal class DetailsRouterImpl @Inject constructor() : DetailsRouter {
    override fun goDetails(navController: NavController, uuid: UUID) {
        navController.navigate(
            R.id.graph_details,
            ProductDetailsFragmentArgs(uuid).toBundle()
        )
    }
}