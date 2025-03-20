package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.entity.CartItem
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class CartListUseCase : BaseUseCase<Unit, List<CartItem>, List<CartItem>>() {

    override suspend fun run(params: Unit, token: String): MainGenericResponse<List<CartItem>>? =
        MainGenericResponse(CartItem.fakeList(), true)

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<CartItem>>?): List<CartItem>? =
        apiResponse?.result

    override val progressBarType: ProgressBarState = ProgressBarState.LoadingWithLogo
    override val needNetworkState: Boolean = true
    override val createException: Boolean = true
    override val checkToken: Boolean = true
}