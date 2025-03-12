package org.gbu.restaurant.viewmodels

import androidx.compose.ui.graphics.Color
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.gbu.restaurant.data.entity.Ingredient
import org.gbu.restaurant.data.entity.MenuItem
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.menu_icon

class MenuDetailsViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    fun findMenuItemById(menuItemId: Long): MenuItem {
        return MenuItem(
            1,
            "Quatro Formagi",
            "Pizza which contains 4 types of cheese. Fresh Buffalo Mozzarela, Blue cheese, Cheddar, and some poison for health. Good luck my Friend",
            Res.drawable.menu_icon,
            listOf(
                Ingredient("Cheddar"),
                Ingredient("Dough"),
                Ingredient("Mozzarella"),
                Ingredient("Blue cheese"),
                Ingredient("Olive oil"),
            ),
            Color.Yellow
        )
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}