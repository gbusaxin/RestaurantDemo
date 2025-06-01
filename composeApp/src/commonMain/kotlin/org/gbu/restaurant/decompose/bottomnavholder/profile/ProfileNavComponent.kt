package org.gbu.restaurant.decompose.bottomnavholder.profile

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.gbu.restaurant.decompose.bottomnavholder.profile.my_coupons.MyCouponsComponent
import org.gbu.restaurant.decompose.bottomnavholder.profile.my_orders.MyOrdersComponent
import org.gbu.restaurant.decompose.bottomnavholder.profile.payment.PaymentComponent
import org.gbu.restaurant.decompose.bottomnavholder.profile.profile.ProfileComponent

interface ProfileNavComponent {

    val pages: Value<ChildStack<*, ProfileNavChild>>

    sealed interface ProfileNavChild {
        data class Profile(val component: ProfileComponent) : ProfileNavChild
        data class Payment(val component: PaymentComponent) : ProfileNavChild
        data class MyCoupons(val component: MyCouponsComponent) : ProfileNavChild
        data class MyOrders(val component: MyOrdersComponent) : ProfileNavChild
    }
}