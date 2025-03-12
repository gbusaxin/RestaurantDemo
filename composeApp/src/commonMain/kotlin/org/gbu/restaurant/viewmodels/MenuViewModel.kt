package org.gbu.restaurant.viewmodels

import androidx.compose.ui.graphics.Color
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.gbu.restaurant.data.entity.Ingredient
import org.gbu.restaurant.data.entity.MenuItem
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.cart_icon
import restaurantdemo.composeapp.generated.resources.food_icon
import restaurantdemo.composeapp.generated.resources.google_logo
import restaurantdemo.composeapp.generated.resources.menu_icon
import restaurantdemo.composeapp.generated.resources.user_profile_icon

class MenuViewModel : InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    private val _items = MutableStateFlow<List<MenuItem>>(emptyList())
    val items: StateFlow<List<MenuItem>> = _items

    init {
        loadItems()
    }

    private fun loadItems(){
        viewModelScope.launch {
            val loadedItems = getAllMenuItems()
            _items.value = loadedItems
        }
    }

    private fun getAllMenuItems():List<MenuItem>{
        return listOf(
            MenuItem(
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
            ),
            MenuItem(
                2,
                "Pepperoni",
                "Pizza with pepperoni mmm that smels good ualala bravo maestro!",
                Res.drawable.google_logo,
                listOf(
                    Ingredient("Cheddar"),
                    Ingredient("Dough"),
                    Ingredient("Mozzarella"),
                    Ingredient("Blue cheese"),
                    Ingredient("Olive oil"),
                ),
                Color.LightGray
            ),
            MenuItem(
                3,
                "Ala mafiozi",
                "Pizza for mafiozi from italy, Mario its mi Mario Pizza Pasta Tiramisu he he he he he ",
                Res.drawable.user_profile_icon,
                listOf(
                    Ingredient("Cheddar"),
                    Ingredient("Dough"),
                    Ingredient("Mozzarella"),
                    Ingredient("Blue cheese"),
                    Ingredient("Olive oil"),
                ),
                Color.Red
            ),
            MenuItem(
                4,
                "Hawai",
                "Discusting pizza with pineapple its awful do not reccomend jnvoeiveirb e viebriv erhbfiverhb ehfbeirb",
                Res.drawable.food_icon,
                listOf(
                    Ingredient("Cheddar"),
                    Ingredient("Dough"),
                    Ingredient("Mozzarella"),
                    Ingredient("Blue cheese"),
                    Ingredient("Olive oil"),
                ),
                Color.Green
            ),
            MenuItem(
                5,
                "Alalala",
                "The worst pizza in meno, Nobody lived after eating it. weird pizza for looser. if you are a looser then it is your choise",
                Res.drawable.cart_icon,
                listOf(
                    Ingredient("Cheddar"),
                    Ingredient("Dough"),
                    Ingredient("Mozzarella"),
                    Ingredient("Blue cheese"),
                    Ingredient("Olive oil"),
                ),
                Color.Yellow
            ),
            MenuItem(
                6,
                "Pizza ala sheet",
                "Pizza which contains 4 types of cheese. Fresh Buffalo Mozzarela, Blue cheese, Cheddar, and some poison for health. Good luck my Friend",
                Res.drawable.menu_icon,
                listOf(
                    Ingredient("Cheddar"),
                    Ingredient("Dough"),
                    Ingredient("Mozzarella"),
                    Ingredient("Blue cheese"),
                    Ingredient("Olive oil"),
                ),
                Color.Blue
            ),
        )
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}