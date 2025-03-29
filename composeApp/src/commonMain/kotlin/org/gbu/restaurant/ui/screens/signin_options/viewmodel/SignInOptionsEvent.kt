package org.gbu.restaurant.ui.screens.signin_options.viewmodel

import org.gbu.restaurant.business.core.ViewEvent

sealed class SignInOptionsEvent: ViewEvent {
    data object SignInWithRestaurant: SignInOptionsEvent()
    data object SignInWithGoogle: SignInOptionsEvent()
    data object SignInWithApple: SignInOptionsEvent()
    data object SignInWithCreateAccount: SignInOptionsEvent()

    data object TermsAndConditions: SignInOptionsEvent()
    data object PrivacyAndPolicy: SignInOptionsEvent()
}