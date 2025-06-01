package org.gbu.restaurant.business.data.local.entity

data class FlashSale(
    val expiredAt: String = "",
    val products: List<Product> = listOf()
)