package org.gbu.restaurant.decompose.menu

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.business.data.entity.MenuItem
import org.gbu.restaurant.viewmodels.MenuViewModel

class MenuComponentImpl(
    private val componentContext: ComponentContext,
    private val onMenuDetail: (MenuItem) -> Unit
): MenuComponent, ComponentContext by componentContext {
    override val menuViewModel: MenuViewModel
        get() = instanceKeeper.getOrCreate { MenuViewModel() }

    override fun onClickMenuDetail(menuItem: MenuItem) {
        onMenuDetail(menuItem)
    }
}