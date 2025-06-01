package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Banner

@Serializable
data class BannerDTO(
    @SerialName("banner") val banner: String?,
    @SerialName("id") val id: Long?
)

fun BannerDTO.toBanner() = Banner(banner = banner ?: "", id = id ?: 0)