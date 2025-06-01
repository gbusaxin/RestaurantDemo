package org.gbu.restaurant.decompose.bottomnavholder.cart

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.cart.add_address.AddAddressComponent
import org.gbu.restaurant.decompose.bottomnavholder.cart.add_address_info.AddAddressInfoComponent
import org.gbu.restaurant.decompose.bottomnavholder.cart.address.AddressComponent
import org.gbu.restaurant.decompose.bottomnavholder.cart.cart.CartComponent
import org.gbu.restaurant.decompose.bottomnavholder.cart.checkout.CheckoutComponent

interface CartNavComponent {

    val pages: Value<ChildStack<*, CartNavChild>>

    sealed interface CartNavChild {
        data class Cart(val component: CartComponent) : CartNavChild
        data class Checkout(val component: CheckoutComponent) : CartNavChild
        data class Address(val component: AddressComponent) : CartNavChild
        data class AddAddress(val component: AddAddressComponent) : CartNavChild
        data class AddAddressInfo(val component: AddAddressInfoComponent) : CartNavChild
    }
}