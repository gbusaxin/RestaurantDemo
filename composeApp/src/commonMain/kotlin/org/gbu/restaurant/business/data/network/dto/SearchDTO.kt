package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Search

@Serializable
data class SearchDTO(
    @SerialName("products") val products: List<ProductDTO>
)

fun SearchDTO.toSearch() = Search(
    products = products.map { it.toProduct() }
)
