package org.gbu.restaurant.business.data.entity

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources._01_lemon_cheesecake
import restaurantdemo.composeapp.generated.resources._01_lemon_cheesecake_bg
import restaurantdemo.composeapp.generated.resources._02_chocolate_cake_1
import restaurantdemo.composeapp.generated.resources._03_chocolate_donuts
import restaurantdemo.composeapp.generated.resources._04_fluffy_cake

data class MenuItem(
    val id: Long,
    val name: String,
    val shortDesc: String,
    val imageLink: DrawableResource,
    val ingredients: List<Ingredient>,
    val bgColor: Color,
    val bgImage: DrawableResource? = Res.drawable._01_lemon_cheesecake_bg,
    val price: String
) {
    companion object {
        fun fakeList(): List<MenuItem> {
            return listOf(
                MenuItem(
                    1,
                    "Quatro Formagi",
                    "Pizza which contains 4 types of cheese. Fresh Buffalo Mozzarela, Blue cheese, Cheddar, and some poison for health. Good luck my Friend. It is very tasty, Contains a lot of milk, Hard to digest. More comfortable to not eat. Bye bye:>",
                    Res.drawable._01_lemon_cheesecake,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                        Ingredient("Butter"),
                        Ingredient("Ketchup"),
                        Ingredient("Fat"),
                        Ingredient("Salami"),
                        Ingredient("Cheese"),
                    ),
                    Color.Yellow,
                    price = "12.50"
                ),
                MenuItem(
                    2,
                    "Pepperoni",
                    "Pizza with pepperoni mmm that smels good ualala bravo maestro!",
                    Res.drawable._02_chocolate_cake_1,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                    ),
                    Color.LightGray,
                    price = "15.99"
                ),
                MenuItem(
                    3,
                    "Ala mafiozi",
                    "Pizza for mafiozi from italy, Mario its mi Mario Pizza Pasta Tiramisu he he he he he ",
                    Res.drawable._03_chocolate_donuts,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                    ),
                    Color.Red,
                    price = "8"
                ),
                MenuItem(
                    4,
                    "Hawai",
                    "Discusting pizza with pineapple its awful do not reccomend jnvoeiveirb e viebriv erhbfiverhb ehfbeirb",
                    Res.drawable._04_fluffy_cake,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                    ),
                    Color.Green,
                    price = "14"
                ),
                MenuItem(
                    5,
                    "Alalala",
                    "The worst pizza in meno, Nobody lived after eating it. weird pizza for looser. if you are a looser then it is your choise",
                    Res.drawable._01_lemon_cheesecake,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                    ),
                    Color.Yellow,
                    price = "17"
                ),
                MenuItem(
                    6,
                    "Pizza ala sheet",
                    "Pizza which contains 4 types of cheese. Fresh Buffalo Mozzarela, Blue cheese, Cheddar, and some poison for health. Good luck my Friend",
                    Res.drawable._02_chocolate_cake_1,
                    listOf(
                        Ingredient("Cheddar"),
                        Ingredient("Dough"),
                        Ingredient("Mozzarella"),
                        Ingredient("Blue cheese"),
                        Ingredient("Olive oil"),
                    ),
                    Color.Blue,
                    price = "19"
                ),
            )
        }
    }
}