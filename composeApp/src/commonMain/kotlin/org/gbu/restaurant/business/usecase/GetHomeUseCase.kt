package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.network.dto.CategoryDTO
import org.gbu.restaurant.business.data.network.dto.HomeDTO
import org.gbu.restaurant.business.data.network.dto.toHome
import org.gbu.restaurant.business.data.local.entity.Home
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class GetHomeUseCase(
    private val appDataStoreManager: AppDataStore
):BaseUseCase<Unit, HomeDTO, Home>(appDataStoreManager) {
    override suspend fun run(params: Unit, token: String): MainGenericResponse<HomeDTO>? {
        return MainGenericResponse(
            HomeDTO(null, null, null, null, null,null), status = true)
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<HomeDTO>?): Home? {
        return apiResponse?.result?.toHome()
    }


    override val progressBarType: ProgressBarState = ProgressBarState.LoadingWithLogo
    override val needNetworkState: Boolean = true
    override val createException: Boolean = true
    override val checkToken: Boolean = true
}