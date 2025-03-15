package org.gbu.restaurant.business.data.network.common

import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.UIComponent

sealed class DataState<T> {
    data class NetworkStatus<T>(val networkState: NetworkState) : DataState<T>()

    data class Response<T>(val uiComponent: UIComponent) : DataState<T>()

    data class Data<T>(val data: T? = null, val status: Boolean? = null) : DataState<T>()

    data class Loading<T>(val progressBarState: ProgressBarState = ProgressBarState.Idle) :
        DataState<T>()
}