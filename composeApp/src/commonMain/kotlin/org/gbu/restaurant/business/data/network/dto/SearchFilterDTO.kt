package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.SearchFilter

@Serializable
data class SearchFilterDTO(
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("min_price") val minPrice: Int?,
    @SerialName("max_price") val maxPrice: Int?
)

fun SearchFilterDTO.toSearchFilter() = SearchFilter(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    minPrice = minPrice ?: 0,
    maxPrice = maxPrice ?: 0
)