package org.gbu.restaurant.business.data.local.entity

import org.gbu.restaurant.business.constants.CurrencyConstants

data class Product(
    val description: String = "",
    val id: Long = 0,
    val image: String = "",
    val isLike: Boolean = false,
    val likes: Int = 0,
    val price: Double = 0.00,
    val rate: Double = 0.0,
    val title: String = "",
    val category: Category = Category(),
    val gallery: List<String> = listOf(),
) {
    fun getPrice() = "${CurrencyConstants.CURRENCY} $price"
}