package org.gbu.restaurant.decompose.menu

import org.gbu.restaurant.business.data.entity.MenuItem
import org.gbu.restaurant.viewmodels.MenuViewModel

interface MenuComponent {

    val menuViewModel: MenuViewModel

    fun onClickMenuDetail(menuItem: MenuItem)
}