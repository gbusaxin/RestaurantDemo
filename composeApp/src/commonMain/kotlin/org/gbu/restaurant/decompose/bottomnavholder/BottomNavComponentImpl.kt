package org.gbu.restaurant.decompose.bottomnavholder

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.bottomnavholder.cart.CartNavComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.HomeNavComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.profile.ProfileNavComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.wish_list.WishListNavComponentImpl
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.cart
import restaurantdemo.composeapp.generated.resources.cart_icon
import restaurantdemo.composeapp.generated.resources.earth_icon
import restaurantdemo.composeapp.generated.resources.home
import restaurantdemo.composeapp.generated.resources.menu_icon
import restaurantdemo.composeapp.generated.resources.profile
import restaurantdemo.composeapp.generated.resources.user_profile_icon
import restaurantdemo.composeapp.generated.resources.wish_list

@OptIn(ExperimentalDecomposeApi::class)
class BottomNavComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateToMainChildRequested: (child: RestaurantRootImpl.MainNavigationConfig) -> Unit
) : BottomNavComponent, ComponentContext by componentContext {

    override val configs = listOf(
        BottomNavConfig.Home,
        BottomNavConfig.Cart,
    )
    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private val navigation = PagesNavigation<BottomNavConfig>()

    private val _pages = this.childPages(
        source = navigation,
        initialPages = {
            Pages(
                items = configs,
                selectedIndex = 0
            )
        },
        childFactory = ::createChildPageFactory
    )

    override val pages: Value<ChildPages<*, BottomNavComponent.BottomNavChild>> = _pages

    override fun onNavigationToMainChild(child: RestaurantRootImpl.MainNavigationConfig) {
        onNavigateToMainChildRequested(child)
    }

    override fun onNewPageSelected(index: Int) {
        mainDispatcher.launch {
            navigation.select(index)
        }
    }

    fun createChildPageFactory(
        config: BottomNavConfig,
        componentContext: ComponentContext
    ): BottomNavComponent.BottomNavChild {
        return when (config) {
            is BottomNavConfig.Home -> BottomNavComponent.BottomNavChild.Home(
                component = buildHomeNavComponent(context = componentContext)
            )

            is BottomNavConfig.Cart -> BottomNavComponent.BottomNavChild.Cart(
                component = buildCartNavComponent(componentContext)
            )

            is BottomNavConfig.WishList -> BottomNavComponent.BottomNavChild.WishList(
                component = buildWishListComponent(componentContext)
            )

            is BottomNavConfig.Profile -> BottomNavComponent.BottomNavChild.Profile(
                component = buildProfileNavComponent(componentContext)
            )
        }
    }

    private fun buildProfileNavComponent(context: ComponentContext) = ProfileNavComponentImpl(
        componentContext = context
    )

    private fun buildWishListComponent(context: ComponentContext) = WishListNavComponentImpl(
        componentContext = context,
        onNavigateHomeChild = { homeNavConfig ->
            val homeIndex = configs.indexOf(BottomNavConfig.Home)
            onNewPageSelected(homeIndex)
            ((pages.value.items[homeIndex]).instance as? BottomNavComponent.BottomNavChild.Home)
                ?.component?.onHomeChildNavigation(homeNavConfig)
        }
    )

    private fun buildCartNavComponent(context: ComponentContext) = CartNavComponentImpl(
        componentContext = context,
        onNavigateHomeChild = { homeNavConfig ->
            val homeIndex = configs.indexOf(BottomNavConfig.Home)
            onNewPageSelected(homeIndex)
            ((pages.value.items[homeIndex]).instance as? BottomNavComponent.BottomNavChild.Home)
                ?.component?.onHomeChildNavigation(homeNavConfig)
        }
    )

    private fun buildHomeNavComponent(context: ComponentContext) = HomeNavComponentImpl(
        componentContext = context
    )

    sealed class BottomNavConfig(
        val title: StringResource,
        val icon: DrawableResource
    ) : Parcelable {
        @Parcelize
        data object Home : BottomNavConfig(
            title = Res.string.home,
            icon = Res.drawable.menu_icon
        )

        @Parcelize
        data object Cart :
            BottomNavConfig(
                title = Res.string.cart,
                icon = Res.drawable.cart_icon
            )

        @Parcelize
        data object WishList : BottomNavConfig(
            title = Res.string.wish_list,
            icon = Res.drawable.earth_icon
        )

        @Parcelize
        data object Profile : BottomNavConfig(
            title = Res.string.profile,
            icon = Res.drawable.user_profile_icon
        )
    }
}