package org.gbu.restaurant.business.data.local.entity

data class Coupon(
    val title: String,
    val desc: String,
    val code: String,
    val offPercent: Int
) {
}