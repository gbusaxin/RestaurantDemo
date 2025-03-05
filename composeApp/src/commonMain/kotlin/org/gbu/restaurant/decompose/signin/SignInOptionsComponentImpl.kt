package org.gbu.restaurant.decompose.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.SignInOptionsViewModel

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