package org.gbu.restaurant.viewmodels

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.gbu.restaurant.data.entity.MenuItem

class MenuDetailsViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    fun findMenuItemById(menuItemId: Long): MenuItem {
        return MenuItem.fakeList().find { it.id == menuItemId } ?: throw Exception("Not found")
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}