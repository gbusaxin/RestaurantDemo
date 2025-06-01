package org.gbu.restaurant.decompose.root.login

import org.gbu.restaurant.business.data.local.entity.User
import org.gbu.restaurant.viewmodels.LoginViewModel

interface LoginComponent {
    val loginViewModel: LoginViewModel

    fun onAuthenticationSuccess(user: User, rememberMe: Boolean)
}