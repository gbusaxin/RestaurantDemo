package org.gbu.restaurant.decompose.bottomnavholder.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.bottomnavholder.home.categories.CategoriesComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.detail.DetailComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.home.HomeComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.notifications.NotificationsComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.search.SearchComponentImpl
import org.gbu.restaurant.decompose.bottomnavholder.home.settings.SettingsComponentImpl

class HomeNavComponentImpl(
    componentContext: ComponentContext
) : HomeNavComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<HomeNavConfig>()
    private val _pages = this.childStack(
        source = navigation,
        initialConfiguration = HomeNavConfig.Home,
        childFactory = ::createChildPageFactory
    )
    override val pages: Value<ChildStack<*, HomeNavComponent.HomeNavChild>> = _pages

    override fun onHomeChildNavigation(child: HomeNavConfig) {
        mainDispatcher.launch {
            navigation.bringToFront(child)
        }
    }

    private val mainDispatcher = CoroutineScope(Dispatchers.Main)

    private fun createChildPageFactory(
        config: HomeNavConfig,
        componentContext: ComponentContext
    ): HomeNavComponent.HomeNavChild {
        return when (config) {
            is HomeNavConfig.Home -> HomeNavComponent.HomeNavChild.Home(
                component = buildHomeComponent(componentContext)
            )

            is HomeNavConfig.Search -> HomeNavComponent.HomeNavChild.Search(
                component = buildSearchComponent(componentContext)
            )

            is HomeNavConfig.Detail -> HomeNavComponent.HomeNavChild.Detail(
                component = buildDetailComponent(componentContext, config.id)
            )

            is HomeNavConfig.Categories -> HomeNavComponent.HomeNavChild.Categories(
                component = buildCategoriesComponent(componentContext)
            )

            is HomeNavConfig.Settings -> HomeNavComponent.HomeNavChild.Settings(
                component = buildSettingsComponent(componentContext)
            )

            is HomeNavConfig.Notifications -> HomeNavComponent.HomeNavChild.Notifications(
                component = buildNotificationsComponent(componentContext)
            )
        }
    }

    private fun buildNotificationsComponent(context: ComponentContext) = NotificationsComponentImpl(
        componentContext = context,
        onPopUp = {
            mainDispatcher.launch {
                navigation.pop()
            }
        }
    )

    private fun buildSettingsComponent(context: ComponentContext) = SettingsComponentImpl(
        componentContext = context,
        onPopUp = {
            mainDispatcher.launch { navigation.pop() }
        }
    )

    private fun buildCategoriesComponent(context: ComponentContext) = CategoriesComponentImpl(
        context = context,
        onPopUp = {
            mainDispatcher.launch { navigation.pop() }
        },
        onNavigateToSearch = {
            mainDispatcher.launch { navigation.push(HomeNavConfig.Categories) }
        }
    )

    private fun buildDetailComponent(context: ComponentContext, id: Long) = DetailComponentImpl(
        componentContext = context,
        onPopUp = {
            mainDispatcher.launch {
                navigation.pop()
            }
        },
        productId = id
    )

    private fun buildHomeComponent(context: ComponentContext) =
        HomeComponentImpl(
            context = context,
            onNavigationToDetail = {
                mainDispatcher.launch {
                    navigation.push(HomeNavConfig.Detail(it))
                }
            },
            onNavigateToNotifications = {
                mainDispatcher.launch {
                    navigation.push(HomeNavConfig.Notifications)
                }
            },
            onNavigateToSettings = {
                mainDispatcher.launch {
                    navigation.push(HomeNavConfig.Settings)
                }
            },
            onNavigateToCategories = {
                mainDispatcher.launch {
                    navigation.push(HomeNavConfig.Categories)
                }
            },
            onNavigateToSearch = { id, sort ->
                mainDispatcher.launch {
                    navigation.push(HomeNavConfig.Search(id, sort))
                }
            }
        )

    private fun buildSearchComponent(
        context: ComponentContext
    ) = SearchComponentImpl(
        context = context,
        onNavigateToDetailPage = {
            mainDispatcher.launch {
                navigation.push(HomeNavConfig.Detail(it))
            }
        },
        onPopUp = {
            mainDispatcher.launch {
                navigation.pop()
            }
        }
    )

    sealed interface HomeNavConfig : Parcelable {
        @Parcelize
        data object Home : HomeNavConfig

        @Parcelize
        data class Search(val id: Long? = null, val sort: Int? = null) : HomeNavConfig

        @Parcelize
        data class Detail(val id: Long) : HomeNavConfig

        @Parcelize
        data object Categories : HomeNavConfig

        @Parcelize
        data object Settings : HomeNavConfig

        @Parcelize
        data object Notifications : HomeNavConfig
    }
}
