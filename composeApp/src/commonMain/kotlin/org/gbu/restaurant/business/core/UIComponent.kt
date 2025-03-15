package org.gbu.restaurant.business.core

import org.gbu.restaurant.business.data.network.common.AlertResponse

sealed class UIComponent {
    data class Toast(
        val alert: AlertResponse
    ) : UIComponent()

    data class Dialog(
        val alert: AlertResponse
    ) : UIComponent()


    data class ToastSimple(
        val title: String,
    ) : UIComponent()

    data class DialogSimple(
        val title: String,
        val description: String
    ) : UIComponent()


    data class None(
        val message: String,
    ) : UIComponent()
}