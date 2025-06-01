package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.FlashSale

@Serializable
data class FlashSaleDTO(
    @SerialName("expired_at") val expiredAt: String? = null,
    @SerialName("products") val products: List<ProductDTO>? = listOf()
)

fun FlashSaleDTO.toFlashSale() = FlashSale(
    expiredAt = expiredAt ?: "",
    products = products?.map { it.toProduct() } ?: listOf(),
)