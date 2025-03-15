package org.gbu.restaurant.decompose.login

import org.gbu.restaurant.business.data.entity.User
import org.gbu.restaurant.viewmodels.LoginViewModel

interface LoginComponent {
    val loginViewModel: LoginViewModel

    fun onAuthenticationSuccess(user: User, rememberMe: Boolean)
}