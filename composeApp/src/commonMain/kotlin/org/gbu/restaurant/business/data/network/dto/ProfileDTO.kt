package org.gbu.restaurant.business.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gbu.restaurant.business.data.local.entity.Profile

@Serializable
data class ProfileDTO(
    @SerialName("name") val name: String?,
    @SerialName("age") val age: String?
) {
}

fun ProfileDTO.toProfile(): Profile = Profile(
    name = name ?: "",
    age = age ?: ""
)