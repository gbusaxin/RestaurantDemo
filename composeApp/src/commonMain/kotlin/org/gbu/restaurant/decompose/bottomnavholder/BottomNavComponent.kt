package org.gbu.restaurant.decompose.bottomnavholder

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.cart.CartNavComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.HomeNavComponent
import org.gbu.restaurant.decompose.bottomnavholder.profile.ProfileNavComponent
import org.gbu.restaurant.decompose.bottomnavholder.wish_list.WishListNavComponent
import org.gbu.restaurant.decompose.root.RestaurantRootImpl

interface BottomNavComponent {

    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, BottomNavChild>>
    val configs: List<BottomNavComponentImpl.BottomNavConfig>

    fun onNewPageSelected(index: Int)

    fun onNavigationToMainChild(child: RestaurantRootImpl.MainNavigationConfig)

    sealed interface BottomNavChild {
        data class Home(val component: HomeNavComponent) : BottomNavChild
        data class Cart(val component: CartNavComponent) : BottomNavChild
        data class WishList(val component: WishListNavComponent) : BottomNavChild
        data class Profile(val component: ProfileNavComponent): BottomNavChild
    }
}