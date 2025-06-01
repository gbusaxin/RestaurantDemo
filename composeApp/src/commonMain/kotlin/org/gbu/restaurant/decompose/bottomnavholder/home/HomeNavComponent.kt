package org.gbu.restaurant.decompose.bottomnavholder.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.home.categories.CategoriesComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.detail.DetailComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.home.HomeComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.notifications.NotificationsComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.search.SearchComponent
import org.gbu.restaurant.decompose.bottomnavholder.home.settings.SettingsComponent

interface HomeNavComponent {

    val pages: Value<ChildStack<*, HomeNavChild>>

    fun onHomeChildNavigation(child: HomeNavComponentImpl.HomeNavConfig)

    sealed interface HomeNavChild {
        data class Home(val component: HomeComponent) : HomeNavChild
        data class Search(val component: SearchComponent) : HomeNavChild
        data class Detail(val component: DetailComponent) : HomeNavChild
        data class Categories(val component: CategoriesComponent) : HomeNavChild
        data class Settings(val component: SettingsComponent) : HomeNavChild
        data class Notifications(val component: NotificationsComponent) : HomeNavChild
    }
}