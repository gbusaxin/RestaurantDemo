package org.gbu.restaurant.business.data.local.entity

data class Order(
    val products: List<Product>,
    val status: Int,
    val code: String,
    val createdAt: String,
    val address: Address,
    val shippingType: ShippingType
) {
    fun getAmount() = "$ ${products.sumOf { it.price } + shippingType.price}"
}
