package org.gbu.restaurant.business.data.local.entity

data class User(
    val id: String,
    val fName: String,
    val lName: String,
    val phone: String? = null,
    val token: String? = null
)