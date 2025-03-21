package org.gbu.restaurant.business.data.entity

data class ShippingType(
    val title: String,
    val price: Double,
    val arrivalDate: Int
){
    fun getEstimatedDate() = "Estimated Arrival in $arrivalDate" // TODO string resources
    fun getPrice() = "€ $price"
}
