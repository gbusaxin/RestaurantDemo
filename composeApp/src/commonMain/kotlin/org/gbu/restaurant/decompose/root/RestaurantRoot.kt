package org.gbu.restaurant.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponent
import org.gbu.restaurant.decompose.root.contactsinfo.ContactInfoComponent
import org.gbu.restaurant.decompose.root.login.LoginComponent
import org.gbu.restaurant.decompose.root.onboarding.OnBoardingComponent
import org.gbu.restaurant.decompose.root.phoneverification.PhoneVerificationComponent
import org.gbu.restaurant.decompose.root.signin.SignInOptionsComponent
import org.gbu.restaurant.decompose.root.splash.SplashComponent
import org.gbu.restaurant.viewmodels.RootViewModel
import org.koin.core.KoinApplication

interface RestaurantRoot {

    val backstack: Value<ChildStack<*, MainDestinationChild>>
    val koinApplication: KoinApplication
    val rootViewModel: RootViewModel

    fun handleInvalidToken()

    sealed class MainDestinationChild {
        class Splash(val component: SplashComponent) : MainDestinationChild()
        class OnBoarding(val component: OnBoardingComponent) : MainDestinationChild()
        class SignInOptions(val component: SignInOptionsComponent) : MainDestinationChild()
        class Login(val component: LoginComponent) : MainDestinationChild()
        class ContactInfo(val component: ContactInfoComponent) : MainDestinationChild()
        class PhoneVerification(val component: PhoneVerificationComponent) : MainDestinationChild()
        class BottomNavHolder(val component: BottomNavComponent) : MainDestinationChild()
    }

}