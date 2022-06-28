package ru.ozon.list.ui.list

import android.animation.ValueAnimator
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ozon.coreui.FragmentLifecycleObserver
import ru.ozon.coreui.recycler.DiffAdapter
import ru.ozon.details.ui.DetailsRouter
import javax.inject.Inject
import com.srggrch.coreui.R as CoreR

class ListUi @Inject constructor(
    fragment: ListFragment,
    private val vm: ListViewModel,
    private val detailsRouter: DetailsRouter
) : FragmentLifecycleObserver<ListFragment>(fragment) {
    private val viewBinding get() = fragment.viewBinding

    private val toolbarElevation: Float by lazy { fragment.resources.getDimension(CoreR.dimen.toolbar_elevation) }

    override fun onViewReady(view: View) {
        setupUi()

        vm.state
            .onEach(::renderState)
            .launchIn(viewScope)
    }

    private fun setupUi() {
        with(viewBinding) {
            recycler.adapter = DiffAdapter(ProductsAdapterDelegate(
                onAddToCardClicked = {

                },
                onFavoriteClicked = {
                    if (it.isFavorite) {
                        vm.removeFromFavorite(it)
                    } else {
                        vm.addToFavorite(it)
                    }
                },
                onItemClicked = {
                    detailsRouter.goDetails(
                        fragment.findNavController(),
                        it.guid
                    )
                }
            ))

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if ((recycler.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                        if (toolbar.elevation == 0f) return
                        animateToolbarElevation(true)

                    } else {
                        if (toolbar.elevation > 0f) return
                        toolbar.elevation = toolbarElevation
                    }
                }
            })
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false // todo
            }
        }
    }


    private fun renderState(state: ListViewModel.State) {
        when (state) {
            is ListViewModel.State.Data -> {
                viewBinding.swipeRefresh.isRefreshing = false
            }
            is ListViewModel.State.Error -> {
                viewBinding.swipeRefresh.isRefreshing = false
            }
            is ListViewModel.State.Loading -> {
                viewBinding.swipeRefresh.isRefreshing = true
            }
        }

        showList(state.products, state)
    }

    private fun showList(list: List<ProductPreviewItem>, state: ListViewModel.State) {
        (viewBinding.recycler.adapter as DiffAdapter).items = list
        viewBinding.noItems.isVisible = list.isEmpty() && state !is ListViewModel.State.Loading
    }

    private fun animateToolbarElevation(animateOut: Boolean) {
        var valueFrom = toolbarElevation
        var valueTo = 0f
        if (!animateOut) {
            valueTo = valueFrom
            valueFrom = 0f
        }
        ValueAnimator.ofFloat(valueFrom, valueTo).setDuration(250).apply {
            startDelay = 0
            addUpdateListener {
                viewBinding.toolbar.elevation = it.animatedValue as Float
            }
            start()
        }
    }

}