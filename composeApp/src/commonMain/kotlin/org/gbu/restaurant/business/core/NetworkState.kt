package org.gbu.restaurant.business.core

sealed class NetworkState {
    data object Good: NetworkState()
    data object Failed: NetworkState()
}