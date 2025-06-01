package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.local.entity.Order
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.business.data.network.dto.OrderDTO
import org.gbu.restaurant.business.data.network.dto.toOrder

class GetOrdersUseCase(
//    private val service: MainService,
    private val appDataStoreManager: AppDataStore
) : BaseUseCase<Unit, List<OrderDTO>, List<Order>>(appDataStoreManager) {
    override suspend fun run(params: Unit, token: String): MainGenericResponse<List<OrderDTO>>? {
        TODO("Not yet implemented")
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<OrderDTO>>?): List<Order>? =
        apiResponse?.result?.map { it.toOrder() }

    override val progressBarType: ProgressBarState = ProgressBarState.LoadingWithLogo
    override val needNetworkState: Boolean = true
    override val createException: Boolean = true
    override val checkToken: Boolean = true
}