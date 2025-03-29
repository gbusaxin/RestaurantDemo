package org.gbu.restaurant.utils

import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.network.common.AlertResponse
import org.gbu.restaurant.business.data.network.common.DataState

fun <T> handleUseCaseException(e: Exception): DataState<T> {
    val splitList: List<String> = e.message?.split(THROWABLE_DIVIDER) ?: listOf()

    if (splitList.size <= 1) {
        return DataState.Response(uiComponent = UIComponent.None(""))
    }

    val title = splitList[1]
    val message = splitList[2]

    e.printStackTrace()

    return DataState.Response(
        uiComponent = UIComponent.Dialog(
            AlertResponse(
                title, message
            )
        )
    )
}

const val THROWABLE_DIVIDER = "THROWABLE_DIVIDER"