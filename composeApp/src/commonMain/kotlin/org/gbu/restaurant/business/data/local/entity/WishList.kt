package org.gbu.restaurant.business.data.local.entity

data class WishList(
    val categories: List<Category> = listOf(),
    val products: List<Product> = listOf()
)