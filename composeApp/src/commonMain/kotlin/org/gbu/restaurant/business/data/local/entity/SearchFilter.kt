package org.gbu.restaurant.business.data.local.entity

data class SearchFilter(
    val categories: List<Category> = listOf(),
    val minPrice: Int = 0,
    val maxPrice: Int = 10,
)