package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.entity.MenuItem
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class GetMenuDetailsUseCase : BaseUseCase<GetMenuDetailsUseCase.Params, MenuItem, MenuItem>() {

    data class Params(
        val id: Long
    )

    override suspend fun run(params: Params, token: String): MainGenericResponse<MenuItem>? {
        return MainGenericResponse(MenuItem.fakeList().find { it.id == params.id }, true)
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<MenuItem>?): MenuItem? {
        return apiResponse?.result
    }

    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true

}