package org.gbu.restaurant.decompose.bottomnavholder.wish_list

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.wish_list.wish_list.WishListComponent

interface WishListNavComponent {

    val pages: Value<ChildStack<*, WishListNavChild>>

    sealed interface WishListNavChild {
        data class WishList(val component: WishListComponent) : WishListNavChild
    }
}