package org.gbu.restaurant.decompose.bottomnavholder.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.bottomnavholder.profile.my_coupons.MyCouponsComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.profile.payment.PaymentComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.profile.profile.ProfileComponentImpl

class ProfileNavComponentImpl(
    componentContext: ComponentContext
) : ProfileNavComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<ProfileNavConfig>()

    private val _pages = this.childStack(
        source = navigation,
        initialConfiguration = ProfileNavConfig.Profile,
        childFactory = ::createChildPageFactory
    )

    override val pages: Value<ChildStack<*, ProfileNavComponent.ProfileNavChild>> = _pages

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private fun createChildPageFactory(
        config: ProfileNavConfig,
        componentContext: ComponentContext
    ): ProfileNavComponent.ProfileNavChild {
        return when (config) {
            ProfileNavConfig.Profile -> ProfileNavComponent.ProfileNavChild.Profile(
                component = buildProfileComponent(componentContext)
            )

            ProfileNavConfig.Payment -> ProfileNavComponent.ProfileNavChild.Payment(
                component = buildPaymentComponent(componentContext)
            )

            ProfileNavConfig.MyCoupons -> ProfileNavComponent.ProfileNavChild.MyCoupons(
                component = buildMyCouponsComponent(componentContext)
            )
        }
    }

    private fun buildMyCouponsComponent(context: ComponentContext) = MyCouponsComponentImpl(
        componentContext = context,
        onPopUp = {
            mainDispatcher.launch {
                navigation.pop()
            }
        }
    )

    private fun buildPaymentComponent(context: ComponentContext) = PaymentComponentImpl(
        componentContext = context,
        onPopUp = {
            mainDispatcher.launch {
                navigation.pop()
            }
        }
    )

    private fun buildProfileComponent(context: ComponentContext) = ProfileComponentImpl(
        componentContext = context,
        onNavigateToAddress = { mainDispatcher.launch { TODO() } },
        onNavigateToEditProfile = { mainDispatcher.launch { TODO() } },
        onNavigateToPaymentMethod = {
            mainDispatcher.launch {
                navigation.push(ProfileNavConfig.Payment)
            }
        },
        onNavigateToMyOrders = { mainDispatcher.launch { TODO() } },
        onNavigateToMyCoupons = {
            mainDispatcher.launch {
                navigation.push(ProfileNavConfig.MyCoupons)
            }
        },
        onNavigateToMyWallet = { mainDispatcher.launch { TODO() } },
        onNavigateToSettings = { mainDispatcher.launch { TODO() } }
    )

    sealed interface ProfileNavConfig : Parcelable {
        @Parcelize
        data object Profile : ProfileNavConfig

        @Parcelize
        data object Payment : ProfileNavConfig

        @Parcelize
        data object MyCoupons : ProfileNavConfig
    }
}