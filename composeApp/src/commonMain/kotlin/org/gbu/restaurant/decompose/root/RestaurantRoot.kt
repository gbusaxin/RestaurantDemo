package org.gbu.restaurant.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponent
import org.gbu.restaurant.decompose.contactsinfo.ContactInfoComponent
import org.gbu.restaurant.decompose.login.LoginComponent
import org.gbu.restaurant.decompose.menudetail.MenuDetailComponent
import org.gbu.restaurant.decompose.onboarding.OnBoardingComponent
import org.gbu.restaurant.decompose.phoneverification.PhoneVerificationComponent
import org.gbu.restaurant.decompose.signin.SignInOptionsComponent
import org.gbu.restaurant.decompose.splash.SplashComponent
import org.gbu.restaurant.viewmodels.RootViewModel
import org.koin.core.KoinApplication

interface RestaurantRoot {

    val backstack: Value<ChildStack<*, MainDestinationChild>>
    val koinApplication: KoinApplication
    val rootViewModel: RootViewModel

    sealed class MainDestinationChild {
        class Splash(val component: SplashComponent) : MainDestinationChild()

        class OnBoarding(val component: OnBoardingComponent) : MainDestinationChild()
        // TODO add all screens for navigation

        class SignInOptions(val component: SignInOptionsComponent) : MainDestinationChild()

        class Login(val component: LoginComponent) : MainDestinationChild()

        class ContactInfo(val component: ContactInfoComponent) : MainDestinationChild()

        class PhoneVerification(val component: PhoneVerificationComponent) : MainDestinationChild()

        class BottomNavHolder(val component: BottomNavComponent) : MainDestinationChild()

        class MenuDetail(val component: MenuDetailComponent): MainDestinationChild()
    }

}