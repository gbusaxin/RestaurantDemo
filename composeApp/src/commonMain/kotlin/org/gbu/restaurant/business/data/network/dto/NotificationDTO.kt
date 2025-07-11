package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Notification

@Serializable
data class NotificationDTO(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("is_read") val isRead: Int?,
)

fun NotificationDTO.toNotification() = Notification(
    id = id ?: 0L,
    title = title ?: "",
    description = description ?: "",
    isRead = isRead == 0,
    createAt = "1h"
)