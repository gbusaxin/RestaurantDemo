package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Category

@Serializable
data class CategoryDTO(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("parent") val parent: Int?,
    @SerialName("icon") val icon: String?
)

fun CategoryDTO.toCategory(): Category = Category(
    id = id ?: 0L,
    name = name ?: "",
    parent = parent ?: 0,
    icon = icon ?: ""
)