package ru.ozon.details.ui

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ozon.coreui.BaseUi
import ru.ozon.coreui.setPrintableText
import ru.ozon.coreui.setPrintableTextOrGone
import ru.ozon.details.R
import ru.ozon.details.ui.adatpers.AdditionalParamsAdapterDelegate
import ru.ozon.details.ui.adatpers.ImagesAdapterDelegate
import javax.inject.Inject
import com.srggrch.coreui.R as CoreR

class ProductDetailsUi @Inject constructor(
    fragment: ProductDetailsFragment,
    vmFactory: ProductDetailsViewModelFactory
) : BaseUi<ProductDetailsFragment>(fragment) {
    private val vm: ProductDetailsViewModel by viewModels(vmFactory)

    private val viewBinding get() = fragment.viewBinding

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate() {
        vm.fetchData(args.uuid)
    }

    override fun onViewReady(view: View) {
        setupUi()

        vm.state
            .onEach(::renderState)
            .launchIn(viewScope)
    }

    private fun setupUi() {
        with(viewBinding) {
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false // todo
            }

            back.setOnClickListener {
                fragment.findNavController().popBackStack()
            }

            addToCart.setOnClickListener {
                // todo
            }

            fav.setOnClickListener {
                vm.onFavClicked()
            }

            setupCardView(contentCard, CoreR.color.white)
            setupCardView(buyCard, CoreR.color.green)
        }
    }

    private fun setupCardView(view: CardView, @ColorRes color: Int) {
        val cornerRadius = fragment.resources.getDimension(R.dimen.details_card_corner_radius)

        val shape = ShapeAppearanceModel().toBuilder().apply {
            setTopLeftCorner(CornerFamily.ROUNDED, cornerRadius)
            setTopRightCorner(CornerFamily.ROUNDED, cornerRadius)
        }.build()

        val bg = MaterialShapeDrawable(shape).apply {
            fillColor = ColorStateList.valueOf(
                ContextCompat.getColor(fragment.requireContext(), color)
            )
        }

        view.background = bg
    }


    private fun renderState(state: ProductDetailsViewModel.State) {
        when (state) {
            is ProductDetailsViewModel.State.Data -> {
                viewBinding.swipeRefresh.isRefreshing = false
                viewBinding.fav.isVisible = true
            }
            is ProductDetailsViewModel.State.Error -> {
                viewBinding.swipeRefresh.isRefreshing = false
            }
            is ProductDetailsViewModel.State.Loading -> {
                viewBinding.swipeRefresh.isRefreshing = true
            }
        }

        val data = state.product ?: return

        with(viewBinding) {
            name.setPrintableText(data.name)
            title.setPrintableText(data.name)
            description.setPrintableText(data.description)
            price.setPrintableText(data.price)
            if (data.availableCount != null && data.count != null) {
                countAvailable.setPrintableTextOrGone(data.availableCount)
                count.setPrintableTextOrGone(data.count)
            }

            rating.rating = data.rating.toFloat()

            fav.setImageResource(
                if (data.isFavorite) {
                    CoreR.drawable.ic_favorite_20
                } else {
                    CoreR.drawable.ic_favorite_border_20
                }
            )

            setupImages(images, data.images)
            setupAdditionalParams(additionalInfo, data.additionalParams)
        }
    }

    private fun setupImages(pager: ViewPager2, images: List<String>) {
        pager.adapter = ListDelegationAdapter(ImagesAdapterDelegate()).apply {
            this.items = images
        }
    }

    private fun setupAdditionalParams(
        recycler: RecyclerView,
        items: List<ProductItem.AdditionalParam>
    ) {
        recycler.adapter = ListDelegationAdapter(AdditionalParamsAdapterDelegate()).apply {
            this.items = items
        }
    }
}