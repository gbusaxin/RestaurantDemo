package org.gbu.restaurant.decompose.root.signin

import org.gbu.restaurant.ui.screens.signin_options.viewmodel.SignInOptionsViewModel

interface SignInOptionsComponent {

    val singInOptionsViewModel: SignInOptionsViewModel

    fun onCreateAccountClicked()
    fun onSignInToAccountClicked()

}