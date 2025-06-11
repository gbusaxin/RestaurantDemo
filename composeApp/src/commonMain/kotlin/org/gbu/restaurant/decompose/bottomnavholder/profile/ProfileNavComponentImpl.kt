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
import org.gbu.restaurant.decompose.bottomnavholder.cart.add_address.AddAddressComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.cart.add_address_info.AddAddressInfoComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.cart.address.AddressComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.settings.SettingsComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.profile.my_coupons.MyCouponsComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.profile.my_orders.MyOrdersComponentImpl
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
            ProfileNavConfig.Address -> ProfileNavComponent.ProfileNavChild.Address(
                component = buildAddressComponent(componentContext)
            )

            ProfileNavConfig.AddAddress -> ProfileNavComponent.ProfileNavChild.AddAddress(
                component = buildAddAddressComponent(componentContext)
            )

            ProfileNavConfig.AddAddressInfo -> ProfileNavComponent.ProfileNavChild.AddAddressInfo(
                component = buildAddAddressInfoComponent(componentContext)
            )

            ProfileNavConfig.Profile -> ProfileNavComponent.ProfileNavChild.Profile(
                component = buildProfileComponent(componentContext)
            )

            ProfileNavConfig.Payment -> ProfileNavComponent.ProfileNavChild.Payment(
                component = buildPaymentComponent(componentContext)
            )

            ProfileNavConfig.MyCoupons -> ProfileNavComponent.ProfileNavChild.MyCoupons(
                component = buildMyCouponsComponent(componentContext)
            )

            ProfileNavConfig.MyOrders -> ProfileNavComponent.ProfileNavChild.MyOrders(
                component = buildMyOrdersComponent(componentContext)
            )

            ProfileNavConfig.Settings -> ProfileNavComponent.ProfileNavChild.Settings(
                component = buildSettingsComponent(componentContext)
            )
        }
    }

    private fun buildAddressComponent(context: ComponentContext) = AddressComponentImpl(
        componentContext = context,
        onNavigateToAddAddress = { mainDispatcher.launch { navigation.push(ProfileNavConfig.AddAddress) } },
        onPopUp = { mainDispatcher.launch { navigation.pop() } }
    )

    private fun buildAddAddressComponent(context: ComponentContext) = AddAddressComponentImpl(
        componentContext = context,
        onAddAddressInformation = { mainDispatcher.launch { navigation.push(ProfileNavConfig.AddAddressInfo) } },
        onPopUp = { mainDispatcher.launch { navigation.pop() } }
    )

    private fun buildAddAddressInfoComponent(context: ComponentContext) =
        AddAddressInfoComponentImpl(
            componentContext = context,
            onPopUp = { mainDispatcher.launch { navigation.pop() } }
        )

    private fun buildMyOrdersComponent(context: ComponentContext) = MyOrdersComponentImpl(
        componentContext = context,
        onPopUp = { mainDispatcher.launch { navigation.pop() } }
    )

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
        onNavigateToAddress = {
            mainDispatcher.launch {
                navigation.push(
                    ProfileNavConfig.AddAddress
                )
            }
        },
        onNavigateToPaymentMethod = {
            mainDispatcher.launch {
                navigation.push(ProfileNavConfig.Payment)
            }
        },
        onNavigateToMyOrders = {
            mainDispatcher.launch {
                navigation.push(ProfileNavConfig.MyOrders)
            }
        },
        onNavigateToMyCoupons = {
            mainDispatcher.launch {
                navigation.push(ProfileNavConfig.MyCoupons)
            }
        },
        onNavigateToMyWallet = { mainDispatcher.launch { TODO() } },
        onNavigateToSettings = { mainDispatcher.launch { navigation.push(ProfileNavConfig.Settings) } }
    )

    private fun buildSettingsComponent(context: ComponentContext) = SettingsComponentImpl(
        componentContext = context,
        onPopUp = { mainDispatcher.launch { ProfileNavConfig.Settings } }
    )

    sealed interface ProfileNavConfig : Parcelable {
        @Parcelize
        data object Address : ProfileNavConfig

        @Parcelize
        data object AddAddress : ProfileNavConfig

        @Parcelize
        data object AddAddressInfo : ProfileNavConfig

        @Parcelize
        data object Profile : ProfileNavConfig

        @Parcelize
        data object Payment : ProfileNavConfig

        @Parcelize
        data object MyCoupons : ProfileNavConfig

        @Parcelize
        data object MyOrders : ProfileNavConfig

        @Parcelize
        data object Settings : ProfileNavConfig
    }
}