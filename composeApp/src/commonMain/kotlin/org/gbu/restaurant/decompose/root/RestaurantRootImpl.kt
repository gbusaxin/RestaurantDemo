package org.gbu.restaurant.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.bottomnavholder.BottomNavComponentImpl
import org.gbu.restaurant.decompose.contactsinfo.ContactInfoComponentImpl
import org.gbu.restaurant.decompose.login.LoginComponentImpl
import org.gbu.restaurant.decompose.menudetail.MenuDetailComponentImpl
import org.gbu.restaurant.decompose.onboarding.OnBoardingComponentImpl
import org.gbu.restaurant.decompose.phoneverification.PhoneVerificationComponentImpl
import org.gbu.restaurant.decompose.signin.SignInOptionsComponentImpl
import org.gbu.restaurant.decompose.splash.SplashComponentImpl
import org.gbu.restaurant.viewmodels.RootViewModel
import org.koin.core.KoinApplication

class RestaurantRootImpl(
    val componentContext: ComponentContext,
    override val koinApplication: KoinApplication
) : RestaurantRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainNavigationConfig>()
    private val mainDispatcher = CoroutineScope(Dispatchers.Main)
    override val rootViewModel: RootViewModel
        get() = instanceKeeper.getOrCreate { RootViewModel() }
    private val _backstack = this.childStack(
        source = navigation,
        initialConfiguration = MainNavigationConfig.Splash, // Splash is first screen by default
        handleBackButton = true
    ) { config, context ->
        createChildFactory(config, context)
    }
    override val backstack = _backstack

    private fun createChildFactory(
        config: MainNavigationConfig,
        componentContext: ComponentContext,
    ): RestaurantRoot.MainDestinationChild {
        return when (config) {
            is MainNavigationConfig.Splash -> RestaurantRoot.MainDestinationChild.Splash(
                component = buildSplashComponent(
                    context = componentContext
                )
            )

            is MainNavigationConfig.OnBoarding -> RestaurantRoot.MainDestinationChild.OnBoarding(
                component = createOnBoardingComponent(
                    context = componentContext
                )
            )

            is MainNavigationConfig.SignInOptions -> RestaurantRoot.MainDestinationChild.SignInOptions(
                component = buildSignInOptionsComponent(context = componentContext)
            )

            is MainNavigationConfig.Login -> RestaurantRoot.MainDestinationChild.Login(
                component = buildLoginComponent(componentContext)
            )

            is MainNavigationConfig.ContactPage -> RestaurantRoot.MainDestinationChild.ContactInfo(
                component = buildContactInfoComponent(context = componentContext)
            )

            is MainNavigationConfig.PhoneVerification -> RestaurantRoot.MainDestinationChild.PhoneVerification(
                component = buildPhoneVerification(componentContext)
            )

            is MainNavigationConfig.BottomNavHolder -> RestaurantRoot.MainDestinationChild.BottomNavHolder(
                component = buildBottomNavComponent(componentContext)
            )

            is MainNavigationConfig.MenuDetail -> RestaurantRoot.MainDestinationChild.MenuDetail(
                component = buildMenuDetailComponent(componentContext, config.menuItemId)
            )

            else -> TODO()
        }
    }


    private fun buildSplashComponent(
        context: ComponentContext
    ) = SplashComponentImpl(
        componentContext = context,
        onSplashTimeFinished = { isOnboardedBefore ->
            mainDispatcher.launch {
                if (isOnboardedBefore) {
                    navigation.replaceCurrent(configuration = MainNavigationConfig.SignInOptions)
                } else {
                    navigation.replaceCurrent(configuration = MainNavigationConfig.OnBoarding)
                }
            }
        }
    )

    private fun createOnBoardingComponent(
        context: ComponentContext
    ) = OnBoardingComponentImpl(componentContext = context, onOnBoardingFinished = {
        mainDispatcher.launch {
            navigation.replaceCurrent(MainNavigationConfig.SignInOptions)
        }
    })

    private fun buildSignInOptionsComponent(
        context: ComponentContext
    ) = SignInOptionsComponentImpl(
        componentContext = context,
        onCreateAccount = {
            mainDispatcher.launch {
                navigation.push(MainNavigationConfig.ContactPage)
            }
        },
        onSignInToAccount = {
            mainDispatcher.launch {
                navigation.push(MainNavigationConfig.Login)
            }
        }
    )

    private fun buildLoginComponent(
        context: ComponentContext
    ) = LoginComponentImpl(
        componentContext = context,
        onAuthenticated = { user, rememberMe ->
            mainDispatcher.launch {
                rootViewModel.updateLoggedUser(user)
                navigation.replaceAll(MainNavigationConfig.BottomNavHolder)
            }
        }
    )

    private fun buildContactInfoComponent(
        context: ComponentContext
    ) = ContactInfoComponentImpl(
        componentContext = context,
        onOtpSentToUser = {
            mainDispatcher.launch { navigation.push(MainNavigationConfig.PhoneVerification) }
        }
    )

    private fun buildPhoneVerification(
        context: ComponentContext
    ) = PhoneVerificationComponentImpl(
        componentContext = context,
        onVerifiedSuccessfully = {
//            navigation.push(MainNavigationConfig.CreateAuthentication) // TODO
        }
    )

    private fun buildBottomNavComponent(
        context: ComponentContext
    ) = BottomNavComponentImpl(
        componentContext = context,
        onNavigateToMainChildRequested = { config ->
            mainDispatcher.launch {
                navigation.push(config)
            }
        }
    )

    private fun buildMenuDetailComponent(
        context: ComponentContext,
        menuItemId: Long
    ) = MenuDetailComponentImpl(
        componentContext = context,
        onBackClicked = {
            mainDispatcher.launch {
                navigation.pop()
            }
        },
        menuItemId = menuItemId
    )

    sealed class MainNavigationConfig : Parcelable {
        @Parcelize
        data object Splash : MainNavigationConfig()

        @Parcelize
        data object OnBoarding : MainNavigationConfig()

        @Parcelize
        data object SignInOptions : MainNavigationConfig()

        @Parcelize
        data object Login : MainNavigationConfig()

        @Parcelize
        data object ContactPage : MainNavigationConfig()

        @Parcelize
        data object PhoneVerification : MainNavigationConfig()

        @Parcelize
        data object BottomNavHolder : MainNavigationConfig()

        @Parcelize
        data class MenuDetail(val menuItemId: Long) : MainNavigationConfig()
    }

}