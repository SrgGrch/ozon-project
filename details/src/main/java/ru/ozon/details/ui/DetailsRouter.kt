package ru.ozon.details.ui

import androidx.navigation.NavController
import ru.ozon.details.R
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