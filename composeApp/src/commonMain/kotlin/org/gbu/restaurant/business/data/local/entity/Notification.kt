package org.gbu.restaurant.business.data.local.entity

data class Notification(
    val id: Long,
    val title: String,
    val description: String,
    val createAt: String,
    val isRead: Boolean,
)