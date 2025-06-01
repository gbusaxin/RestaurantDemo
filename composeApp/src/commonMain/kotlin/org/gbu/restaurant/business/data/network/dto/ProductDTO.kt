package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.data.local.entity.Product

@Serializable
data class ProductDTO(
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Long?,
    @SerialName("image") val image: String?,
    @SerialName("isLike") val isLike: Boolean?,
    @SerialName("likes") val likes: Int?,
    @SerialName("price") val price: Double?,
    @SerialName("rate") val rate: Double?,
    @SerialName("title") val title: String?,
    @SerialName("category") val category: CategoryDTO?,
    @SerialName("gallery") val gallery: List<String>?,
)

fun ProductDTO.toProduct() = Product(
    description = description ?: "",
    id = id ?: 0,
    image = image ?: "",
    isLike = isLike ?: false,
    likes = likes ?: 0,
    price = price ?: 0.00,
    rate = rate ?: 0.0,
    title = title ?: "",
    category = category?.toCategory() ?: Category(),
)