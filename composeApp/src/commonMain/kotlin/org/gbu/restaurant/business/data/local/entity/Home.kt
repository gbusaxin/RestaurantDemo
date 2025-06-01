package org.gbu.restaurant.business.data.local.entity

data class Home(
    val address: Address = Address(),
    val banners: List<Banner> = listOf(),
    val categories: List<Category> = emptyList(),
    val flashSale: FlashSale = FlashSale(),
    val mostSale: List<Product> = listOf(),
    val newestProduct: List<Product> = listOf(),
)