package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Address
import org.gbu.restaurant.business.data.local.entity.Order
import org.gbu.restaurant.ui.screens.checkout.viewmodel.shippingType_global

@Serializable
data class OrderDTO(
    @SerialName("code") val code: String?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("shipping_type") val shippingType: Int?,
    @SerialName("status") val status: Int?,
    @SerialName("address") val address: AddressDTO?,
    @SerialName("products") val products: List<ProductDTO>?
)
fun OrderDTO.toOrder() = Order(
    code = code ?: "",
    createdAt = createdAt ?: "",
    shippingType = shippingType_global[shippingType?:0] ,
    status = status ?: 0,
    address = address?.toAddress() ?: Address(),
    products = products?.map { it.toProduct() } ?: listOf(),
)