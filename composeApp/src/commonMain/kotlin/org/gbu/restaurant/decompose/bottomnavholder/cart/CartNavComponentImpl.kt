package org.gbu.restaurant.decompose.bottomnavholder.cart

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
import org.gbu.restaurant.decompose.bottomnavholder.cart.cart.CartComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.cart.checkout.CheckoutComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.HomeNavComponentImpl

class CartNavComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateHomeChild: (HomeNavComponentImpl.HomeNavConfig) -> Unit
) : CartNavComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<CartNavConfig>()

    private val _pages = this.childStack(
        source = navigation,
        initialConfiguration = CartNavConfig.Cart,
        childFactory = ::createChildPageFactory
    )
    override val pages: Value<ChildStack<*, CartNavComponent.CartNavChild>> = _pages

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private fun createChildPageFactory(
        config: CartNavConfig,
        componentContext: ComponentContext
    ): CartNavComponent.CartNavChild {
        return when (config) {
            is CartNavConfig.Cart -> CartNavComponent.CartNavChild.Cart(
                component = buildCartComponent(componentContext)
            )

            is CartNavConfig.Checkout -> CartNavComponent.CartNavChild.Checkout(
                component = buildCheckoutComponent(componentContext)
            )

            is CartNavConfig.Address -> CartNavComponent.CartNavChild.Address(
                component = buildAddressComponent(componentContext)
            )

            is CartNavConfig.AddAddress -> CartNavComponent.CartNavChild.AddAddress(
                component = buildAddAddressComponent(componentContext)
            )

            is CartNavConfig.AddAddressInfo -> CartNavComponent.CartNavChild.AddAddressInfo(
                component = buildAddAddressInfoComponent(componentContext)
            )
        }
    }

    private fun buildAddAddressInfoComponent(context: ComponentContext) =
        AddAddressInfoComponentImpl(
            componentContext = context,
            onPopUp = { mainDispatcher.launch { navigation.pop() } }
        )

    private fun buildAddAddressComponent(context: ComponentContext) = AddAddressComponentImpl(
        componentContext = context,
        onAddAddressInformation = { mainDispatcher.launch { navigation.push(CartNavConfig.AddAddressInfo) } },
        onPopUp = { mainDispatcher.launch { navigation.pop() } }
    )

    private fun buildAddressComponent(context: ComponentContext) = AddressComponentImpl(
        componentContext = context,
        onNavigateToAddAddress = { mainDispatcher.launch { navigation.push(CartNavConfig.AddAddress) } },
        onPopUp = { mainDispatcher.launch { navigation.pop() } }
    )

    private fun buildCheckoutComponent(context: ComponentContext) = CheckoutComponentImpl(
        componentContext = context,
        onPopUp = { mainDispatcher.launch { navigation.pop() } },
        onNavigateToAddress = { mainDispatcher.launch { navigation.push(CartNavConfig.Address) } }
    )

    private fun buildCartComponent(context: ComponentContext) = CartComponentImpl(
        componentContext = context,
        clickToDetail = {
            mainDispatcher.launch {
                onNavigateHomeChild(
                    HomeNavComponentImpl.HomeNavConfig.Detail(
                        it
                    )
                )
            }
        },
        clickToCheckout = {
            mainDispatcher.launch {
                navigation.push(CartNavConfig.Checkout)
            }
        }
    )

    sealed interface CartNavConfig : Parcelable {
        @Parcelize
        data object Cart : CartNavConfig

        @Parcelize
        data object Checkout : CartNavConfig

        @Parcelize
        data object Address : CartNavConfig

        @Parcelize
        data object AddAddress : CartNavConfig

        @Parcelize
        data object AddAddressInfo : CartNavConfig
    }
}