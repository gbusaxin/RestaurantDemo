package org.gbu.restaurant.decompose.bottomnavholder.wish_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.bottomnavholder.home.HomeNavComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.wish_list.wish_list.WishListComponentImpl

class WishListNavComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateHomeChild: (HomeNavComponentImpl.HomeNavConfig) -> Unit
) : WishListNavComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<WishListNavConfig>()

    private val _pages = this.childStack(
        source = navigation,
        initialConfiguration = WishListNavConfig.WishList,
        childFactory = this::createChildPageFactory
    )

    override val pages: Value<ChildStack<*, WishListNavComponent.WishListNavChild>> = _pages

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private fun createChildPageFactory(
        config: WishListNavConfig,
        componentContext: ComponentContext
    ): WishListNavComponent.WishListNavChild {
        return when (config) {
            WishListNavConfig.WishList -> WishListNavComponent.WishListNavChild.WishList(
                component = buildWishListComponent(componentContext)
            )
        }
    }

    private fun buildWishListComponent(context: ComponentContext) = WishListComponentImpl(
        componentContext = context,
        onNavigateToDetail = {
            mainDispatcher.launch {
                onNavigateHomeChild(
                    HomeNavComponentImpl.HomeNavConfig.Detail(
                        it
                    )
                )
            }
        }
    )

    sealed interface WishListNavConfig : Parcelable {
        @Parcelize
        data object WishList : WishListNavConfig
    }
}