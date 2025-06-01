package org.gbu.restaurant.ui.screens.search.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.usecase.GetSearchFilterUseCase
import org.gbu.restaurant.business.usecase.SearchUseCase

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val getSearchFilterUseCase: GetSearchFilterUseCase
) : BaseViewModel<SearchEvent, SearchState, Nothing>() {
    override fun setInitialState(): SearchState = SearchState()

    override fun onTriggerEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetNextPage -> {
                getNextPage()
            }

            is SearchEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is SearchEvent.OnUpdateFilterDialogState -> {
                onUpdateFilterDialogState(event.value)
            }

            is SearchEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }

            is SearchEvent.OnUpdatePriceRange -> {
                onUpdatePriceRange(event.value)
            }

            is SearchEvent.OnUpdateSearchText -> {
                onUpdateSearchText(event.value)
            }

            is SearchEvent.OnUpdateSelectedCategory -> {
                onUpdateSelectedCategory(event.categories)
            }

            is SearchEvent.OnUpdateSelectedSort -> {
                onUpdateSelectedSort(event.value)
            }

            is SearchEvent.OnUpdateSortDialogState -> {
                onUpdateSortDialogState(event.value)
            }

            is SearchEvent.Search -> {
                search(
                    minPrice = event.minPrice,
                    maxPrice = event.maxPrice,
                    categories = event.categories
                )
            }
        }
    }

    init {
        getSearchFilter()
    }

    private fun onUpdateSelectedSort(value: Int) {
        setState { copy(selectedSort = value) }
    }

    private fun onUpdatePriceRange(value: ClosedFloatingPointRange<Float>) {
        setState { copy(selectedRange = value) }
    }

    private fun onUpdateSortDialogState(value: UIComponentState) {
        setState { copy(sortDialogState = value) }
    }

    private fun onUpdateFilterDialogState(value: UIComponentState) {
        setState { copy(filterDialogState = value) }
    }

    private fun onUpdateSearchText(value: String) {
        setState { copy(searchText = value) }
    }

    private fun getSearchFilter() {
        executeUseCase(getSearchFilterUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState {
                    copy(
                        searchFilter = it,
                        selectedRange = it.minPrice.toFloat()..it.maxPrice.toFloat()
                    )
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {

        })
    }

    private fun search(
        minPrice: Int? = null,
        maxPrice: Int? = null,
        categories: List<Category>? = null
    ) {
        resetPaging()
        executeUseCase(
            searchUseCase.execute(
            SearchUseCase.Params(
                page = state.value.page,
                minPrice = minPrice,
                maxPrice = maxPrice,
                categories = categories,
                sort = state.value.selectedSort
            )
        ), onSuccess = {
            it?.let { setState { copy(search = it) } }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {

        })
    }

    private fun resetPaging() {
        setState { copy(page = 1, hasNextPage = true) }
    }

    private fun getNextPage() {
        setState { copy(page = state.value.page + 1) }
    }

    private fun onUpdateSelectedCategory(categories: List<Category>) {
        setState { copy(selectedCategory = categories) }
    }

    private fun onRetryNetwork() {
        getSearchFilter()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }

}








