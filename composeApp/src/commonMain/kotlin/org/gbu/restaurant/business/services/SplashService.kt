package org.gbu.restaurant.business.services

import org.gbu.restaurant.business.data.network.common.MainGenericResponse

interface SplashService {
    companion object{
        const val REGISTER = "register"
        const val LOGIN = "login"
    }

    suspend fun login(email: String, password: String): MainGenericResponse<String?>
    suspend fun register(name: String, email: String, password: String): MainGenericResponse<String?>
}