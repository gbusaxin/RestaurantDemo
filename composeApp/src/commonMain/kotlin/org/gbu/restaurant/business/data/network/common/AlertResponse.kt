package org.gbu.restaurant.business.data.network.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlertResponse(
    @SerialName("title") var title: String = "",
    @SerialName("message") var message: String = ""
)
