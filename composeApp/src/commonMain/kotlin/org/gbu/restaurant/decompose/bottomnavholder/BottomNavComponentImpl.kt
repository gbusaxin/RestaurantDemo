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
import org.gbu.restaurant.decompose.menu.MenuComponentImpl
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.cart
import restaurantdemo.composeapp.generated.resources.cart_icon
import restaurantdemo.composeapp.generated.resources.delivery
import restaurantdemo.composeapp.generated.resources.delivery_icon
import restaurantdemo.composeapp.generated.resources.menu
import restaurantdemo.composeapp.generated.resources.menu_icon
import restaurantdemo.composeapp.generated.resources.other
import restaurantdemo.composeapp.generated.resources.other_icon
import restaurantdemo.composeapp.generated.resources.profile
import restaurantdemo.composeapp.generated.resources.user_profile_icon

@OptIn(ExperimentalDecomposeApi::class)
class BottomNavComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateToMainChildRequested: (child: RestaurantRootImpl.MainNavigationConfig) -> Unit
) : BottomNavComponent, ComponentContext by componentContext {

    override val configs = listOf(
        BottomNavConfig.Menu
//        BottomNavConfig.Cart,
//        BottomNavConfig.Delivery,
//        BottomNavConfig.Profile,
//        BottomNavConfig.Other, TODO()
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

    override val pages: Value<ChildPages<*, BottomNavComponent.BottomNavChild>>
        get() = _pages

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
            is BottomNavConfig.Menu -> BottomNavComponent.BottomNavChild.Menu(
                component = buildMenuComponent(componentContext)
            )
//            is BottomNavConfig.Delivery ->
//            is BottomNavConfig.Cart ->
//            is BottomNavConfig.Profile ->
//            is BottomNavConfig.Other ->
            else -> TODO()
        }
    }

    private fun buildMenuComponent(context: ComponentContext) = MenuComponentImpl(
        componentContext = context,
        onMenuDetail = {
            onNavigationToMainChild(RestaurantRootImpl.MainNavigationConfig.MenuDetail(it.id)) // TODO test below
        }
    )

    sealed class BottomNavConfig(
        val title: StringResource,
        val icon: DrawableResource
    ) : Parcelable {
        @Parcelize
        data object Menu :
            BottomNavConfig(
                title = Res.string.menu,
                icon = Res.drawable.menu_icon
            )

        @Parcelize
        data object Delivery :
            BottomNavConfig(
                title = Res.string.delivery,
                icon = Res.drawable.delivery_icon
            )

        @Parcelize
        data object Cart :
            BottomNavConfig(
                title = Res.string.cart,
                icon = Res.drawable.cart_icon
            )

        @Parcelize
        data object Profile :
            BottomNavConfig(
                title = Res.string.profile,
                icon = Res.drawable.user_profile_icon
            )

        @Parcelize
        data object Other :
            BottomNavConfig(
                title = Res.string.other,
                icon = Res.drawable.other_icon
            )
    }
}