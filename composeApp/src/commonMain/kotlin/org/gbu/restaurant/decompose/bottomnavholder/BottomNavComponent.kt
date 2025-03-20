package org.gbu.restaurant.decompose.bottomnavholder

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.cart.CartComponent
import org.gbu.restaurant.decompose.menu.MenuComponent
import org.gbu.restaurant.decompose.root.RestaurantRootImpl

interface BottomNavComponent {

    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, BottomNavChild>>
    val configs: List<BottomNavComponentImpl.BottomNavConfig>

    fun onNewPageSelected(index: Int)

    fun onNavigationToMainChild(child: RestaurantRootImpl.MainNavigationConfig)

    sealed interface BottomNavChild {
        data class Menu(val component: MenuComponent) : BottomNavChild

//        data class Delivery(val component: DeliveryComponent) : BottomNavChild

        data class Cart(val component: CartComponent) : BottomNavChild

//        data class Profile(val component: ProfileComponent) : BottomNavChild

//        data class Other(val component: OtherComponent) : BottomNavChild
    }
}