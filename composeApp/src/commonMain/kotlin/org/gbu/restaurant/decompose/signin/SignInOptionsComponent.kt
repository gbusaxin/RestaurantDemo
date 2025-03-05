package org.gbu.restaurant.decompose.signin

import org.gbu.restaurant.viewmodels.SignInOptionsViewModel

interface SignInOptionsComponent {

    val singInOptionsViewModel: SignInOptionsViewModel

    fun onCreateAccountClicked()
    fun onSignInToAccountClicked()

}