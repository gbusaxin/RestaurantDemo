package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.WishList

@Serializable
data class WishListDTO(
    @SerialName("categories") val categories: List<CategoryDTO>? = listOf(),
    @SerialName("products") val products: List<ProductDTO>? = listOf()
)

fun WishListDTO.toWishList() = WishList(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    products = products?.map { it.toProduct() } ?: listOf()
)

