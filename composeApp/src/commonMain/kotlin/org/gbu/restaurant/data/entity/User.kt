package org.gbu.restaurant.data.entity

data class User(
    val id: String,
    val username: String,
    val fName: String,
    val lName: String,
    val phone: String? = null,
    val token: String? = null
)