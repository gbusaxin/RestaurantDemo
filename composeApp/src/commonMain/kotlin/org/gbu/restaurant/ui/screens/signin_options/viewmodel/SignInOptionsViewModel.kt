package org.gbu.restaurant.ui.screens.signin_options.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel

class SignInOptionsViewModel :
    BaseViewModel<SignInOptionsEvent, SignInOptionsState, SignInOptionsAction>() {

    override fun setInitialState(): SignInOptionsState = SignInOptionsState()

    override fun onTriggerEvent(event: SignInOptionsEvent) {
        when (event) {
            SignInOptionsEvent.PrivacyAndPolicy -> {
                privacyAndPolicy()
            }

            SignInOptionsEvent.SignInWithApple -> {
                onSignInWithApple()
            }

            SignInOptionsEvent.SignInWithCreateAccount -> {
                onCreateAnAccount()
            }

            SignInOptionsEvent.SignInWithGoogle -> {
                onSignInWithGoogle()
            }

            SignInOptionsEvent.SignInWithRestaurant -> {
                onSignInWithRestaurant()
            }

            SignInOptionsEvent.TermsAndConditions -> {
                termsAndConditions()
            }
        }
    }

    private fun onSignInWithApple() {
        setAction { SignInOptionsAction.Navigation.SignInApple }
    }

    private fun onSignInWithGoogle() {
        setAction { SignInOptionsAction.Navigation.SignInGoogle }
    }

    private fun onSignInWithRestaurant() {
        setAction { SignInOptionsAction.Navigation.SignInRestaurant }
    }

    private fun onCreateAnAccount() {
        setAction { SignInOptionsAction.Navigation.CreateAccount }
    }

    private fun privacyAndPolicy() {

    }

    private fun termsAndConditions() {

    }

}