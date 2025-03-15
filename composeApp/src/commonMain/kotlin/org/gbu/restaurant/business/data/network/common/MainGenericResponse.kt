package org.gbu.restaurant.business.data.network.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainGenericResponse<T>(
    @SerialName("result") var result: T?,
    @SerialName("status") var status: Boolean?,
    @SerialName("alert") var alert: AlertResponse? = AlertResponse()
)