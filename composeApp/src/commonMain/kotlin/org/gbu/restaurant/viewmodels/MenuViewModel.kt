package org.gbu.restaurant.viewmodels

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gbu.restaurant.business.data.entity.MenuItem

class MenuViewModel : InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    private val _items = MutableStateFlow<List<MenuItem>>(emptyList())
    val items: StateFlow<List<MenuItem>> = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            val loadedItems = getAllMenuItems()
            _items.value = loadedItems
        }
    }

    private fun getAllMenuItems(): List<MenuItem> {
        return MenuItem.fakeList()
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}