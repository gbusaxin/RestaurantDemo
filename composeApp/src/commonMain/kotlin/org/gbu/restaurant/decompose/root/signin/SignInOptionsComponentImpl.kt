package org.gbu.restaurant.decompose.root.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.ui.screens.signin_options.viewmodel.SignInOptionsViewModel

class SignInOptionsComponentImpl(
    componentContext: ComponentContext,
    val onCreateAccount: () -> Unit,
    val onSignInToAccount: () -> Unit
) : SignInOptionsComponent, ComponentContext by componentContext {

    override val singInOptionsViewModel: SignInOptionsViewModel
        get() = instanceKeeper.getOrCreate { SignInOptionsViewModel() }

    override fun onCreateAccountClicked() {
        onCreateAccount()
    }

    override fun onSignInToAccountClicked() {
        onSignInToAccount()
    }
}